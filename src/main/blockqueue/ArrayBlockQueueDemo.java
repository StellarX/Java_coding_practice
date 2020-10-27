import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @briefing Arrayblockingqueue demo
 * @author space
 * @date 2020/10/4 11:50
 */
public class ArrayBlockQueueDemo {
    public static BlockingQueue<String> blockingQueue = new java.util.concurrent.ArrayBlockingQueue<String>(3);
    public static void main(String[] args) throws InterruptedException {
//        exceptionMethod();
//        booleanMethod();
//        blockMethod();
        timeoutMethod();

    }


    /**
     * test methods that will block just a while if something wrong
     */
    public static void timeoutMethod() throws InterruptedException {
        System.out.println(blockingQueue.offer("a", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
    }

    /**
     * test methods that will block if something wrong
     * @throws InterruptedException
     */
    public static void blockMethod() throws InterruptedException {
//        blockingQueue.put("a");
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        blockingQueue.put("b");
    }

    /**
     * test methods that will return boolean rather than throw exception if something wrong
     */
    public static void booleanMethod(){
        System.out.println(blockingQueue.offer("1")); // true
        System.out.println(blockingQueue.offer("2")); // true
        System.out.println(blockingQueue.offer("3")); // true
        System.out.println(blockingQueue.offer("4")); // false

        System.out.println(blockingQueue.peek()); // return 1

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()); // return null
    }

    /**
     * test methods that will throw exception if something wrong
     */
    private static void exceptionMethod() {
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));
//        System.out.println(blockingQueue.add("3")); // throw exception: IllegalStateException: Queue full
//        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());
    }
}
