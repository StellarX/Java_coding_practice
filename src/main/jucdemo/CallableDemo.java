import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @briefing �����̵߳ĵ����ַ��� ʵ��callable�ӿ�
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
//        new Thread(futureTask, "bbb").start();// bbb����ִ��


        System.out.println("return : " + futureTask.get());// get���������
//        while(!futureTask.isDone()){ // todo Ҫ�����ʲô�ã�
//        }

        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}
