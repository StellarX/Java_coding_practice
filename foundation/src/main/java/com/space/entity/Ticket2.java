package com.space.entity;

public class Ticket2 implements Runnable{
    private int tick = 100;
    @Override
    public void run() {
        while (true){
            if (tick > 0){
                System.out.println(Thread.currentThread().getName() + " tick:" + --tick);
            }
        }
    }
}
