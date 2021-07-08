package com.space.controller;

import com.space.config.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Slf4j
@RestController
public class TestController {
    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{toUserId}/{message}")
    public ResponseEntity<String> pushToWeb(@PathVariable("message") String message,
                                            @PathVariable("toUserId") String toUserId) throws IOException {
        log.debug("toUserId:{}", toUserId);
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
