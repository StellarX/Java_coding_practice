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

    @MessageMapping("/chatting")
    @SendTo("/topic/messages") //@SendTo()广播消息到前端监听/topic/message的地方
    public OutputMessage send(Message message) {
        log.info("message:{}", message.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
