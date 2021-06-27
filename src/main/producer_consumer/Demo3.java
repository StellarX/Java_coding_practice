import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @briefing ʹ����������ʵ�������������ߣ�ÿ����һ����������һ����flagΪtrue��һֱ��ȥ��false��ֹͣ
 * @author space
 * @date 2020/10/7 20:45
 */
class Res3{
    public volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue = null;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public Res3(BlockingQueue<String> blockingQueue){ // ע��������ô��ӿ�
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
        System.out.println("main�߳̽�ͣ...");
        r.flag = false;
    }
}
