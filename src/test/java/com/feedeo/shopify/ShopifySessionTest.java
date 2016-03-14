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

        target = new ShopifySession.Builder()
                .withShopName(shopName)
                .withOAuth2AccessToken(oAuth2AccessToken)
                .build();

        assertThat(target.getShopName()).isEqualTo(shopName);
        assertThat(target.getoAuth2AccessToken()).isEqualTo(oAuth2AccessToken);
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
