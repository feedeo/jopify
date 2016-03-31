/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

  private Long id;

  @JsonProperty("created_at")
  private DateTime createdAt;

  @JsonProperty("updated_at")
  private DateTime updatedAt;

  private String title;

  private String vendor;

  @JsonProperty("handle")
  private String handle;

  @JsonProperty("product_type")
  private String productType;

  @JsonProperty("body_html")
  private String bodyHtml;

  @JsonProperty("template_suffix")
  private String templateSuffix;

  @JsonProperty("published_at")
  private DateTime publishedAt;

  @JsonProperty("metafields_global_title_tag")
  private String metafieldsGlobalTitleTag;

  @JsonProperty("metafields_global_description_tag")
  private String metafieldsGlobalDescriptionTag;

  private String tags;

  private List<ProductImage> images;

  private ProductImage image;

  private List<ProductOption> options;

  private List<ProductVariant> variants;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getHandle() {
    return handle;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getBodyHtml() {
    return bodyHtml;
  }

  public void setBodyHtml(String bodyHtml) {
    this.bodyHtml = bodyHtml;
  }

  public String getTemplateSuffix() {
    return templateSuffix;
  }

  public void setTemplateSuffix(String templateSuffix) {
    this.templateSuffix = templateSuffix;
  }

  public DateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(DateTime publishedAt) {
    this.publishedAt = publishedAt;
  }

  public String getMetafieldsGlobalTitleTag() {
    return metafieldsGlobalTitleTag;
  }

  public void setMetafieldsGlobalTitleTag(String metafieldsGlobalTitleTag) {
    this.metafieldsGlobalTitleTag = metafieldsGlobalTitleTag;
  }

  public String getMetafieldsGlobalDescriptionTag() {
    return metafieldsGlobalDescriptionTag;
  }

  public void setMetafieldsGlobalDescriptionTag(String metafieldsGlobalDescriptionTag) {
    this.metafieldsGlobalDescriptionTag = metafieldsGlobalDescriptionTag;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public List<ProductImage> getImages() {
    return images;
  }

  public void setImages(List<ProductImage> images) {
    this.images = images;
  }

  public List<ProductOption> getOptions() {
    return options;
  }

  public void setOptions(List<ProductOption> options) {
    this.options = options;
  }

  public List<ProductVariant> getVariants() {
    return variants;
  }

  public void setVariants(List<ProductVariant> variants) {
    this.variants = variants;
  }

  public ProductImage getImage() {
    return image;
  }

  public void setImage(ProductImage image) {
    this.image = image;
  }
}
