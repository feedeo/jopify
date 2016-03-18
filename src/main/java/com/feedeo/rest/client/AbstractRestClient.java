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
}
