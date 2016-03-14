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

  public String getClientId() {
    OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
    return restOperations.getResource().getClientId();
  }

  public void setClientId(String clientId) {
    OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
    BaseOAuth2ProtectedResourceDetails resource = (BaseOAuth2ProtectedResourceDetails) restOperations.getResource();
    resource.setClientId(clientId);
  }

  public String getClientSecret() {
    OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
    return restOperations.getResource().getClientSecret();
  }

  public void setClientSecret(String clientSecret) {
    OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
    BaseOAuth2ProtectedResourceDetails resource = (BaseOAuth2ProtectedResourceDetails) restOperations.getResource();
    resource.setClientSecret(clientSecret);
  }

  public void setAccessToken(String accessToken) {
    OAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

    OAuth2RestTemplate restOperations = (OAuth2RestTemplate) getRestOperations();
    restOperations.getOAuth2ClientContext().setAccessToken(oAuth2AccessToken);
  }

  @Override
  protected RestOperations createRestOperations(ObjectMapper objectMapper, ClientHttpRequestFactory clientHttpRequestFactory) {
    RestTemplate restTemplate = (RestTemplate) super.createRestOperations(objectMapper, clientHttpRequestFactory);

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
