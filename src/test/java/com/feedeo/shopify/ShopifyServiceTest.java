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
