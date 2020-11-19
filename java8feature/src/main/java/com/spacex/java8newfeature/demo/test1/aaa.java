package com.spacex.java8newfeature.demo.test1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class aaa {

    @RequestMapping("/test1")
    public static void main(String[] args) {
        System.out.println("hello");
    }

    @RequestMapping("/ooo")
    public String fun1(){
        return "whtat";
    }
}
