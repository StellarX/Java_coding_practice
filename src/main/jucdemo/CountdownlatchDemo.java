package jucdemo;

import enumeration.EnumDemo;
import java.util.concurrent.CountDownLatch;

/**
 * Countdownlatch的作用演示
 * 作用：让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒
 * @author space
 * @date 2020/10/3
 */
public class CountdownlatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for(int i = 1; i <= 3; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " left...");
                countDownLatch.countDown();
            }, EnumDemo.foreach_EnumDemo(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " over...");
    }
}
