import lombok.SneakyThrows;

/**
 * @briefing ʹ�� wait��notify��notifyAll���� �Լ� synchronizedͬ������� ʵ��������������ģʽ (2�������ߣ� 1��������)
 * @author space
 * @date 2020/10/6 13:09
 */


class Res1{
    int apple;
}

class Producer1 implements Runnable {
    private final Res1 r;

    Producer1(Res1 r) {
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (r) {
                while (r.apple == 1) r.wait(); // ʹ��ǰ�̵߳ȴ����������� todo ���r.wait() �� Producer �Լ� r������ʲô��ϵ��
                r.apple++;
                System.out.println(Thread.currentThread().getName() + " apple ++  = " + r.apple);
                r.notifyAll();
            }
        }
    }
}

class Consumer1 implements Runnable{
    private final Res1 r;

    Consumer1(Res1 r){
        this.r = r;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(true){
            synchronized (r){
                if(r.apple == 0) r.wait();
                r.apple--;
                System.out.println(Thread.currentThread().getName() +  " apple --  = " + r.apple);
                r.apple = 0;
                r.notify();
            }
        }
    }
}

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Res1 r = new Res1();
        new Thread(new Producer1(r)).start();
        new Thread(new Producer1(r)).start();
        new Thread(new Consumer1(r)).start();

    }
}
