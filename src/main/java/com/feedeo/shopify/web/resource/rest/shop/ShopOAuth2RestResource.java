package com.feedeo.shopify.web.resource.rest.shop;

import com.feedeo.shopify.model.Shop;
import com.feedeo.shopify.web.resource.ShopWebResource;
import com.feedeo.shopify.web.resource.rest.ShopifyOAuth2RestResource;

import static com.feedeo.shopify.constant.ApiConstant.BASE_URL;
import static com.feedeo.shopify.constant.ApiConstant.SHOP_ENDPOINT;

public class ShopOAuth2RestResource extends ShopifyOAuth2RestResource implements ShopWebResource {

    @Override
    public Shop getByShopName(String accessToken, String shopName) {
        return getRestOperationsWithAccessToken(accessToken)
                .getForEntity(BASE_URL + SHOP_ENDPOINT, Shop.class, shopName)
                .getBody();
    }
}
