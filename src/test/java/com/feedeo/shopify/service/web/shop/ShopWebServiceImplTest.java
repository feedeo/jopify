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

package com.feedeo.shopify.service.web.shop;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.web.resource.ShopWebResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopWebServiceImplTest {

  @InjectMocks
  private ShopWebServiceImpl target;

  @Mock
  private ShopifySession session;

  @Mock
  private ShopWebResource resource;

  @Test
  public void shouldInvokeShopWebResourceWithOAuth2AccessTokenAndShopName() {
    String oAuth2AccessToken = "my-oauth2-access-token";
    String shopName = "my-shop-name";

    when(session.getShopName()).thenReturn(shopName);
    when(session.getoAuth2AccessToken()).thenReturn(oAuth2AccessToken);
    when(session.acquire()).thenReturn(session);

    target.getShop();

    verify(resource, times(1)).getShop(eq(oAuth2AccessToken), eq(shopName));
    verify(session, times(1)).acquire();
  }
}
