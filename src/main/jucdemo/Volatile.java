package jucdemo;

class Mydata{
    volatile int number = 0;
//    int number = 0;
    public void addTo60(){
        this.number = 60;
    }
}

/**
 * 验证volatile的可见性
 * @author space
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
//            System.out.println(mydata.number); // todo 为什么加了这一句，main线程就可以打印出下面的over了呢？ 而不加的话就一直循环
        }; // 如果number没有被volatile修饰，则main线程读到的number一直是0

        System.out.println(Thread.currentThread().getName() + "\t over");
    }

}
