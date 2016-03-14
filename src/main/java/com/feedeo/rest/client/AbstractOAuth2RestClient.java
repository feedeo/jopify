package com.feedeo.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractOAuth2RestClient extends AbstractRestClient {

    public AbstractOAuth2RestClient(String clientId, String clientSecret) {
        super();

        setClientId(clientId);
        setClientSecret(clientSecret);
    }

    public void setClientId(String clientId) {
        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
        BaseOAuth2ProtectedResourceDetails resource = (BaseOAuth2ProtectedResourceDetails) restOperations.getResource();
        resource.setClientId(clientId);
    }

    public void setClientSecret(String clientSecret) {
        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
        BaseOAuth2ProtectedResourceDetails resource = (BaseOAuth2ProtectedResourceDetails) restOperations.getResource();
        resource.setClientSecret(clientSecret);
    }

    public String getClientId() {
        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
        return restOperations.getResource().getClientId();
    }

    public String getClientSecret() {
        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
        return restOperations.getResource().getClientSecret();
    }

    public void setAccessToken(String accessToken) {
        OAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
        restOperations.getOAuth2ClientContext().setAccessToken(oAuth2AccessToken);
    }

    @Override
    protected RestOperations createRestOperations(ObjectMapper objectMapper, ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate  = (RestTemplate) super.createRestOperations(objectMapper, clientHttpRequestFactory);

        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(new BaseOAuth2ProtectedResourceDetails(), new DefaultOAuth2ClientContext());
        oAuth2RestTemplate.setMessageConverters(restTemplate.getMessageConverters());
        oAuth2RestTemplate.setInterceptors(restTemplate.getInterceptors());

        return oAuth2RestTemplate;
    }

    @Override
    public OAuth2RestOperations getRestOperations() {
        return (OAuth2RestOperations) super.getRestOperations();
    }
}
