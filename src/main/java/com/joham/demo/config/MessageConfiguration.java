package com.joham.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author joham
 */
@Configuration
public class MessageConfiguration {

    @Bean
    public String message() {
        return "message configuration";
    }
}
