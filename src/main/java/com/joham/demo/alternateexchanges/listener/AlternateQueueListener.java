package com.joham.demo.alternateexchanges.listener;

import com.joham.demo.alternateexchanges.config.AlternateDirectExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@Slf4j
public class AlternateQueueListener {

    /**
     * 监听备用交换器绑定的队列的消息
     */
    @RabbitListener(queues = AlternateDirectExchangeConfig.QUEUE_ALTERNATE)
    public void listen(String message) {
        log.info("[{}] {}", AlternateDirectExchangeConfig.QUEUE_ALTERNATE, message);
    }

}
