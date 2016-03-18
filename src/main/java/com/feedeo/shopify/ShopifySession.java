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

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class ShopifySession {
  private String shopName;
  private String oAuth2AccessToken;

  public ShopifySession(Builder builder) {
    this.shopName = builder.shopName;
    this.oAuth2AccessToken = builder.oAuth2AccessToken;
  }

  public String getShopName() {
    return shopName;
  }

  public String getoAuth2AccessToken() {
    return oAuth2AccessToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ShopifySession)) return false;
    ShopifySession that = (ShopifySession) o;
    return Objects.equal(shopName, that.shopName) &&
            Objects.equal(oAuth2AccessToken, that.oAuth2AccessToken);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(shopName, oAuth2AccessToken);
  }

  public static class Builder {
    private String shopName;
    private String oAuth2AccessToken;

    public Builder() {
    }

    public Builder withShopName(String shopName) {
      this.shopName = shopName;
      return this;
    }

    public Builder withOAuth2AccessToken(String oAuth2AccessToken) {
      this.oAuth2AccessToken = oAuth2AccessToken;
      return this;
    }

    public ShopifySession build() {
      checkArgument(!isNullOrEmpty(shopName));
      checkArgument(!isNullOrEmpty(oAuth2AccessToken));

      return new ShopifySession(this);
    }
  }
}
