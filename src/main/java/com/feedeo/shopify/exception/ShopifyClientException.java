package com.feedeo.shopify.exception;

public abstract class ShopifyClientException extends ShopifyException {
    public ShopifyClientException(String message) {
        super(message);
    }
}
