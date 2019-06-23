package com.joham.demo.rabbitmq.listener;

import com.joham.demo.rabbitmq.exchange.FanoutExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@Slf4j
public class FanoutQueueListener {

    @RabbitListener(queues = FanoutExchangeConfig.QUEUE_1)
    public void listenFanoutQueue1(String message) {
        log.info("[{}] {}", FanoutExchangeConfig.QUEUE_1, message);
    }

    @RabbitListener(queues = FanoutExchangeConfig.QUEUE_2)
    public void listenFanoutQueue2(String message) {
        log.info("[{}] {}", FanoutExchangeConfig.QUEUE_2, message);
    }

}
