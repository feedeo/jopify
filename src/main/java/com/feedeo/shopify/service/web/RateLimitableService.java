/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.service.web;

import com.feedeo.shopify.ShopifySession;

public interface RateLimitableService extends WebService {
  ShopifySession acquireSession();
}
