package com.feedeo.shopify.web.resource;

import com.feedeo.shopify.model.Shop;

public interface ShopWebResource extends WebResource {
    Shop getByShopName(String accessToken, String shopName);
}
