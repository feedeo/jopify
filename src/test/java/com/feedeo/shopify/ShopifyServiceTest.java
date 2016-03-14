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

package com.feedeo.shopify;

import com.feedeo.shopify.service.ShopService;
import com.feedeo.shopify.service.web.shop.ShopWebServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ShopifyServiceTest {

  private ShopifyService target;

  @Mock
  private ShopifySession session;

  @Before
  public void setUp() {
    target = new ShopifyService();
  }

  @Test
  public void shouldGetShopWebServiceImplementationWithSession() {
    ShopifyService.Service service = target.getService(session, ShopService.class);

    assertThat(service)
            .isNotNull()
            .isInstanceOf(ShopService.class)
            .isExactlyInstanceOf(ShopWebServiceImpl.class);

    assertThat(service.getSession())
            .isNotNull();
  }
}
