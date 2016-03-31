/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource.rest.product;

import com.feedeo.shopify.model.Product;
import com.feedeo.shopify.web.resource.ProductWebResource;
import com.feedeo.shopify.web.resource.rest.ShopifyOAuth2RestResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.feedeo.shopify.constant.ApiConstant.*;
import static com.google.common.base.Joiner.on;

public class ProductOAuth2RestResource extends ShopifyOAuth2RestResource implements ProductWebResource {

  @Override
  public long getCount(final String accessToken, final String shopName) {
    return getRestOperationsWithAccessToken(accessToken)
            .getForEntity(BASE_URL + PRODUCT_COUNT_ENDPOINT, ProductCountOAuth2RestResourceResponse.class, shopName)
            .getBody()
            .getCount();
  }

  @Override
  public Page<Product> getAll(final String accessToken, final String shopName, final Pageable pageable, final String[] select) {
    long total = getCount(accessToken, shopName);

    List<Product> products = getRestOperationsWithAccessToken(accessToken)
            .getForEntity(BASE_URL + PRODUCT_ENDPOINT, ProductsOAuth2RestResourceResponse.class,
                    shopName,
                    pageable.getPageNumber() + 1, // Shopify pages start at index 1
                    pageable.getPageSize(),
                    on(",").skipNulls().join(select)
            )
            .getBody()
            .getProducts();

    return new PageImpl<>(products, new PageRequest(pageable.getPageNumber(), pageable.getPageSize()), total);
  }
}
