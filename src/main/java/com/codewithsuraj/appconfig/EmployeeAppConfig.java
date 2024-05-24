package com.codewithsuraj.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *This is the config class for configuring bean for employee
 */
@Configuration
public class EmployeeAppConfig {
    /**
     * this is the url configuration
     */

    @Value("${addressService.base.url}")
    private String addressBaseURL;

    /**
     * this is model mapper bean configuration
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.
                builder().
                baseUrl(addressBaseURL).
                build();
    }

    /**
     * this is the restTemplate bean configuration
     *   @Bean
     *     public RestTemplate restTemplate() {
     *
     *         return new RestTemplate();
     *     }
     */
}
