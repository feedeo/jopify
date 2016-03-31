/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductVariant extends Product {
  private String barcode;

  @JsonProperty("compare_at_price")
  private String compareAtPrice;

  @JsonProperty("fulfillment_service")
  private String fulfillmentService;

  private Long grams;

  private double weight;

  @JsonProperty("weight_unit")
  private String weightUnit;

  @JsonProperty("inventory_management")
  private String inventoryManagement;

  @JsonProperty("inventory_policy")
  private String inventoryPolicy;

  @JsonProperty("inventory_quantity")
  private Long inventoryQuantity;

  private Long position;

  private Double price;

  @JsonProperty("product_id")
  private Long productId;

  private String sku;

  private Boolean taxable;

  @JsonProperty("image_id")
  private Long imageId;

  @JsonProperty("old_inventory_quantity")
  private Long oldInventoryQuantity;

  @JsonProperty("requires_shipping")
  private Boolean requiresShipping;

  private String option1;

  private String option2;

  private String option3;

  private String option4;

  private String option5;

  private String option6;

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getCompareAtPrice() {
    return compareAtPrice;
  }

  public void setCompareAtPrice(String compareAtPrice) {
    this.compareAtPrice = compareAtPrice;
  }

  public String getFulfillmentService() {
    return fulfillmentService;
  }

  public void setFulfillmentService(String fulfillmentService) {
    this.fulfillmentService = fulfillmentService;
  }

  public Long getGrams() {
    return grams;
  }

  public void setGrams(Long grams) {
    this.grams = grams;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getWeightUnit() {
    return weightUnit;
  }

  public void setWeightUnit(String weightUnit) {
    this.weightUnit = weightUnit;
  }

  public String getInventoryManagement() {
    return inventoryManagement;
  }

  public void setInventoryManagement(String inventoryManagement) {
    this.inventoryManagement = inventoryManagement;
  }

  public String getInventoryPolicy() {
    return inventoryPolicy;
  }

  public void setInventoryPolicy(String inventoryPolicy) {
    this.inventoryPolicy = inventoryPolicy;
  }

  public Long getInventoryQuantity() {
    return inventoryQuantity;
  }

  public void setInventoryQuantity(Long inventoryQuantity) {
    this.inventoryQuantity = inventoryQuantity;
  }

  public Long getPosition() {
    return position;
  }

  public void setPosition(Long position) {
    this.position = position;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public Boolean getTaxable() {
    return taxable;
  }

  public void setTaxable(Boolean taxable) {
    this.taxable = taxable;
  }

  public Long getImageId() {
    return imageId;
  }

  public void setImageId(Long imageId) {
    this.imageId = imageId;
  }

  public Long getOldInventoryQuantity() {
    return oldInventoryQuantity;
  }

  public void setOldInventoryQuantity(Long oldInventoryQuantity) {
    this.oldInventoryQuantity = oldInventoryQuantity;
  }

  public Boolean getRequiresShipping() {
    return requiresShipping;
  }

  public void setRequiresShipping(Boolean requiresShipping) {
    this.requiresShipping = requiresShipping;
  }

  public String getOption1() {
    return option1;
  }

  public void setOption1(String option1) {
    this.option1 = option1;
  }

  public String getOption2() {
    return option2;
  }

  public void setOption2(String option2) {
    this.option2 = option2;
  }

  public String getOption3() {
    return option3;
  }

  public void setOption3(String option3) {
    this.option3 = option3;
  }

  public String getOption4() {
    return option4;
  }

  public void setOption4(String option4) {
    this.option4 = option4;
  }

  public String getOption5() {
    return option5;
  }

  public void setOption5(String option5) {
    this.option5 = option5;
  }

  public String getOption6() {
    return option6;
  }

  public void setOption6(String option6) {
    this.option6 = option6;
  }
}
