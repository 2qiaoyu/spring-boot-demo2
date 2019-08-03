package com.joham.demo.queuedelay.controller;

import com.joham.demo.queuedelay.bean.QueueDelayMessageRequestBean;
import com.joham.demo.queuedelay.service.QueueDelayMessageSender;
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
@RequestMapping("queuedelay")
public class QueueDelayMessageController {

    private final QueueDelayMessageSender messageSender;

    @PostMapping("/send")
    public String send(@RequestBody QueueDelayMessageRequestBean messageRequestBean){
        messageSender.sendMessage(messageRequestBean);
        return "success";
    }

}
