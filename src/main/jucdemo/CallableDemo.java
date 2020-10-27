import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @briefing 运行线程的第三种方法 实现callable接口
 * @author space
 * @date 2020/10/8 11:19
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println("run...");
        Thread.sleep(2000);
        return 123;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask, "aaa").start();
//        new Thread(futureTask, "bbb").start();// bbb不会执行


        System.out.println("return : " + futureTask.get());// get尽量往后放
//        while(!futureTask.isDone()){ // todo 要这个有什么用？
//        }

        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}
