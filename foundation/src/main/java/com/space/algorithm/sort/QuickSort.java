package com.space.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		 int[] arr = {2,3,6,4,8,1,0,5,7};
		 System.out.println(Arrays.toString(arr));
		 
		 
		 System.out.println(Arrays.toString(arr));
	}
	
 
	
	public static void swap(int[] arr, int front, int back) {
		int temp = arr[front];
		arr[front] = arr[back];
		arr[back] = temp;
	}
}
