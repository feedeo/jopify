package com.feedeo.shopify;

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
