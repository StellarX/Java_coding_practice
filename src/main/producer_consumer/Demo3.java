import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @briefing 使用阻塞队列实现生产者消费者，每生成一个马上消费一个，flag为true就一直下去，false则停止
 * @author space
 * @date 2020/10/7 20:45
 */
class Res3{
    public volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue = null;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public Res3(BlockingQueue<String> blockingQueue){ // 注意这里最好传接口
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void Produce() throws InterruptedException {
        String data = null;
        Boolean ret;
        System.out.println("start producer...");
        while(flag){
            data = atomicInteger.incrementAndGet() + "";
            ret = blockingQueue.offer(data);
            if(ret)
                System.out.println(Thread.currentThread().getName() + "\t" + " offer data" + data);
            else
                System.out.println(Thread.currentThread().getName() + "\t" + " offer data failed!!!");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("flag == false, so stop produce...");
    }

    public void Consumer() throws InterruptedException {
        String data = null;
        System.out.println("start consumer...");
        while(flag){
            data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(null == data || data.equalsIgnoreCase("")){
                System.out.println(Thread.currentThread().getName() + "\t" + "poll data failed!!!");
                flag = false;
                return;
            }
            else
                System.out.println(Thread.currentThread().getName() + "\t" + "poll data " + data);
        }
    }
}

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Res3 r = new Res3(new LinkedBlockingQueue<>(3));
        new Thread(() -> {
            try {
                r.Produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();
        new Thread(() -> {
            try {
                r.Consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println("main线程叫停...");
        r.flag = false;
    }
}
