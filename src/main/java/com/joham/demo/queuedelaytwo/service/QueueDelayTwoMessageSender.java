package com.joham.demo.queuedelaytwo.service;

import com.alibaba.fastjson.JSON;
import com.joham.demo.queuedelaytwo.bean.QueueDelayTwoMessageRequestBean;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author joham
 */
@Service
@AllArgsConstructor
public class QueueDelayTwoMessageSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 向 RabbitMQ 发送消息
     */
    public void sendMessage(QueueDelayTwoMessageRequestBean messageRequestBean) {
        for (int i = 0; i < messageRequestBean.getCount(); i++) {
            String content = String.format("[%4d] %s", i, messageRequestBean.getContent());
            rabbitTemplate.convertAndSend(messageRequestBean.getExchange(), messageRequestBean.getRoutingKey(), content, message -> {
                Integer delayTime = messageRequestBean.getDelayTime();
                if (delayTime != null) {
                    message.getMessageProperties().setDelay(delayTime);
                }
                return message;
            });
        }
    }

}
