package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @briefing 线程池的创建和使用
 * @author space
 * @date 2020/10/8 21:17
 */
public class Demo1 {

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor;
//        threadPoolExecutor = new ThreadPoolExecutor();

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();


        try{
            for(int i = 0; i < 20; i++){
//                threadPool.submit()
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t doing...");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

        //辅助工具类
//        Executors.callable()

    }

}
