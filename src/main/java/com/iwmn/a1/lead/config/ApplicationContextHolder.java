package com.iwmn.a1.lead.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Map;
import java.util.function.Function;

public class ApplicationContextHolder {

    private static ApplicationContext context;


    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("============= Initialized applicationContextHoder!");
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Environment getEnvironment() {
        return getContext().getEnvironment();
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        try {
            if (name == null || clazz == null) {
                return null;
            }
            return context.getBean(name, clazz);
        } catch (BeansException be) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (BeansException be) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz, Class... genericArguments) {
        try {
            Map<String, T> beans = context.getBeansOfType(clazz);
            return beans.get(0);
        } catch (BeansException be) {
            return null;
        }
    }


    public static <T> T lookup(Function<Context, T> doLookup) {
        Thread thread = Thread.currentThread();
        // Store the current classloader for later
        ClassLoader origLoader = thread.getContextClassLoader();
        try {
            InitialContext env = new InitialContext();
            thread.setContextClassLoader(com.liferay.portal.kernel.util.PortalClassLoaderUtil.getClassLoader());
            T result = doLookup.apply(env);
            if (result == null) {
                System.err.println("Can not obtain the jndi object in the initial context.");
                System.err.println("Trying to obtain the object from the 'java:comp/env/' context");
                Context context = (Context) env.lookup("java:comp/env/");
                return doLookup.apply(context);
            }
            return result;
        } catch (Exception e) {
            System.err.println("Outer catch");
            e.printStackTrace();
        } finally {
            thread.setContextClassLoader(origLoader);
        }
        return null;
    }

}
