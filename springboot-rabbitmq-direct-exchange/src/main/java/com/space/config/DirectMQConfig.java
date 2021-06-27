package com.space.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author space
 * @date 2021/6/27
 *
 */
@Configuration
public class DirectMQConfig {

    /**
     * 交换机名称
     */
    public static final String DIRECT_EXCHANGE_NAME = "my_direct_exchange";

    /**
     * 绑定key，交换机绑定队列时需要指定
     */
    public static final String BINDING_KEY_TEST1 = "direct_key1";
    public static final String BINDING_KEY_TEST2 = "direct_key2";

    /**
     * 队列名称
     */
    public static final String QUEUE_TEST1 = "test1";
    public static final String QUEUE_TEST2 = "test2";

    /**
     * 构建DirectExchange交换机
     */
    @Bean
    public DirectExchange directExchange() {
        // 支持持久化，长期不用补删除
        return new DirectExchange(DIRECT_EXCHANGE_NAME, true, false);
    }

    /**
     * 构建序列
     */
    @Bean
    public Queue test1Queue() {
        // 支持持久化
        return new Queue(QUEUE_TEST1, true);
    }

    @Bean
    public Queue test2Queue() {
        // 支持持久化
        return new Queue(QUEUE_TEST2, true);
    }

    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding test1Binding() {
        return BindingBuilder.bind(test1Queue()).to(directExchange()).with(BINDING_KEY_TEST1);
    }

    @Bean
    public Binding test2Binding() {
        return BindingBuilder.bind(test2Queue()).to(directExchange()).with(BINDING_KEY_TEST2);
    }

    /**
     * 配置
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    /**
     * 实例化操作模板
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}


