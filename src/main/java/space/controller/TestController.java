package space.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test1")
    public void test1(@RequestHeader String requestHeader){
        log.info("API: /test1");
        System.out.println(requestHeader);
    }
}
