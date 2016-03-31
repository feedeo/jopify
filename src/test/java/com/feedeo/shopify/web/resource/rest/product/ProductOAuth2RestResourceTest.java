/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.web.resource.rest.product;

import com.feedeo.shopify.model.Product;
import com.feedeo.shopify.model.ProductImage;
import com.feedeo.shopify.model.ProductOption;
import com.feedeo.shopify.model.ProductVariant;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
public class ProductOAuth2RestResourceTest {
  public static final String responseCountOk = "{\"count\": 17}";

  public static final String responseProductsOk = "{\n" +
          "  \"products\": [{\n" +
          "    \"id\": 632910392,\n" +
          "    \"title\": \"IPod Nano - 8GB\",\n" +
          "    \"body_html\": \"<p>It's the small iPod with one very big idea: Video...\",\n" +
          "    \"vendor\": \"Apple\",\n" +
          "    \"product_type\": \"Cult Products\",\n" +
          "    \"created_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "    \"handle\": \"ipod-nano\",\n" +
          "    \"updated_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "    \"published_at\": \"2007-12-31T19:00:00-05:00\",\n" +
          "    \"template_suffix\": null,\n" +
          "    \"published_scope\": \"web\",\n" +
          "    \"tags\": \"Emotive, Flash Memory, MP3, Music\",\n" +
          "    \"variants\": [\n" +
          "        {\n" +
          "          \"id\": 808950810,\n" +
          "          \"product_id\": 632910392,\n" +
          "          \"title\": \"Pink\",\n" +
          "          \"price\": \"199.00\",\n" +
          "          \"sku\": \"IPOD2008PINK\",\n" +
          "          \"position\": 1,\n" +
          "          \"grams\": 200,\n" +
          "          \"inventory_policy\": \"continue\",\n" +
          "          \"compare_at_price\": null,\n" +
          "          \"fulfillment_service\": \"manual\",\n" +
          "          \"inventory_management\": \"shopify\",\n" +
          "          \"option1\": \"Pink\",\n" +
          "          \"option2\": null,\n" +
          "          \"option3\": null,\n" +
          "          \"created_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"updated_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"requires_shipping\": true,\n" +
          "          \"taxable\": true,\n" +
          "          \"barcode\": \"1234_pink\",\n" +
          "          \"inventory_quantity\": 10,\n" +
          "          \"old_inventory_quantity\": 10,\n" +
          "          \"image_id\": 562641783,\n" +
          "          \"weight\": 0.2,\n" +
          "          \"weight_unit\": \"kg\"," +
          "          \"option1\":\"34\",\n" +
          "          \"option2\":\"Black\",\n" +
          "          \"option3\":null" +
          "        }\n" +
          "      ],\n" +
          "     \"images\": [\n" +
          "        {\n" +
          "          \"id\": 850703190,\n" +
          "          \"product_id\": 632910392,\n" +
          "          \"position\": 1,\n" +
          "          \"created_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"updated_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"src\": \"https:\\/\\/cdn.shopify.com\\/s\\/files\\/1\\/0006\\/9093\\/3842\\/products\\/ipod-nano.png?v=1458248091\",\n" +
          "          \"variant_ids\": [\n" +
          "          ]\n" +
          "        }\n" +
          "       ],\n" +
          "     \"image\": {\n" +
          "          \"id\": 850703190,\n" +
          "          \"product_id\": 632910392,\n" +
          "          \"position\": 1,\n" +
          "          \"created_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"updated_at\": \"2016-03-17T16:54:51-04:00\",\n" +
          "          \"src\": \"https:\\/\\/cdn.shopify.com\\/s\\/files\\/1\\/0006\\/9093\\/3842\\/products\\/ipod-nano.png?v=1458248091\",\n" +
          "          \"variant_ids\": [\n" +
          "          ]\n" +
          "       },\n" +
          "     \"options\": [\n" +
          "        {\n" +
          "          \"id\": 891236591,\n" +
          "          \"product_id\": 921728736,\n" +
          "          \"name\": \"Title\",\n" +
          "          \"position\": 1,\n" +
          "          \"values\": [\n" +
          "            \"Black\"\n" +
          "          ]\n" +
          "        }\n" +
          "     ]\n" +
          "  }, " +
          "  {}]\n" +
          "}";

  private ProductOAuth2RestResource target;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    target = new ProductOAuth2RestResource();

    RestTemplate restTemplate = (RestTemplate) target.getRestOperations();

