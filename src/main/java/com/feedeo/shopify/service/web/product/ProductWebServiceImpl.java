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

package com.feedeo.shopify.service.web.product;

import com.feedeo.shopify.ShopifySession;
import com.feedeo.shopify.model.Product;
import com.feedeo.shopify.service.ProductService;
import com.feedeo.shopify.service.web.AbstractWebService;
import com.feedeo.shopify.web.resource.ProductWebResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProductWebServiceImpl extends AbstractWebService implements ProductService {
  public ProductWebServiceImpl(ShopifySession session, ProductWebResource resource) {
    super(session, resource);
  }

  @Override
  public long getCount() {
    ShopifySession session = this.acquireSession();
    ProductWebResource resource = (ProductWebResource) this.getResource();

    return resource.getCount(session.getoAuth2AccessToken(), session.getShopName());
  }

  @Override
  public Page<Product> getAll() {
    return _getAll(null, null, null);
  }

  @Override
  public Page<Product> getAll(final Pageable page) {
    checkNotNull(page);
    return _getAll(page.getPageNumber(), page.getPageSize(), null);
  }

  @Override
  public Page<Product> getAll(final Pageable page, final String[] select) {
    checkNotNull(page);
    return _getAll(page.getPageNumber(), page.getPageSize(), select);
  }

  private Page<Product> _getAll(final Integer page, final Integer size, final String[] select) {
    ShopifySession session = this.acquireSession();
    ProductWebResource resource = (ProductWebResource) this.getResource();

    Integer _page = page == null ? 0 : page;
    Integer _size = size == null ? 50 : size;
    String[] _select = select == null ? new String[]{} : select;

    Pageable pageable = new PageRequest(_page, _size);

    return resource.getAll(session.getoAuth2AccessToken(), session.getShopName(), pageable, _select);
  }
}
