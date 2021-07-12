package com.space.blockqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @briefing synchronous block queue demo
 * @author com.space
 * @date 2020/10/5 22:33
 */
public class SynchronousBlockQueueDemo {
    public static SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    public static void main(String[] args) {
        testBlock();

    }

    /**
     * test demo
     * ÿ�������������ȵ���һ���̵߳����Ƴ�����������������һֱ��������״̬
     * ע�����ʹ��put/take����
     */
    public static void testBlock(){
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " add 1");
            try {
                synchronousQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " add 2");
            try {
                synchronousQueue.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " add 3");
            try {
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000); // aaa �̱߳���ȴ���2s�� bbb�߳�take
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }

}
