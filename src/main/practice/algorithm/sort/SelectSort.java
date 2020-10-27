package algorithm.sort;

import java.util.Arrays;

public class SelectSort {

	public static void main(String[] args) {
		int[] a = {1,4,2,8,3,7,8,4,6,9};
		System.out.println(Arrays.toString(a));
		
		
		
		System.out.println(Arrays.toString(a));
	}

}


//Answer:
//int min;
//for(int i = 0; i < a.length-1; i++) {
//	min = a[i];
//	for(int j = i+1; j < a.length; j++) 
//		if(a[j] < min) 
//			min = a[j];
//	int t = min;
//	min = a[i];
//	a[i] = t;
//}
