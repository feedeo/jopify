Jopify: Shopify API Java Library
=======
[![][travis img]][travis]
[![][release img]][release]
[![][license img]][license]
[![][codecov img]][codecov]

Installation
======
Add the dependency to your `dependencies` block in your `pom.xml`
```xml
<dependency>
  <groupId>com.feedeo</groupId>
  <artifactId>jopify</artifactId>
  <version>0.1.0</version>
</dependency>
```

Usage :shipit:
======
Create a session from an OAuth 2.0 access token and the shop name and instantiate the desired API service.
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

Shop shop = shopService.getShop();

```

Supported API services
==
As of now it only supports a limited number of API services.

~~Application proxies~~
~~ApplicationCharge~~
~~Article~~
~~Asset~~
~~Blog~~
~~CarrierService~~
~~Checkout~~
~~Collect~~
~~Comment~~
~~Country~~
~~CustomCollection~~
~~Customer~~
~~CustomerAddress~~
~~CustomerSavedSearch~~
~~Discount~~
~~Event~~
~~Fulfillment~~
~~FulfillmentEvent~~
~~FulfillmentService~~
~~Gift Card~~
~~Location~~
~~Metafield~~
~~Multipass~~
~~Order~~
~~Order Risks~~
~~Page~~
~~Policy~~
~~Product~~
~~Product Image~~
~~Product Variant~~
~~Province~~
~~RecurringApplicationCharge~~
~~Redirect~~
~~Refund~~
~~ScriptTag~~
~~Shipping Zone~~
Shop :white_check_mark:
~~SmartCollection~~
~~Theme~~
~~Transaction~~
~~User~~
~~Webhook~~

Java Docs
==

Reference
======
https://docs.shopify.com/api/reference

[travis]:https://travis-ci.org/feedeo/jopify
[travis img]:https://travis-ci.org/feedeo/jopify.svg?branch=master

[release]:https://github.com/feedeo/jopify/releases
[release img]:https://img.shields.io/github/release/feedeo/jopify.svg

[license]:LICENSE
[license img]:https://img.shields.io/badge/license-MIT-blue.svg

[codecov]:https://codecov.io/github/feedeo/jopify?branch=master
[codecov img]:https://codecov.io/github/feedeo/jopify/coverage.svg?branch=master