/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource;

public interface ProductWebResource extends WebResource {
  long getCount(String accessToken, String shopName);
}
