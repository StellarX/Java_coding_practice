package com.space.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] a = {1,4,2,8,3,7,8,4,6,9};
		System.out.println(Arrays.toString(a));

		bubbleSortOptimized(a);
//		bubbleSort(a);
		
		System.out.println(Arrays.toString(a));
	}

	private static void bubbleSortOptimized(int[] a) {
		for (int i = 0; i < a.length - 1; i++){
			boolean isMove = false;
			for (int j = 0; j < a.length - i - 1; j++){
				if (a[j] > a[j+1]){
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
					isMove = true;
				}
			}
			if (!isMove) break;
		}
	}

	public static void bubbleSort(int[] a){
		for(int i = 0; i < a.length - 1; i++) {
			for(int j = 0; j < a.length - i - 1; j++) {
				if(a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

}
