/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016, Feedeo AB <hugo@feedeo.io>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.feedeo.shopify.web.resource.rest.shop;

import com.feedeo.shopify.model.Shop;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
public class ShopOAuth2RestResourceTest {
  public static final String responseOk = "{\n" +
          "  \"shop\": {\n" +
          "    \"id\": 690933842,\n" +
          "    \"name\": \"Apple Computers\",\n" +
          "    \"email\": \"steve@apple.com\",\n" +
          "    \"domain\": \"shop.apple.com\",\n" +
          "    \"created_at\": \"2007-12-31T19:00:00-05:00\",\n" +
          "    \"province\": \"California\",\n" +
          "    \"country\": \"US\",\n" +
          "    \"address1\": \"1 Infinite Loop\",\n" +
          "    \"zip\": \"95014\",\n" +
          "    \"city\": \"Cupertino\",\n" +
          "    \"source\": null,\n" +
          "    \"phone\": \"1231231234\",\n" +
          "    \"updated_at\": \"2015-09-02T14:53:51-04:00\",\n" +
          "    \"customer_email\": \"customers@apple.com\",\n" +
          "    \"latitude\": 45.45,\n" +
          "    \"longitude\": -75.43,\n" +
          "    \"primary_location_id\": null,\n" +
          "    \"primary_locale\": \"en\",\n" +
          "    \"country_code\": \"US\",\n" +
          "    \"country_name\": \"United States\",\n" +
          "    \"currency\": \"USD\",\n" +
          "    \"timezone\": \"(GMT-05:00) Eastern Time (US & Canada)\",\n" +
          "    \"iana_timezone\": \"America\\/New_York\",\n" +
          "    \"shop_owner\": \"Steve Jobs\",\n" +
          "    \"money_format\": \"$ {{amount}}\",\n" +
          "    \"money_with_currency_format\": \"$ {{amount}} USD\",\n" +
          "    \"province_code\": \"CA\",\n" +
          "    \"taxes_included\": null,\n" +
          "    \"tax_shipping\": null,\n" +
          "    \"county_taxes\": true,\n" +
          "    \"plan_display_name\": \"Shopify Plus\",\n" +
          "    \"plan_name\": \"enterprise\",\n" +
          "    \"myshopify_domain\": \"apple.myshopify.com\",\n" +
          "    \"google_apps_domain\": null,\n" +
          "    \"google_apps_login_enabled\": null,\n" +
          "    \"eligible_for_payments\": true,\n" +
          "    \"requires_extra_payments_agreement\": false,\n" +
          "    \"password_enabled\": false,\n" +
          "    \"has_storefront\": true,\n" +
          "    \"setup_required\": false\n" +
          "  }\n" +
          "}";

  private ShopOAuth2RestResource target;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    target = new ShopOAuth2RestResource();

    RestTemplate restTemplate = (RestTemplate) target.getRestOperations();

    mockServer = createServer(restTemplate);
  }

  @Test
  public void shouldGetShopifyShop() {
    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/shop.json"))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseOk, APPLICATION_JSON));

    Shop shop = target.getShop("my-access-token", "my-shop");

    mockServer.verify();

    assertThat(shop).isNotNull();
    assertThat(shop.getId()).isEqualTo(690933842);
    assertThat(shop.getName()).isEqualTo("Apple Computers");
    assertThat(shop.getEmail()).isEqualTo("steve@apple.com");
    assertThat(shop.getDomain()).isEqualTo("shop.apple.com");
    assertThat(shop.getCreatedAt()).isEqualTo(new DateTime("2007-12-31T19:00:00-05:00"));
    assertThat(shop.getProvince()).isEqualTo("California");
    assertThat(shop.getCountry()).isEqualTo("US");
    assertThat(shop.getAddress1()).isEqualTo("1 Infinite Loop");
    assertThat(shop.getZip()).isEqualTo("95014");
    assertThat(shop.getCity()).isEqualTo("Cupertino");
    assertThat(shop.getSource()).isNull();
    assertThat(shop.getPhone()).isEqualTo("1231231234");
    assertThat(shop.getUpdatedAt()).isEqualTo(new DateTime("2015-09-02T14:53:51-04:00"));
    assertThat(shop.getCustomerEmail()).isEqualTo("customers@apple.com");
    assertThat(shop.getLatitude()).isEqualTo(45.45);
    assertThat(shop.getLongitude()).isEqualTo(-75.43);
    assertThat(shop.getPrimaryLocationId()).isNull();
    assertThat(shop.getPrimaryLocale()).isEqualTo("en");
    assertThat(shop.getCountryCode()).isEqualTo("US");
    assertThat(shop.getCountryName()).isEqualTo("United States");
    assertThat(shop.getCurrency()).isEqualTo("USD");
    assertThat(shop.getTimezone()).isEqualTo("(GMT-05:00) Eastern Time (US & Canada)");
    assertThat(shop.getIanaTimezone()).isEqualTo("America/New_York");
    assertThat(shop.getShopOwner()).isEqualTo("Steve Jobs");
    assertThat(shop.getMoneyFormat()).isEqualTo("$ {{amount}}");
    assertThat(shop.getMoneyWithCurrencyFormat()).isEqualTo("$ {{amount}} USD");
    assertThat(shop.getProvinceCode()).isEqualTo("CA");
    assertThat(shop.isTaxesIncluded()).isNull();
    assertThat(shop.getTaxShipping()).isNull();
    assertThat(shop.isCountyTaxes()).isEqualTo(true);
    assertThat(shop.getPlanDisplayName()).isEqualTo("Shopify Plus");
    assertThat(shop.getPlanName()).isEqualTo("enterprise");
    assertThat(shop.getMyshopifyDomain()).isEqualTo("apple.myshopify.com");
    assertThat(shop.getGoogleAppsDomain()).isNull();
    assertThat(shop.getGoogleAppsLoginEnabled()).isNull();
    assertThat(shop.getPasswordEnabled()).isEqualTo(false);
    assertThat(shop.getHasStorefront()).isEqualTo(true);
    assertThat(shop.getSetupRequired()).isEqualTo(false);
  }

}
