import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @briefing Array blocking queue test
 * @author space
 * @date 2021/01/07 22:48
 * @location ChengDu,SiChuan,China
 */
public class ArrayBlockQueueDemo {
    public static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
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
        System.out.println(blockingQueue.offer("b", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 10, TimeUnit.SECONDS));// block for 10s
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
    }

    /**
     * test methods that will block if something wrong
     */
    public static void blockMethod() throws InterruptedException {
        blockingQueue.put("a");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());//will block/wait, because there is no more element in queue.
        blockingQueue.put("b");
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
        System.out.println(blockingQueue.element());//return first element, that is 1.
    }
}
