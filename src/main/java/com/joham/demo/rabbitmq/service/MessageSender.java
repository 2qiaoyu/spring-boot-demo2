package com.joham.demo.rabbitmq.service;

import com.joham.demo.rabbitmq.bean.MessageRequestBean;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author joham
 */
@Service
@AllArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 向 RabbitMQ 发送消息
     */
    public void sendMessage(MessageRequestBean messageRequestBean) {
        for (int i = 0; i < messageRequestBean.getCount(); i++) {
            String content = String.format("[%4d] %s", i, messageRequestBean.getContent());
            rabbitTemplate.convertAndSend(messageRequestBean.getExchange(), messageRequestBean.getRoutingKey(), content, message -> {
                Map<String, String> headers = messageRequestBean.getHeaders();
                if (headers != null && !headers.isEmpty()) {
                    headers.forEach((key, value) -> message.getMessageProperties().setHeader(key, value));
                }
                return message;
            });
        }
    }

}
