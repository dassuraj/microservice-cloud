package com.codewithsuraj.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class EmployeeAppConfig {
//    @Bean
//    public RestTemplate restTemplate(){
//
//        return new RestTemplate();
//    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



    //    @Autowired
//    private RestTemplate restTemplate;
//    @GetMapping(value = "/employee")
//    public String getEmployee(){
//        String address =restTemplate.getForObject("http://localhost:8081/address",String.class);
//        return "Name:->Suraj::gmail:->suraj@gmail.com::address->"+address;
//    }
}
