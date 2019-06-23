package com.joham.demo.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
@Slf4j
public class TestPropertyController {

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private HomeProperties homeProperties;

    @RequestMapping("testProperty")
    public String testProperty() {
        return userProperties.toString() + homeProperties.toString();
    }
}
