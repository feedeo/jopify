package com.feedeo.shopify.web.resource.rest;

import org.springframework.security.oauth2.client.OAuth2RestOperations;

public interface OAuth2RestResource extends RestResource {
    OAuth2RestOperations getRestOperations();
}
