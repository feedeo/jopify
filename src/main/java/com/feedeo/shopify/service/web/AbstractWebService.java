package com.feedeo.shopify.service.web;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.web.resource.WebResource;

public abstract class AbstractWebService implements WebService {
    protected ShopifySession session;
    protected WebResource resource;

    protected AbstractWebService(ShopifySession session, WebResource resource) {
        this.session = session;
        this.resource = resource;
    }

    @Override
    public ShopifySession getSession() {
        return session;
    }

    @Override
    public WebResource getResource() {
        return resource;
    }
}
