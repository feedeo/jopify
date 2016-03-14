package com.feedeo.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.feedeo.web.client.AbstractWebClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_ROOT_VALUE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;
import static java.util.TimeZone.getDefault;

public abstract class AbstractRestClient extends AbstractWebClient {

    private ObjectMapper objectMapper;
    private ClientHttpRequestFactory clientHttpRequestFactory;
    private RestOperations restOperations;

    protected AbstractRestClient() {
        super();

        objectMapper = createObjectMapper();
        clientHttpRequestFactory = createClientHttpRequestFactory(getHttpClient());
        restOperations = createRestOperations(objectMapper, clientHttpRequestFactory);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public RestOperations getRestOperations() {
        return restOperations;
    }

    protected ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.configure(WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(UNWRAP_ROOT_VALUE, true);

        objectMapper.registerModule(new JodaModule());

        objectMapper.setTimeZone(getDefault());

        return objectMapper;
    }

    protected ClientHttpRequestFactory createClientHttpRequestFactory(HttpClient httpClient) {
        return new HttpComponentsClientHttpRequestFactory(httpClient) {
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                switch (httpMethod) {
                    case GET:
                        return new HttpGet(uri);
                    case DELETE:
                        return new HttpEntityEnclosingDeleteRequest(uri);
                    case HEAD:
                        return new HttpHead(uri);
                    case OPTIONS:
                        return new HttpOptions(uri);
                    case POST:
                        return new HttpPost(uri);
                    case PUT:
                        return new HttpPut(uri);
                    case TRACE:
                        return new HttpTrace(uri);
                    case PATCH:
                        return new HttpPatch(uri);
                    default:
                        throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
                }
            }
        };
    }

    protected static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {

        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    protected RestOperations createRestOperations(ObjectMapper objectMapper, ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                return clientHttpRequestExecution.execute(httpRequest, bytes);
            }
        });
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
