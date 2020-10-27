package question;

import java.util.ArrayList;

public class que1 {

	public static void main(String[] args) {
		// TODO 为什么后者还要快些？？不科学！！
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 1000000; i++)
			list.add("20");
		long t1 = System.currentTimeMillis();
		while(list.remove("20"));
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);//341773
		
		
		for(int i = 0; i < 1000000; i++)
			list.add("20");
		t1 = System.currentTimeMillis();
		while (list.contains("20")) {
	        list.remove("20");
	    }
		t2 = System.currentTimeMillis();
		System.out.println(t2-t1);//321486
	}

}
