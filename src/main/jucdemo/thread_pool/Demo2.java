package thread_pool;

import java.util.concurrent.*;

/**
 * @briefing 自定义线程池 测试4种拒绝策略
 * @author space
 * @date 2020/10/10 10:57
 */
public class Demo2 {

    public static void main(String[] args) {

//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try{
            for(int i = 0; i < 33; i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t run....");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }


}
