package space;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Practice {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    public static void test1(){
        String[] arr = new String[5];
        int[] b = new int[5];
        for (int i = 0; i < arr.length; i++){
            String a = "aaa";
            arr[i] = a;
            b[i] = i;
        }
        System.out.println(Arrays.toString(arr));
        List<String> strings = Arrays.asList(arr);
        List<int[]> ints = Arrays.asList(b);
        System.out.println(strings);
        int[] ints1 = ints.get(0);
        for (int i = 0; i <ints1.length; i++){
            System.out.println(ints1[i]);
        }
    }
    static void test2(){
        ArrayList<String> strings = new ArrayList<>();
        ListIterator<String> stringListIterator = strings.listIterator();
        strings.add(null);
        strings.add(null);
        strings.add("jack");
        System.out.println(strings.indexOf(null));
//        while(stringListIterator.hasNext()){
//            System.out.println(stringListIterator.next());
//        }
        System.out.println(strings.size());
    }
}
