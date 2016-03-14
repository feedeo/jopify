package com.feedeo.shopify.service.web.shop;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.service.web.shop.ShopWebServiceImpl;
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
    public void shouldInvokeShopWebResourceWithShopNameAndOAuth2AccessToken() {
        String shopName = "my-shop-name";
        String oAuth2AccessToken = "my-oauth2-access-token";

        when(session.getShopName()).thenReturn(shopName);
        when(session.getoAuth2AccessToken()).thenReturn(oAuth2AccessToken);

        target.getShop();

        verify(resource, times(1)).getByShopName(eq(shopName), eq(oAuth2AccessToken));
    }
}
