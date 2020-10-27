import lombok.SneakyThrows;

import java.nio.channels.AsynchronousFileChannel;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @briefing 使用JDK1.5提供的lock实现（多个）生产者（多个）消费者案例
 * @author space
 * @date 2020/10/6 18:38
 */

// todo 将加减操作封装到Res2内部
class Res2{
    protected int apple = 0;
    protected final Lock lock = new ReentrantLock();
    protected final Condition condition_pro = lock.newCondition(); // 将生产者和消费者阻塞到不同的condition
    protected final Condition condition_con = lock.newCondition();
}

class Producer2 implements Runnable{
    private final Res2 r;

    Producer2(Res2 r){
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            r.lock.lock();
            while (r.apple == 1) r.condition_pro.await(); // await方法也会释放锁
            r.apple = 1;
            System.out.println(Thread.currentThread().getName() + " apple ++ " + r.apple);
            r.condition_con.signalAll();
            r.lock.unlock();
        }
    }
}

class Consumer2 implements Runnable{
    private final Res2 r;

    Consumer2(Res2 r){
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            r.lock.lock();
            while(r.apple == 0) r.condition_con.await();
            r.apple--;
            System.out.println(Thread.currentThread().getName() + " apple -- " + r.apple);
            r.condition_pro.signalAll();
            r.lock.unlock();
        }
    }
}

public class Demo2 {

    public static void main(String[] args) {
        Res2 r = new Res2();
        new Thread(new Producer2(r)).start();
        new Thread(new Producer2(r)).start();
        new Thread(new Producer2(r)).start();

        new Thread(new Consumer2(r)).start();
        new Thread(new Consumer2(r)).start();
        new Thread(new Consumer2(r)).start();
        new Thread(new Consumer2(r)).start();
    }

}
