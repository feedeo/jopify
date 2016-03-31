/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImage extends Product {
  private String src;

  private Long position;

  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("variant_ids")
  private List<Long> variantIds;

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public Long getPosition() {
    return position;
  }

  public void setPosition(Long position) {
    this.position = position;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public List<Long> getVariantIds() {
    return variantIds;
  }

  public void setVariantIds(List<Long> variantIds) {
    this.variantIds = variantIds;
  }
}
