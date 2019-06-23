package com.joham.demo.priorityqueue.listener;

import com.joham.demo.priorityqueue.config.PriorityQueueDirectExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@Slf4j
public class PriorityQueueDirectQueueListener {

    /**
     * 监听优先级队列的消息
     */
    @RabbitListener(queues = PriorityQueueDirectExchangeConfig.QUEUE)
    public void listen(Message message) throws Exception {
        // 模拟业务处理
        Thread.sleep(1000);
        log.info("[priority] {} [content] {}", message.getMessageProperties().getPriority(), new String(message.getBody()));
    }

}
