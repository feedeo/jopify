/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource.rest.product;

import com.feedeo.shopify.model.Product;

import java.util.List;

public class ProductsOAuth2RestResourceResponse {
  private List<Product> products;

  public List<Product> getProducts() {
    return products;
  }
}