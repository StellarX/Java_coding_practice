package com.space.controller;

import com.space.config.DirectMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author space
 */
@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给test1队列发消息
     */
    @GetMapping("/sendMessage1")
    public Object sendMessage1() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        Message message = new Message("你好,这是发给test1队列的消息".getBytes(), messageProperties);
        rabbitTemplate.send(DirectMQConfig.DIRECT_EXCHANGE_NAME, DirectMQConfig.BINDING_KEY_TEST1, message);
        return "ok";
    }

    /**
     * 给test2队列发消息
     */
    @GetMapping("/sendMessage2")
    public Object sendMessage2() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        Message message = new Message("你好,这是发给test2队列的消息".getBytes(), messageProperties);
        rabbitTemplate.send(DirectMQConfig.DIRECT_EXCHANGE_NAME, DirectMQConfig.BINDING_KEY_TEST2, message);
        return "ok";
    }

}


