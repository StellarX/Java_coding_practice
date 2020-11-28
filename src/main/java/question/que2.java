package question;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


class Father{
    static int a = 10;
}

public class que2 extends Father{

    public static void main(String[] args) throws IOException {

        int[] a = {7,3,1,5};
        for(int i = 0; i < a.length; i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(a[finalI]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a[finalI]);
            }).start();
        }


    }
}
