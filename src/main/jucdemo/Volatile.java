package jucdemo;

class Mydata{
    volatile int number = 0;
//    int number = 0;
    public void addTo60(){
        this.number = 60;
    }
}

/**
 * ��֤volatile�Ŀɼ���
 * @author space
 */

public class Volatile {
    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                Thread.sleep(3000);
//                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated value: " + mydata.number);
        }, "aaa").start();

        while (mydata.number == 0){
//            System.out.println(mydata.number); // todo Ϊʲô������һ�䣬main�߳̾Ϳ��Դ�ӡ�������over���أ� �����ӵĻ���һֱѭ��
        }; // ���numberû�б�volatile���Σ���main�̶߳�����numberһֱ��0

        System.out.println(Thread.currentThread().getName() + "\t over");
    }

}
