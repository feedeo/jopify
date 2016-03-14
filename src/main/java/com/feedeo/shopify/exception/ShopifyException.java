package com.feedeo.shopify.exception;

public abstract class ShopifyException extends Exception {
    public ShopifyException(String message) {
        super(message);
    }
}
