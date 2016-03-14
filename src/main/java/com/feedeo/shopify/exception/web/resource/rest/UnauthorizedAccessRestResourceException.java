package com.feedeo.shopify.exception.web.resource.rest;

public class UnauthorizedAccessRestResourceException extends RestResourceException {
    public UnauthorizedAccessRestResourceException(String message) {
        super("Unauthorized access " + message);
    }
}
