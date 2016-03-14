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

    public interface Service {
        ShopifySession getSession();
    }

    public interface Resource {
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

        String classImplementationSimpleName =  serviceSimpleName + "WebServiceImpl";
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

        String classImplementationSimpleName =  serviceName + "OAuth2RestResource";
        String className = this.getClass().getPackage().getName() +
                ".web.resource.rest." +
                serviceName.toLowerCase() +
                "." +
                classImplementationSimpleName;
        Class clazz = Class.forName(className);

        return (RestResource) clazz.newInstance();
    }
}
