package space.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Mydata {
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
