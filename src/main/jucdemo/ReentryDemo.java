package jucdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入性演示
 * @author : space
 * @date :2020/10/2 17:20
 */

class Res{

    public synchronized void fun1(){
        System.out.println(Thread.currentThread().getName() + "doing 111");
        fun2();
    }
    public synchronized void fun2(){
        System.out.println(Thread.currentThread().getName() + "doing 222");
    }
}

class Res2{
    Lock lock = new ReentrantLock();
    public void fun1(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "doing 333");
            fun2();
        } finally {
            lock.unlock();
        }
    }
    public void fun2(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "doing 444");
        }finally {
            lock.unlock();
        }
    }

}

public class ReentryDemo {
    public static void main(String[] args) {
        //1. 先用synchronized 演示可重入性
        Res r = new Res();

        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                r.fun1();
            }, String.valueOf(i)).start(); // java.util.ConcurrentModificationException

            new Thread(() -> {
                r.fun2();
            }, String.valueOf(i + 5)).start();
        }
        //todo 从运行结果可以得出：同一个线程执行111后肯定继续执行222，那这说明它在获取fun1的锁时会同时去获取内部方法的锁（）？？？
        //如果内部方法获取不到锁，那么fun1也执行不了？？？

        //wait for 3s to let threads finish
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");

        //2. 用reentrylock 演示可重入性

        Res2 r2 = new Res2();
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                r2.fun1();
            }, String.valueOf(i)).start();
        }

    }
}
