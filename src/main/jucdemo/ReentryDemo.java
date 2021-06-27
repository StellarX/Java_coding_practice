package jucdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����������ʾ
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
        //1. ����synchronized ��ʾ��������
        Res r = new Res();

        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                r.fun1();
            }, String.valueOf(i)).start(); // java.util.ConcurrentModificationException

            new Thread(() -> {
                r.fun2();
            }, String.valueOf(i + 5)).start();
        }
        //todo �����н�����Եó���ͬһ���߳�ִ��111��϶�����ִ��222������˵�����ڻ�ȡfun1����ʱ��ͬʱȥ��ȡ�ڲ�������������������
        //����ڲ�������ȡ����������ôfun1Ҳִ�в��ˣ�����

        //wait for 3s to let threads finish
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");

        //2. ��reentrylock ��ʾ��������

        Res2 r2 = new Res2();
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                r2.fun1();
            }, String.valueOf(i)).start();
        }

    }
}
