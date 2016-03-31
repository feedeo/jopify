/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource;

import com.feedeo.shopify.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductWebResource extends WebResource {
  long getCount(final String accessToken, final String shopName);

  Page<Product> getAll(final String accessToken, final String shopName, final Pageable pageable, final String[] select);
}
