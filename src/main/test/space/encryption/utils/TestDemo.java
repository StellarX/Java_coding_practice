package space.encryption.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TestDemo {

    @Test
    public void testClone(){
        System.out.println("test");
        ArrayList<String> list = new ArrayList<>();
        list.add("jack");
        list.add("tom");

        ListIterator<String> listIterator = list.listIterator();
        listIterator.add("mary");
        while (listIterator.hasNext()){
            String next = listIterator.next();
            System.out.println(next);

        }

    }

}
