package com.feedeo.shopify.exception.web.resource.rest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.feedeo.shopify.exception.ShopifyClientException;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use= NAME, include= PROPERTY, property="errors")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NotFoundRestResourceException.class, name = "Not Found"),
        @JsonSubTypes.Type(value = UnauthorizedAccessRestResourceException.class, name = "[API] Invalid API key or access token (unrecognized login or wrong password)")
})
public abstract class RestResourceException extends ShopifyClientException {
    public RestResourceException(String message) {
        super(message);
    }
}
