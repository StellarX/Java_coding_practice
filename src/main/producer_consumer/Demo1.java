import lombok.SneakyThrows;

/**
 * @briefing 使用 wait和notify、notifyAll方法 以及 synchronized同步代码块 实现生产者消费者模式 (2个生产者， 1个消费者)
 * @author space
 * @date 2020/10/6 13:09
 */


class Res1{
    int apple;
}

class Producer1 implements Runnable {
    private final Res1 r;

    Producer1(Res1 r) {
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (r) {
                while (r.apple == 1) r.wait(); // 使当前线程等待，并放弃锁 todo 这个r.wait() 和 Producer 以及 r到底是什么关系？
                r.apple++;
                System.out.println(Thread.currentThread().getName() + " apple ++  = " + r.apple);
                r.notifyAll();
            }
        }
    }
}

class Consumer1 implements Runnable{
    private final Res1 r;

    Consumer1(Res1 r){
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(true){
            synchronized (r){
                if(r.apple == 0) r.wait();
                r.apple--;
                System.out.println(Thread.currentThread().getName() +  " apple --  = " + r.apple);
                r.apple = 0;
                r.notify();
            }
        }
    }
}

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Res1 r = new Res1();
        new Thread(new Producer1(r)).start();
        new Thread(new Producer1(r)).start();
        new Thread(new Consumer1(r)).start();

    }
}
