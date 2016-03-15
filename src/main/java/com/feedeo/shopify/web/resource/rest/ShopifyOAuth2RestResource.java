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

package com.feedeo.shopify.web.resource.rest;

import com.feedeo.rest.client.AbstractOAuth2RestClient;
import com.feedeo.rest.client.AbstractRestClient;
import com.feedeo.shopify.exception.ShopifyServerException;
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

  public ShopifyOAuth2RestResource() {
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
      } catch (HttpClientErrorException e) {
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

      } catch (HttpServerErrorException e) {

        propagate(new ShopifyServerException(e));
      }
    }
  }
}
