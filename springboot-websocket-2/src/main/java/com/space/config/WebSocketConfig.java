package com.space.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker //表示开启使用 STOMP 协议来传输基于代理的消息，Broker 就是代理的意思。
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 用来配置消息代理，由于我们是实现推送功能，这里的消息代理是 /topic
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");//设置接收前端消息的接口的前缀，也可以不用设置，但前端也要改一下
//        config.setUserDestinationPrefix("/user");
    }

    /**
     * 表示注册 STOMP 协议的节点，并指定映射的 URL
     * 注册一个端点为 /chat，要想订阅和发送消息，先要连接connect到这个端点
     * .setAllowedOrigins("*")解决前端跨域问题
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*");

        //用来注册 STOMP 协议节点，同时指定使用 SockJS 协议
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
    }
}