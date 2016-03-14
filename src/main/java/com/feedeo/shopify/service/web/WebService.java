package com.feedeo.shopify.service.web;

import com.feedeo.shopify.ShopifyService;
import com.feedeo.shopify.web.resource.WebResource;

public interface WebService extends ShopifyService.Service {
    WebResource getResource();
}
