package com.feedeo.shopify.web.resource.rest;

import com.feedeo.rest.client.AbstractOAuth2RestClient;
import com.feedeo.rest.client.AbstractRestClient;
import com.feedeo.shopify.exception.web.resource.rest.RestResourceException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.feedeo.shopify.web.client.rest.ShopifyOAuth2RestClient.getInstance;
import static com.google.common.base.Throwables.propagate;

public class ShopifyOAuth2RestResource implements OAuth2RestResource {

    protected AbstractOAuth2RestClient client = getInstance();

    public ShopifyOAuth2RestResource () {
        RestTemplate restTemplate = (RestTemplate) client.getRestOperations();
        restTemplate.setErrorHandler(new ShopifyOAuth2RestResourceErrorHandler());
    }

    public OAuth2RestOperations getRestOperationsWithAccessToken(String accessToken) {
        client.setAccessToken(accessToken);
        return client.getRestOperations();
    }

    @Override
    public OAuth2RestOperations getRestOperations() {
        return client.getRestOperations();
    }

    @Override
    public AbstractRestClient getClient() {
        return client;
    }

    private class ShopifyOAuth2RestResourceErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            try {
                super.handleError(response);
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                if (e.getResponseBodyAsString() == null) {
                    throw e;
                }

                RestResourceException resourceException;
                try {
                    resourceException = client.getObjectMapper()
                            .reader(RestResourceException.class)
                            .withRootName("") // http://stackoverflow.com/questions/8837018/jackson-json-deserialization-ignore-root-element-from-json
                            .readValue(e.getResponseBodyAsString());
                } catch (Exception ignored) {
                    throw e;
                }

                propagate(resourceException);
            }
        }
    }
}
