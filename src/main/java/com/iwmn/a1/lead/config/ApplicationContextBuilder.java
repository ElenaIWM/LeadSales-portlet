package com.iwmn.a1.lead.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationContextBuilder {

    public static ApplicationContext create(String packageToScan) {
        return create(new String[]{packageToScan}, null);
    }

    public static ApplicationContext create(String[] packagesToScan, String activeProfile) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        ConfigurableEnvironment env = context.getEnvironment();
        addPropertySource(env, "application.properties");
        if (activeProfile == null) {
            activeProfile = env.getProperty("spring.profiles.active");
        }
        System.out.println("spring.profiles.active=" + activeProfile);
        String[] profiles = activeProfile.split(",");
        env.setActiveProfiles(profiles);
        for (String profile : profiles) {
            addPropertySource(env, "application-" + profile + ".properties");
        }
        printSources(env);
        for (String packageToScan : packagesToScan) {
            context.scan(packageToScan);
        }
        context.refresh();
        ApplicationContextHolder.setApplicationContext(context);
        return context;
    }


    public static void addPropertySource(ConfigurableEnvironment env, String source) {
        try {
            InputStream is = new ClassPathResource(source).getInputStream();
            Properties properties = new Properties();
            properties.load(is);
            env.getPropertySources().addBefore("systemProperties", new PropertiesPropertySource(source, properties));
        } catch (FileNotFoundException e) {
            System.err.println("Can not find class path source: " + source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printSources(ConfigurableEnvironment env) {
        System.out.println("---- property sources ----");
        for (PropertySource<?> propertySource : env.getPropertySources()) {
            System.out.println("name =  " + propertySource.getName() + "\nsource = "
                    + propertySource.getSource().getClass() + "\n");
        }

    }
}
