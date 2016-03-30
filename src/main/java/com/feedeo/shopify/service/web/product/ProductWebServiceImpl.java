/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.service.web.product;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.service.ProductService;
import com.feedeo.shopify.service.web.AbstractWebService;
import com.feedeo.shopify.web.resource.ProductWebResource;

public class ProductWebServiceImpl extends AbstractWebService implements ProductService {
  public ProductWebServiceImpl(ShopifySession session, ProductWebResource resource) {
    super(session, resource);
  }

  @Override
  public long getCount() {
    ShopifySession session = this.acquireSession();
    ProductWebResource resource = (ProductWebResource) this.getResource();

    return resource.getCount(session.getoAuth2AccessToken(), session.getShopName());
  }
}
