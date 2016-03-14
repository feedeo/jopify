package com.feedeo.shopify.service;

import com.feedeo.shopify.ShopifyService.Service;
import com.feedeo.shopify.model.Shop;

public interface ShopService extends Service {
    Shop getShop();
}
