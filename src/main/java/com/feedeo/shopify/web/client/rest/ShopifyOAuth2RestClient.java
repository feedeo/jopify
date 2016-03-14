package com.feedeo.shopify.web.client.rest;

import com.feedeo.rest.client.AbstractOAuth2RestClient;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class ShopifyOAuth2RestClient extends AbstractOAuth2RestClient {
    private static ShopifyOAuth2RestClient instance = null;

    private ShopifyOAuth2RestClient(String clientId, String clientSecret) {
        super(clientId, clientSecret);
    }

    public static ShopifyOAuth2RestClient getInstance() {
        if(instance == null) {
            synchronized(ShopifyOAuth2RestClient.class) {
                if(instance == null) {
                    instance = createSingleton();
                }
            }
        }
        return instance;
    }

    private static ShopifyOAuth2RestClient createSingleton() {
        Properties properties = getProperties();

        String clientId = getClientIdFromProperties(properties);
        String clientSecret = getClientSecretFromProperties(properties);

        ShopifyOAuth2RestClient client = new ShopifyOAuth2RestClient(clientId, clientSecret);

        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) client.getRestOperations();
        restOperations.setAuthenticator(new ShopifyOAuth2RequestAuthenticator());

        return client;
    }

    private static Properties getProperties() {
        final Properties properties = new Properties();

        final URL url = Resources.getResource("jopify.properties");
        final ByteSource byteSource = Resources.asByteSource(url);

        InputStream inputStream = null;
        try {
            inputStream = byteSource.openBufferedStream();
            properties.load(inputStream);
        } catch (final IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return properties;
    }

    private static String getClientIdFromProperties(Properties properties) {
        String clientId = properties.getProperty("jopify.client_id");

        checkArgument(!isNullOrEmpty(clientId), "Client ID is not set in properties file");

        return clientId;
    }

    private static String getClientSecretFromProperties(Properties properties) {
        String clientSecret = properties.getProperty("jopify.client_secret");

        checkArgument(!isNullOrEmpty(clientSecret), "Client secret is not set in properties file");

        return properties.getProperty("jopify.client_secret");
    }

    private static class ShopifyOAuth2RequestAuthenticator implements OAuth2RequestAuthenticator {

        @Override
        public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext,
                                 ClientHttpRequest request) {
            OAuth2AccessToken accessToken = clientContext.getAccessToken();
            if (accessToken == null) {
                throw new AccessTokenRequiredException(resource);
            }

            request.getHeaders().set("X-Shopify-Access-Token", accessToken.getValue());
        }

    }
}
