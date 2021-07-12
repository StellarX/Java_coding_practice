package com.space.entity;

public class MyData {
//        volatile public int number = 0;
    public int number = 0;
//    AtomicInteger a = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }
    public void addOne(){
        this.number++;
    }
}
