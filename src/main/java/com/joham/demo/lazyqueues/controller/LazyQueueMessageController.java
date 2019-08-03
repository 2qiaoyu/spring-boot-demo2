package com.joham.demo.lazyqueues.controller;

import com.joham.demo.lazyqueues.bean.LazyQueueMessageRequestBean;
import com.joham.demo.lazyqueues.service.LazyQueueMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RequestMapping("lazyqueue")
@RestController
@AllArgsConstructor
public class LazyQueueMessageController {

    private final LazyQueueMessageSender messageSender;

    @PostMapping("/send")
    public String send(@RequestBody LazyQueueMessageRequestBean messageRequestBean){
        messageSender.sendMessage(messageRequestBean);
        return "success";
    }

}
