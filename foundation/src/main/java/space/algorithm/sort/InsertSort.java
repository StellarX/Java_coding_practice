package space.algorithm.sort;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		int[] a = {1,4,2,8,3,7,8,4,6,9};
		System.out.println(Arrays.toString(a));
		
		for(int i = 1; i < a.length; i++) {
			int j = i-1;
			if(a[i] < a[j]) {
				int temp = a[i];
				do {
					a[j+1] = a[j];
					j--;
				}while(j >= 0 && temp < a[j]);
				a[j+1] = temp;
			}
		}
		
		
		System.out.println(Arrays.toString(a));
		
	}

}
