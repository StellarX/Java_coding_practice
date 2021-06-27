package jucdemo;

import enumeration.EnumDemo;
import java.util.concurrent.CountDownLatch;

/**
 * Countdownlatch��������ʾ
 * ���ã���һЩ�߳�����ֱ����һЩ�߳����һϵ�в�����ű�����
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
