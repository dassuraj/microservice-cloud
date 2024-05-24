package com.codewithsuraj.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeAppConfig {
    //    @Bean
//    public RestTemplate restTemplate() {
//
//        return new RestTemplate();
//    }
    @Value("${addressService.base.url}")
    private String addressBaseURL;

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
//     @Autowired
//     private RestTemplate restTemplate;
//
//    @GetMapping(value = "/employee")
//    public String getEmployee() {
//        String address = restTemplate.getForObject("http://localhost:8081/address", String.class);
//        return "Name:->Suraj::gmail:->suraj@gmail.com::address->" + address;
//    }
}
