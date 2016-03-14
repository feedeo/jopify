package com.feedeo.shopify.web.client.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ShopifyOAuth2RestClientTest {

    @Test
    public void shouldReturnSameInstance() {
        ShopifyOAuth2RestClient target = ShopifyOAuth2RestClient.getInstance();

        assertThat(target).isSameAs(ShopifyOAuth2RestClient.getInstance());
    }
}
