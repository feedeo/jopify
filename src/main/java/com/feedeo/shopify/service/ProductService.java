/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.service;

import com.feedeo.shopify.ShopifyService.Service;
import com.feedeo.shopify.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends Service {
  long getCount();

  Page<Product> getAll();

  Page<Product> getAll(final Pageable page);

  Page<Product> getAll(final Pageable page, final String[] select);
}
