package com.joham.demo.xml;

import com.joham.demo.annotation.MyLog;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
public class UserXmlController {

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @MyLog
    public UserXml create(@RequestBody UserXml user) {
        user.setName("aa : " + user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }
}
