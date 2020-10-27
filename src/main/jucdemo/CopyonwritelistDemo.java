import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyonwritelistDemo {
    public static void main(String[] args) {

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(8);
        copyOnWriteArrayList.add(6);
        copyOnWriteArrayList.add(5);
        copyOnWriteArrayList.add(3);
        System.out.println(copyOnWriteArrayList);
        for(int i = 0; i < 4; i++){
            int finalI = i;
            new Thread(() -> {
                System.out.println(copyOnWriteArrayList.get(finalI));
            }, String.valueOf(i)).start();
        }

    }
}
