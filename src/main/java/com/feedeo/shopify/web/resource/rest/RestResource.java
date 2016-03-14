package com.feedeo.shopify.web.resource.rest;

import com.feedeo.shopify.web.resource.WebResource;
import org.springframework.web.client.RestOperations;

public interface RestResource extends WebResource {
    RestOperations getRestOperations();
}
