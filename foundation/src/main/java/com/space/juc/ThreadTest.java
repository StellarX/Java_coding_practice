package com.space.juc;

/**
 * 线程的几种运行方式  todo
 */
public class ThreadTest{
    public static void main(String[] args) {
        new Thread(() -> {
            for(int x = 0; x < 100; x++)
                System.out.println(Thread.currentThread().getName() + "...." + x);
        }).start();

        Runnable r = () -> {
            for(int x = 0; x < 100; x++)
                System.out.println(Thread.currentThread().getName() + "...." + x);
        };
        new Thread(r).start();

        for(int x = 0; x < 100; x++)
            System.out.println(Thread.currentThread().getName() + "...." + x);
    }
}

