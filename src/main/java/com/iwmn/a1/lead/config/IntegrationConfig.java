package com.iwmn.a1.lead.config;

import com.iwmn.CycloneSession;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.xml.rpc.ServiceException;


@Profile("!mock")
@Configuration
@EnableConfigurationProperties
public class IntegrationConfig {

    @Bean
    @ConfigurationProperties(prefix = "cyclone")
    public EndpointSessionProperties cycloneSessionConfig() {
        return new EndpointSessionProperties();
    }

    @Bean
    public CycloneSession cycloneSession() throws ServiceException {
        EndpointSessionProperties config = cycloneSessionConfig();
        return new CycloneSession(
                config.getEndpoint(),
                config.getUsername(),
                config.getPassword());
    }

}
