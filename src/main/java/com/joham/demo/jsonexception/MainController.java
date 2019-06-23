package com.joham.demo.jsonexception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author joham
 */
@Controller
public class MainController {

    @GetMapping("/")
    public void index() throws CustomException {
        throw new CustomException();
    }

}