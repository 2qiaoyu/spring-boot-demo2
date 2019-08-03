package com.joham.demo.alternateexchanges.service;

import com.joham.demo.alternateexchanges.bean.AlternateExchangesMessageRequestBean;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author joham
 */
@Service
@AllArgsConstructor
public class AlternateExchangesMessageSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 向 RabbitMQ 发送消息
     */
    public void sendMessage(AlternateExchangesMessageRequestBean messageRequestBean) {
        for (int i = 0; i < messageRequestBean.getCount(); i++) {
            String content = String.format("[%4d] %s", i, messageRequestBean.getContent());
            rabbitTemplate.convertAndSend(messageRequestBean.getExchange(), messageRequestBean.getRoutingKey(), content);
        }
    }

}
