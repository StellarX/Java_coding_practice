package algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] a = {1,4,2,8,3,7,8,4,6,9};
		System.out.println(Arrays.toString(a));
		
		for(int i = 0; i < a.length - 1; i++) {
			for(int j = 0; j < a.length - i - 1; j++) {
				if(a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(a));
	}

}
