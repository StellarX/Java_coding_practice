package space.juc;

import space.entity.Ticket;
import space.entity.Ticket2;

/**
 * @Date 2021/3/15 17:43
 * @Description sell ticket using multi-thread.
 * two implements:
 *  1. using common variable
 *  2. using runnable interface
 */
public class MultiThreadDemo {
    public static void main(String[] args) {
//        test1();//共享变量
        test2();//Runnable
    }
    public static void test1(){
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();
        ticket1.start();
        ticket2.start();
        ticket3.start();
        ticket4.start();
    }
    public static void test2(){
        Ticket2 ticket2 = new Ticket2();
        Thread t1 = new Thread(ticket2);
        Thread t2 = new Thread(ticket2);
        t1.start();
        t2.start();
    }
}
