package com.feedeo.shopify.web.resource;


import com.feedeo.shopify.ShopifyService.Resource;
import com.feedeo.web.client.AbstractWebClient;

public interface WebResource extends Resource {
    AbstractWebClient getClient();
}
