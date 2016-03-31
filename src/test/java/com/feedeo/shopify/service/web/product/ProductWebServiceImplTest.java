/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.service.web.product;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.web.resource.ProductWebResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductWebServiceImplTest {

  @InjectMocks
  private ProductWebServiceImpl target;

  @Mock
  private ShopifySession session;

  @Mock
  private ProductWebResource resource;

  @Mock
  private Page page;

  @Test
  public void shouldInvokeProductCountWebResourceWithOAuth2AccessTokenAndShopName() {
    String oAuth2AccessToken = "my-oauth2-access-token";
    String shopName = "my-shop-name";
    Long count = 10L;

    when(session.getShopName()).thenReturn(shopName);
    when(session.getoAuth2AccessToken()).thenReturn(oAuth2AccessToken);
    when(session.acquire()).thenReturn(session);
    when(resource.getCount(any(String.class), any(String.class))).thenReturn(count);

    Long result = target.getCount();

    verify(resource, times(1)).getCount(eq(oAuth2AccessToken), eq(shopName));
    verify(session, times(1)).acquire();

    assertThat(result).isEqualTo(count);
  }

  @Test
  public void shouldInvokeProductsWebResourceWithOAuth2AccessTokenAndShopName() {
    String oAuth2AccessToken = "my-oauth2-access-token";
    String shopName = "my-shop-name";

    when(session.getShopName()).thenReturn(shopName);
    when(session.getoAuth2AccessToken()).thenReturn(oAuth2AccessToken);
    when(session.acquire()).thenReturn(session);
    when(resource.getAll(eq(oAuth2AccessToken), eq(shopName), any(Pageable.class), any(String[].class))).thenReturn(page);

    Page page = target.getAll();

    verify(resource, times(1)).getAll(eq(oAuth2AccessToken), eq(shopName), any(Pageable.class), any(String[].class));
    verify(session, times(1)).acquire();

    assertThat(page).isEqualTo(page);
  }
}
