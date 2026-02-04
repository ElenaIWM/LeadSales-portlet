package com.iwmn.a1.lead.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

import static com.iwmn.a1.lead.config.ApplicationContextHolder.lookup;


@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.iwmn.a1.lead.repository")
public class JpaConfig {

    @Autowired
    private Environment environment;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("com.iwmn.a1.lead.model");
        lfb.setJpaProperties(hibernateProps());
        return lfb;
    }

    Properties hibernateProps() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.show_sql", environment.getProperty("jpa.hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("jpa.hibernate.dialect"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("jpa.hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(destroyMethod = "")
    public DataSource dataSource() {
        return lookup(context -> {
            try {
                return (DataSource) context.lookup("jdbc/PortletPool");
            } catch (NamingException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return null;
            }
        });
    }


}
