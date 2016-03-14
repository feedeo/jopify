package com.feedeo.web.client;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.http.protocol.HTTP.CONN_KEEP_ALIVE;

public abstract class AbstractWebClient {

    private HttpClient httpClient;

    protected AbstractWebClient() {
        httpClient = createHttpClient();
    }

    protected HttpClient getHttpClient() {
        return httpClient;
    }

    protected HttpClient createHttpClient() {

        final SocketConfig socketConfig = SocketConfig
                .custom()
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .build();

        final ConnectionConfig connectionConfig = ConnectionConfig
                .custom()
                .build();

        final RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setStaleConnectionCheckEnabled(false)
                .build();

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(256);
        connectionManager.setDefaultMaxPerRoute(256);

        IdleConnectionMonitorThread staleMonitor = new IdleConnectionMonitorThread(connectionManager);
        staleMonitor.start();

        try {
            staleMonitor.join(1000);
        } catch (InterruptedException ignored) {
        }

        final ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator(CONN_KEEP_ALIVE));
                while (iterator.hasNext()) {
                    HeaderElement header = iterator.nextElement();
                    String param = header.getName();
                    String value = header.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return 5 * 1000;
            }
        };

        return HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultSocketConfig(socketConfig)
                .setDefaultConnectionConfig(connectionConfig)
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .build();
    }

    public class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connectionManager;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connectionManager) {
            super();

            this.connectionManager = connectionManager;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(1000);
                        connectionManager.closeExpiredConnections();
                        connectionManager.closeIdleConnections(30, SECONDS);
                    }
                }
            } catch (InterruptedException ignored) {
                shutdown();
            }
        }

        public void shutdown() {
            shutdown = true;

            synchronized (this) {
                notifyAll();
            }
        }
    }
}
