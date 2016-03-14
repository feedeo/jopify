package com.feedeo.shopify.exception.web.resource.rest;

public class NotFoundRestResourceException extends RestResourceException {
    public NotFoundRestResourceException() {
        super("Not found");
    }
}
