package com.feedeo.shopify.service.web.shop;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.model.Shop;
import com.feedeo.shopify.service.ShopService;
import com.feedeo.shopify.service.web.AbstractWebService;
import com.feedeo.shopify.web.resource.ShopWebResource;

public class ShopWebServiceImpl extends AbstractWebService implements ShopService {
    public ShopWebServiceImpl(ShopifySession session, ShopWebResource resource) {
        super(session, resource);
    }

    @Override
    public Shop getShop() {
        return ((ShopWebResource) resource).getByShopName(session.getShopName(), session.getoAuth2AccessToken());
    }
}