    mockServer = createServer(restTemplate);
  }

  @Test
  public void shouldGetShopifyProductCount() {
    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products/count.json"))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseCountOk, APPLICATION_JSON));

    long count = target.getCount("my-access-token", "my-shop");

    mockServer.verify();

    assertThat(count).isEqualTo(17);
  }

  @Test
  public void shouldGetShopifyProductsPage() {
    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products/count.json"))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseCountOk, APPLICATION_JSON));

    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products.json?page=1&limit=2&fields="))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseProductsOk, APPLICATION_JSON));

    PageRequest pageRequest = new PageRequest(0, 2);

    Page<Product> result = target.getAll("my-access-token", "my-shop", pageRequest, new String[]{});

    mockServer.verify();

    assertThat(result).isNotNull();
    assertThat(result.getNumber()).isEqualTo(0);
    assertThat(result.getNumberOfElements()).isEqualTo(2);
    assertThat(result.getTotalPages()).isEqualTo(9);
    assertThat(result.getTotalElements()).isEqualTo(17);
    assertThat(result.hasNext()).isTrue();
    assertThat(result.hasPrevious()).isFalse();
    assertThat(result.getContent()).isNotNull();
    assertThat(result.getContent()).hasSize(2);
  }

  @Test
  public void shouldGetShopifyProductsWithProperties() {
    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products/count.json"))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseCountOk, APPLICATION_JSON));

    mockServer
            .expect(requestTo("https://my-shop.myshopify.com/admin/products.json?page=1&limit=2&fields="))
            .andExpect(method(GET))
            .andRespond(withSuccess(responseProductsOk, APPLICATION_JSON));

    PageRequest pageRequest = new PageRequest(0, 2);

    Page<Product> result = target.getAll("my-access-token", "my-shop", pageRequest, new String[]{});

    mockServer.verify();

    List<Product> products = result.getContent();
    Product product = products.get(0);

    assertThat(product).isNotNull();
    assertThat(product.getId()).isEqualTo(632910392);
    assertThat(product.getTitle()).isEqualTo("IPod Nano - 8GB");
    assertThat(product.getBodyHtml()).isEqualTo("<p>It's the small iPod with one very big idea: Video...");
    assertThat(product.getVendor()).isEqualTo("Apple");
    assertThat(product.getProductType()).isEqualTo("Cult Products");
    assertThat(product.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(product.getHandle()).isEqualTo("ipod-nano");
    assertThat(product.getUpdatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(product.getPublishedAt()).isEqualTo(new DateTime("2007-12-31T19:00:00-05:00"));
    assertThat(product.getTemplateSuffix()).isNull();
    assertThat(product.getMetafieldsGlobalTitleTag()).isNull();
    assertThat(product.getMetafieldsGlobalDescriptionTag()).isNull();
    assertThat(product.getTags()).isEqualTo("Emotive, Flash Memory, MP3, Music");
    assertThat(product.getVariants()).isNotNull().hasSize(1);

    ProductVariant variant = product.getVariants().get(0);

    assertThat(variant).isNotNull();
    assertThat(variant.getId()).isEqualTo(808950810);
    assertThat(variant.getProductId()).isEqualTo(632910392);
    assertThat(variant.getTitle()).isEqualTo("Pink");
    assertThat(variant.getPrice()).isEqualTo(199.00);
    assertThat(variant.getSku()).isEqualTo("IPOD2008PINK");
    assertThat(variant.getPosition()).isEqualTo(1);
    assertThat(variant.getGrams()).isEqualTo(200);
    assertThat(variant.getInventoryPolicy()).isEqualTo("continue");
    assertThat(variant.getCompareAtPrice()).isNull();
    assertThat(variant.getFulfillmentService()).isEqualTo("manual");
    assertThat(variant.getInventoryManagement()).isEqualTo("shopify");
    assertThat(variant.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(variant.getUpdatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(variant.getRequiresShipping()).isTrue();
    assertThat(variant.getTaxable()).isTrue();
    assertThat(variant.getBarcode()).isEqualTo("1234_pink");
    assertThat(variant.getInventoryQuantity()).isEqualTo(10);
    assertThat(variant.getOldInventoryQuantity()).isEqualTo(10);
    assertThat(variant.getImageId()).isEqualTo(562641783);
    assertThat(variant.getWeight()).isEqualTo(0.2);
    assertThat(variant.getWeightUnit()).isEqualTo("kg");
    assertThat(variant.getOption1()).isEqualTo("34");
    assertThat(variant.getOption2()).isEqualTo("Black");
    assertThat(variant.getOption3()).isNull();

    ProductImage image = product.getImages().get(0);

    assertThat(image).isNotNull();
    assertThat(image.getId()).isEqualTo(850703190);
    assertThat(image.getProductId()).isEqualTo(632910392);
    assertThat(image.getPosition()).isEqualTo(1);
    assertThat(image.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(image.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(image.getSrc()).isEqualTo("https://cdn.shopify.com/s/files/1/0006/9093/3842/products/ipod-nano.png?v=1458248091");
    assertThat(image.getVariants()).isNull();

    image = product.getImage();

    assertThat(image).isNotNull();
    assertThat(image.getId()).isEqualTo(850703190);
    assertThat(image.getProductId()).isEqualTo(632910392);
    assertThat(image.getPosition()).isEqualTo(1);
    assertThat(image.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(image.getCreatedAt()).isEqualTo(new DateTime("2016-03-17T16:54:51-04:00"));
    assertThat(image.getSrc()).isEqualTo("https://cdn.shopify.com/s/files/1/0006/9093/3842/products/ipod-nano.png?v=1458248091");
    assertThat(image.getVariants()).isNull();

    ProductOption option = product.getOptions().get(0);

    assertThat(option).isNotNull();
    assertThat(option.getId()).isEqualTo(891236591);
    assertThat(option.getProductId()).isEqualTo(921728736);
    assertThat(option.getName()).isEqualTo("Title");
    assertThat(option.getPosition()).isEqualTo(1);
    assertThat(option.getValues()).isNotNull().isNotEmpty().contains("Black", atIndex(0));
  }

}
