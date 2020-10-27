import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @briefing 练习lock、condition的使用
 * 多线程之间顺序调用，实现A、B、C三个线程启动，A打印5次，B打印10次，C打印15次，然后循环如此
 * @author space
 * @date 2020/10/6 22:45
 */
class PrintWord{
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int flag = 1;

    public void print5() {
//        lock.lock(); // todo 不加锁为什么会抛java.lang.IllegalMonitorStateException
        while(flag != 1) {
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) System.out.println(Thread.currentThread().getName() + "\t" + i);
        flag = 2;
        c2.signal();
//        lock.unlock();
    }
    @SneakyThrows
    public void print10(){
//        lock.lock();
        while(flag != 2) { c2.await();}
        for (int i = 0; i < 10; i++) System.out.println(Thread.currentThread().getName() + "\t" + i);
        flag = 3;
        c3.signal();
//        lock.unlock();
    }
    @SneakyThrows
    public void print15() {
//        lock.lock(); //
        while(flag != 3) { c3.await();}
        for (int i = 0; i < 15; i++) System.out.println(Thread.currentThread().getName() + "\t" + i);
        flag = 1;
        c1.signal();
//        lock.unlock();
    }
}

public class Practice1 {

    public static void main(String[] args) {
        PrintWord printWord = new PrintWord();
        new Thread(() -> {
            for(int i = 0; i < 10; i++){
                printWord.print5();
            }
        }, "aaa").start();

        new Thread(() -> {
            for(int i = 0; i < 10; i++){
                printWord.print10();
            }
        }, "bbb").start();

        new Thread(() -> {
            for(int i = 0; i < 10; i++){
                printWord.print15();
            }
        }, "ccc").start();
    }

}
