/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016, Feedeo AB <hugo@feedeo.io>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
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
