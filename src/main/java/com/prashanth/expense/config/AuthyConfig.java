//package com.prashanth.expense.config;
//
//import com.authy.AuthyApiClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AuthyConfig {
//
//    @Value("${authy.api.key}")
//    private String authyApiKey;
//
//    @Bean
//    public AuthyApiClient authyApiClient() {
//        return new AuthyApiClient(authyApiKey);
//    }
//}
