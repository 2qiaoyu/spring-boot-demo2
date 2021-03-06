package com.joham.demo.rabbitmq.listener;

import com.joham.demo.lazyqueues.config.LazyQueueDirectExchangeConfig;
import com.joham.demo.rabbitmq.exchange.DirectExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@Slf4j
public class DirectQueueListener {

    @RabbitListener(queues = LazyQueueDirectExchangeConfig.QUEUE)
    public void listenDirectQueue1(String message) {
        log.info("[{}] {}", LazyQueueDirectExchangeConfig.QUEUE, message);
    }

    @RabbitListener(queues = DirectExchangeConfig.QUEUE_2)
    public void listenDirectQueue2(String message) {
        log.info("[{}] {}", DirectExchangeConfig.QUEUE_2, message);
    }

}
