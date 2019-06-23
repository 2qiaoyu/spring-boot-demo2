package com.joham.demo.priorityqueue.service;

import com.joham.demo.priorityqueue.bean.PriorityQueueMessageRequestBean;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author joham
 */
@Service
@AllArgsConstructor
public class PriorityQueueMessageSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 向 RabbitMQ 发送消息
     */
    public void sendMessage(PriorityQueueMessageRequestBean priorityQueueMessageRequestBean) {
        for (int i = 0; i < priorityQueueMessageRequestBean.getCount(); i++) {
            String content = String.format("[%4d] %s", i, priorityQueueMessageRequestBean.getContent());
            rabbitTemplate.convertAndSend(priorityQueueMessageRequestBean.getExchange(), priorityQueueMessageRequestBean.getRoutingKey(), content, message -> {
                Integer priority = priorityQueueMessageRequestBean.getPriority();
                if (priority != null) {
                    message.getMessageProperties().setPriority(priority);
                }
                return message;
            });
        }
    }
}
