package com.joham.demo.queuedelaytwo.controller;

import com.joham.demo.queuedelaytwo.bean.QueueDelayTwoMessageRequestBean;
import com.joham.demo.queuedelaytwo.service.QueueDelayTwoMessageSender;
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
@RequestMapping("queuedelaytwo")
public class QueueDelayTwoMessageController {

    private final QueueDelayTwoMessageSender queueDelayTwoMessageSender;

    @PostMapping("/send")
    public String send(@RequestBody QueueDelayTwoMessageRequestBean messageRequestBean) {
        queueDelayTwoMessageSender.sendMessage(messageRequestBean);
        return "success";
    }

}
