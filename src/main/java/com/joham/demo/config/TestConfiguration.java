package com.joham.demo.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
public class TestConfiguration {

    @GetMapping("testConfiguration")
    public String testConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration();
        return messageConfiguration.message();
    }
}
