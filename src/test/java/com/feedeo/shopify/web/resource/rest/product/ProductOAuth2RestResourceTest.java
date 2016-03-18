/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource.rest.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
public class ProductOAuth2RestResourceTest {
  public static final String responseOk = "{\"count\": 2}";

  private ProductOAuth2RestResource target;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    target = new ProductOAuth2RestResource();

    RestTemplate restTemplate = (RestTemplate) target.getRestOperations();

    mockServer = createServer(restTemplate);
  }

  @Test
  public void shouldGetShopifyProductCount() {
    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products/count.json"))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseOk, APPLICATION_JSON));

    long count = target.getCount("my-access-token", "my-shop");

    mockServer.verify();

    assertThat(count).isEqualTo(2);
  }

}
