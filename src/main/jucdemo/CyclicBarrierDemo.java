package jucdemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierDemo
 * @author : space
 * @date : 2020/10/4 10:33
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("****summon the demon");
        }); // second parameter means what to do when all threads reach the barrier

        for(int i = 0; i < 7; i++){
            new Thread(() -> {
                System.out.println("get");
                try {
                    cyclicBarrier.await(); // block the threads
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("gogogo");
            }, String.valueOf(i)).start();
        }
    }
}
