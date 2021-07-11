package com.space.pojo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyData {
//        volatile public int number = 0;
    public int number = 0;
//    AtomicInteger a = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }
    public void addOne(){
        log.debug("this.number++");
        this.number++;
    }
}
