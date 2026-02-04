package com.iwmn.a1.lead.config;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    public static final String PACKAGE_TO_SCAN_PARAM = "packageToScan";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("----------- Initializing context");
        ServletContext servletContext = sce.getServletContext();
        String packageToScan = servletContext.getInitParameter(PACKAGE_TO_SCAN_PARAM);
        if (packageToScan == null) {
            throw new IllegalArgumentException(
                    "The servlet context parameter " + PACKAGE_TO_SCAN_PARAM + " is not configured");
        }

        ApplicationContextBuilder.create(packageToScan);
        int a = 1 + 1;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
