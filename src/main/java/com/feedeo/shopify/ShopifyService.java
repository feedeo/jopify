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

package com.feedeo.shopify;

import com.feedeo.shopify.service.web.WebService;
import com.feedeo.shopify.web.resource.WebResource;
import com.feedeo.shopify.web.resource.rest.RestResource;

import java.lang.reflect.InvocationTargetException;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

public class ShopifyService {

  public <T extends Service> T getService(ShopifySession session, Class<T> serviceClass) {
    checkNotNull(session);
    checkNotNull(serviceClass);

    Service service = null;
    try {
      service = instantiateWebServiceImplementation(session, serviceClass);
    } catch (Exception e) {
      propagate(e);
    }

    return (T) service;
  }

  private <T extends Service> Service instantiateWebServiceImplementation(ShopifySession session, Class<T> serviceClass)
          throws ClassNotFoundException, IllegalAccessException,
          InstantiationException, NoSuchMethodException, InvocationTargetException {

    String serviceSimpleName = serviceClass.getSimpleName().substring(0, serviceClass.getSimpleName().length() - "Service".length());

    RestResource resource = instantiateRestResource(serviceSimpleName);
    Class resourceClass = resource.getClass().getInterfaces()[0];
    WebService service = instantiateWebService(session, resource, resourceClass, serviceSimpleName);

    return service;
  }

  private WebService instantiateWebService(ShopifySession session, WebResource resource, Class resourceClass, String serviceSimpleName)
          throws ClassNotFoundException, IllegalAccessException,
          InstantiationException, NoSuchMethodException, InvocationTargetException {

    String classImplementationSimpleName = serviceSimpleName + "WebServiceImpl";
    String className = this.getClass().getPackage().getName() +
            ".service.web." +
            serviceSimpleName.toLowerCase() +
            "." +
            classImplementationSimpleName;
    Class clazz = Class.forName(className);

    return (WebService) clazz.getConstructor(ShopifySession.class, resourceClass).newInstance(session, resource);
  }

  private RestResource instantiateRestResource(String serviceName)
          throws ClassNotFoundException, IllegalAccessException,
          InstantiationException, NoSuchMethodException, InvocationTargetException {

    String classImplementationSimpleName = serviceName + "OAuth2RestResource";
    String className = this.getClass().getPackage().getName() +
            ".web.resource.rest." +
            serviceName.toLowerCase() +
            "." +
            classImplementationSimpleName;
    Class clazz = Class.forName(className);

    return (RestResource) clazz.newInstance();
  }

  public interface Service {
    ShopifySession getSession();
  }

  public interface Resource {
  }
}
