package com.space.entity;

public class Ticket extends Thread{
    private static int tick = 100;
    public void run(){
        while (true){
            if (tick > 0){
                tick--;
                System.out.println(currentThread().getName() + " tick:" + tick);
            }
        }
    }
}
