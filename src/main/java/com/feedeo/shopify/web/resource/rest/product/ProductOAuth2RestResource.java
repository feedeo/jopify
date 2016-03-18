/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource.rest.product;

import com.feedeo.shopify.web.resource.ProductWebResource;
import com.feedeo.shopify.web.resource.rest.ShopifyOAuth2RestResource;

import static com.feedeo.shopify.constant.ApiConstant.BASE_URL;
import static com.feedeo.shopify.constant.ApiConstant.PRODUCT_COUNT_ENDPOINT;

public class ProductOAuth2RestResource extends ShopifyOAuth2RestResource implements ProductWebResource {

  @Override
  public long getCount(String accessToken, String shopName) {
    return getRestOperationsWithAccessToken(accessToken)
            .getForEntity(BASE_URL + PRODUCT_COUNT_ENDPOINT, ProductCountOAuth2RestResourceResponse.class, shopName)
            .getBody()
            .getCount();
  }
}
