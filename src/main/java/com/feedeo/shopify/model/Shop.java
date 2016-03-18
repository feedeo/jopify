/*
 * Copyright (C) 2016, Feedeo AB. All rights reserved.
 */

package com.feedeo.shopify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop {

  private String address1;

  private String city;

  private String country;

  @JsonProperty("country_code")
  private String countryCode;

  @JsonProperty("country_name")
  private String countryName;

  @JsonProperty("created_at")
  private DateTime createdAt;

  @JsonProperty("updated_at")
  private DateTime updatedAt;

  @JsonProperty("customer_email")
  private String customerEmail;

  private String currency;

  private String domain;

  private String email;

  @JsonProperty("google_apps_domain")
  private String googleAppsDomain;

  @JsonProperty("google_apps_login_enabled")
  private String googleAppsLoginEnabled;

  private Long id;

  private Double latitude;

  private Double longitude;

  @JsonProperty("money_format")
  private String moneyFormat;

  @JsonProperty("money_with_currency_format")
  private String moneyWithCurrencyFormat;

  @JsonProperty("myshopify_domain")
  private String myshopifyDomain;

  private String name;

  @JsonProperty("plan_name")
  private String planName;

  @JsonProperty("plan_display_name")
  private String planDisplayName;

  @JsonProperty("password_enabled")
  private Boolean passwordEnabled;

  private String phone;

  @JsonProperty("primary_locale")
  private String primaryLocale;

  @JsonProperty("primary_location_id")
  private Long primaryLocationId;

  private String province;

  @JsonProperty("province_code")
  private String provinceCode;

  @JsonProperty("shop_owner")
  private String shopOwner;

  private String source;

  @JsonProperty("tax_shipping")
  private String taxShipping;

  @JsonProperty("taxes_included")
  private Boolean taxesIncluded;

  @JsonProperty("county_taxes")
  private Boolean countyTaxes;

  private String timezone;

  @JsonProperty("iana_timezone")
  private String ianaTimezone;

  private String zip;

  @JsonProperty("has_storefront")
  private Boolean hasStorefront;

  @JsonProperty("setup_required")
  private Boolean setupRequired;

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
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

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGoogleAppsDomain() {
    return googleAppsDomain;
  }

  public void setGoogleAppsDomain(String googleAppsDomain) {
    this.googleAppsDomain = googleAppsDomain;
  }

  public String getGoogleAppsLoginEnabled() {
    return googleAppsLoginEnabled;
  }

  public void setGoogleAppsLoginEnabled(String googleAppsLoginEnabled) {
    this.googleAppsLoginEnabled = googleAppsLoginEnabled;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getMoneyFormat() {
    return moneyFormat;
  }

  public void setMoneyFormat(String moneyFormat) {
    this.moneyFormat = moneyFormat;
  }

  public String getMoneyWithCurrencyFormat() {
    return moneyWithCurrencyFormat;
  }

  public void setMoneyWithCurrencyFormat(String moneyWithCurrencyFormat) {
    this.moneyWithCurrencyFormat = moneyWithCurrencyFormat;
  }

  public String getMyshopifyDomain() {
    return myshopifyDomain;
  }

  public void setMyshopifyDomain(String myshopifyDomain) {
    this.myshopifyDomain = myshopifyDomain;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public String getPlanDisplayName() {
    return planDisplayName;
  }

  public void setPlanDisplayName(String planDisplayName) {
    this.planDisplayName = planDisplayName;
  }

  public Boolean getPasswordEnabled() {
    return passwordEnabled;
  }

  public void setPasswordEnabled(Boolean passwordEnabled) {
    this.passwordEnabled = passwordEnabled;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPrimaryLocale() {
    return primaryLocale;
  }

  public void setPrimaryLocale(String primaryLocale) {
    this.primaryLocale = primaryLocale;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(String provinceCode) {
    this.provinceCode = provinceCode;
  }

  public String getShopOwner() {
    return shopOwner;
  }

  public void setShopOwner(String shopOwner) {
    this.shopOwner = shopOwner;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTaxShipping() {
    return taxShipping;
  }

  public void setTaxShipping(String taxShipping) {
    this.taxShipping = taxShipping;
  }

  public Boolean isTaxesIncluded() {
    return taxesIncluded;
  }

  public void setTaxesIncluded(Boolean taxesIncluded) {
    this.taxesIncluded = taxesIncluded;
  }

  public Boolean isCountyTaxes() {
    return countyTaxes;
  }

  public void setCountyTaxes(Boolean countyTaxes) {
    this.countyTaxes = countyTaxes;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public String getIanaTimezone() {
    return ianaTimezone;
  }

  public void setIanaTimezone(String ianaTimezone) {
    this.ianaTimezone = ianaTimezone;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Boolean getHasStorefront() {
    return hasStorefront;
  }

  public void setHasStorefront(Boolean hasStorefront) {
    this.hasStorefront = hasStorefront;
  }

  public Boolean getSetupRequired() {
    return setupRequired;
  }

  public void setSetupRequired(Boolean setupRequired) {
    this.setupRequired = setupRequired;
  }

  public Long getPrimaryLocationId() {
    return primaryLocationId;
  }

  public void setPrimaryLocationId(Long primaryLocationId) {
    this.primaryLocationId = primaryLocationId;
  }
}
