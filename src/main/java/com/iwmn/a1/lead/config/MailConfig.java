package com.iwmn.a1.lead.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.mail.Session;
import javax.naming.NamingException;

import static com.iwmn.a1.lead.config.ApplicationContextHolder.lookup;

@Configuration
public class MailConfig {


    @Bean
    public Session mailSession() {
        return lookup(context -> {
            try {
                return (Session) context.lookup("mail/MailSession");
            } catch (NamingException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return null;
            }
        });
    }
}
