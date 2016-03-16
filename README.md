# Jopify: Shopify API Java Library
[![][travis img]][travis]
[![][release img]][release]
[![][license img]][license]
[![][codecov img]][codecov]

## Installation
Add `jopify` to your `dependencies` block in your `pom.xml`.
```xml
<dependency>
  <groupId>com.feedeo</groupId>
  <artifactId>jopify</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage :shipit:
Build a `ShopifySession` from an OAuth 2.0 access token and the shop name. Then instantiate the desired API service through `ShopifyService`.
```java
import com.feedeo.shopify.*;

String oAuth2AccessToken = "my-oauth2-access-token";
String shopName = "my-shop-name";

ShopifySession session = new ShopifySession.Builder()
  .withOAuth2AccessToken(oAuth2AccessToken)
  .withShopName(shopName)
  .build();

ShopifyService shopifyService = new ShopifyService();

ShopService shopService = shopifyService.getService(session, ShopService.class);

try {
  Shop shop = shopService.getShop();
} catch (ShopifyException e) {
    e.printStackTrace();
}
```

## Limitations
As of now it only supports a limited number of API services.

[Shop](https://docs.shopify.com/api/reference/shop) :white_check_mark:

## Documentation
Seet the [Javadoc](http://feedeo.github.io/jopify/javadoc/) for the library API.

## Reference
[Shopify API reference](https://docs.shopify.com/api/reference)

[travis]:https://travis-ci.org/feedeo/jopify
[travis img]:https://travis-ci.org/feedeo/jopify.svg?branch=master

[release]:https://github.com/feedeo/jopify/releases
[release img]:https://img.shields.io/github/release/feedeo/jopify.svg

[license]:LICENSE
[license img]:https://img.shields.io/badge/license-MIT-blue.svg

[codecov]:https://codecov.io/github/feedeo/jopify?branch=master
[codecov img]:https://codecov.io/github/feedeo/jopify/coverage.svg?branch=master