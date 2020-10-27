import java.util.TreeMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @briefing synchronous block queue demo
 * @author space
 * @date 2020/10/5 22:33
 */
public class SynchronousBlockQueueDemo {
    public static SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();
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
            System.out.println("add 1");
            try {
                synchronousQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("add 2");
            try {
                synchronousQueue.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("add 3");
            try {
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aaa").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000); // aaa �̱߳���ȴ���2s�� bbb�߳�take
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bbb").start();
    }

}
