package space.juc;

import lombok.SneakyThrows;
import space.entity.Mydata;

/**
 * @Description 验证volatile的可见性、（是否保证）原子性
 * @Author space
 * @Date 2021/3/26
 */

public class Volatile {
    @SneakyThrows
    public static void main(String[] args) {
//        testVisibility();
        testAtomic();//volatile不保证原子性
    }

    private static void testAtomic() throws InterruptedException {
        Mydata mydata = new Mydata();
        for (int i = 0; i < 20; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    mydata.addOne();
            }, String.valueOf(i)).start();
        }
        Thread.sleep(11000);
//        while(Thread.activeCount() > 3){
//            Thread.yield();
//        }
        System.out.println(mydata.number);
    }

    private static void testVisibility() {
        Mydata mydata = new Mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                Thread.sleep(3000);
//                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated value: " + mydata.number);
        }, "aaa").start();

        while (mydata.number == 0){
//            System.out.println(Thread.activeCount());
//            System.out.println("test");//todo 这里为什么加上任意语句就能over了，说明不加volatile也能刷新主内存 参考周阳04节
//            System.out.println(Thread.currentThread().getName() + ": "+mydata.number);
        }
        ;

        System.out.println(Thread.currentThread().getName() + "\t over");
    }

}
