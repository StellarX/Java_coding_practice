package com.space.component;

import com.space.config.DirectMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author space
 *
 */
@Component
public class Consumer {

    /**
     * 监听test1队列
     */
    @RabbitListener(queues = DirectMQConfig.QUEUE_TEST1)
    public void consumeMessage1(Message message) {
        System.out.println("这是监听test1得到的消息：======" + new String(message.getBody(), StandardCharsets.UTF_8));
    }

    /**
     * 监听test2队列
     */
    @RabbitListener(queues = DirectMQConfig.QUEUE_TEST2)
    public void consumeMessage2(Message message) {
        System.out.println("这是监听test2得到的消息：======" + new String(message.getBody(), StandardCharsets.UTF_8));
    }

}


