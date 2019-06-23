package com.joham.demo.rabbitmq.controller;

import com.joham.demo.rabbitmq.bean.MessageRequestBean;
import com.joham.demo.rabbitmq.service.MessageSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
@AllArgsConstructor
public class MessageController {

    private final MessageSender messageSender;

    @PostMapping("/send")
    public String send(@RequestBody MessageRequestBean messageRequestBean) {
        messageSender.sendMessage(messageRequestBean);
        return "success";
    }

}
