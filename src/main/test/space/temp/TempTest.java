package space.temp;

import org.junit.Test;

public class TempTest {
    @Test
    public void test(){
        String[] strings = new String[5];
        for (int i = 0; i < strings.length; i++){
            strings[i] = "" + i;
            System.out.println(strings[i]);
        }

    }
}
