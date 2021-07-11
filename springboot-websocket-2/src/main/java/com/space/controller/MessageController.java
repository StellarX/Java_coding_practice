package com.space.controller;

import com.space.pojo.Message;
import com.space.pojo.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Controller
public class MessageController {

    /**
     * @Description 此接口充当了两个角色，一个是接收前端消息，一个是广播接收的消息到所有在线用户
     */
    //前端的send方法会向此接口发送消息
    @MessageMapping("/chatting")
    //表示当服务器有消息需要推送的时候，会对订阅了 @SendTo 中路径的浏览器发送消息。
    @SendTo("/topic/messages") //广播消息到前端监听/topic/message的地方
    public OutputMessage send(Message message) {
        log.info("message:{}", message.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);//注意是广播
    }
}
