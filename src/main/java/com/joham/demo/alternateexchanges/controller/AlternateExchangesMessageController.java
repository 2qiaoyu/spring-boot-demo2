package com.joham.demo.alternateexchanges.controller;

import com.joham.demo.alternateexchanges.bean.AlternateExchangesMessageRequestBean;
import com.joham.demo.alternateexchanges.service.AlternateExchangesMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
@AllArgsConstructor
@RequestMapping("alternateexchanges")
public class AlternateExchangesMessageController {

    private final AlternateExchangesMessageSender messageSender;

    @PostMapping("/send")
    public String send(@RequestBody AlternateExchangesMessageRequestBean messageRequestBean) {
        messageSender.sendMessage(messageRequestBean);
        return "success";
    }

}
