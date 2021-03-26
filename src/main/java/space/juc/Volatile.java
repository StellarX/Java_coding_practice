package space.juc;

import space.entity.Mydata;

/**
 * @Description 验证volatile的可见性
 * @Author space
 * @Date 2021/3/26
 */

public class Volatile {
    public static void main(String[] args) {
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
        };

        System.out.println(Thread.currentThread().getName() + "\t over");
    }

}
