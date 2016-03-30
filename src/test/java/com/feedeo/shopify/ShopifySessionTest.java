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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ShopifySessionTest {

  private ShopifySession target;

  @Test
  public void shouldBuildSession() {
    final String shopName = "my-shop-name";
    final String oAuth2AccessToken = "my-oauth2-access-token";
    final double rateLimit = 1.0;

    target = new ShopifySession.Builder()
            .withShopName(shopName)
            .withOAuth2AccessToken(oAuth2AccessToken)
            .withRateLimit(rateLimit)
            .build();

    assertThat(target.getShopName()).isEqualTo(shopName);
    assertThat(target.getoAuth2AccessToken()).isEqualTo(oAuth2AccessToken);
    assertThat(target.getRateLimit()).isEqualTo(rateLimit);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWithNullShopName() {
    final String shopName = null;
    final String oAuth2AccessToken = "my-oauth2-access-token";

    target = new ShopifySession.Builder()
            .withShopName(shopName)
            .withOAuth2AccessToken(oAuth2AccessToken)
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWithEmptyShopName() {
    final String shopName = "";
    final String oAuth2AccessToken = "my-oauth2-access-token";

    target = new ShopifySession.Builder()
            .withShopName(shopName)
            .withOAuth2AccessToken(oAuth2AccessToken)
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWithNullOAuth2AccessToken() {
    final String shopName = "my-shop-name";
    final String oAuth2AccessToken = null;

    target = new ShopifySession.Builder()
            .withShopName(shopName)
            .withOAuth2AccessToken(oAuth2AccessToken)
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWithEmptyOAuth2AccessToken() {
    final String shopName = "my-shop-name";
    final String oAuth2AccessToken = "";

    target = new ShopifySession.Builder()
            .withShopName(shopName)
            .withOAuth2AccessToken(oAuth2AccessToken)
            .build();
  }
}
