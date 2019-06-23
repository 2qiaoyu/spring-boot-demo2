package com.joham.demo.priorityqueue.controller;

import com.joham.demo.priorityqueue.bean.PriorityQueueMessageRequestBean;
import com.joham.demo.priorityqueue.service.PriorityQueueMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitmq 优先级队列
 * @author joham
 */
@RestController
@AllArgsConstructor
@RequestMapping("priorityqueue")
public class PriorityQueueMessageController {

    private final PriorityQueueMessageSender priorityQueueMessageSender;

    @PostMapping("/send")
    public String send(@RequestBody PriorityQueueMessageRequestBean priorityQueueMessageRequestBean){
        priorityQueueMessageSender.sendMessage(priorityQueueMessageRequestBean);
        return "success";
    }

}
