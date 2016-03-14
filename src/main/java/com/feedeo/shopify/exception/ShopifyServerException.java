package com.feedeo.shopify.exception;

public abstract class ShopifyServerException extends ShopifyException {
    public ShopifyServerException(String message) {
        super(message);
    }
}
