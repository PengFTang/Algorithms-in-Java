package sorting;

import java.util.Random;

public class MergeSort {
	// bottom up merge sort
	public void mergeSortBottomUp(int[] arr) {
		int L = arr.length;
		int[] arrToMerge = new int[L];
		for(int i=2; i<L<<1; i=i<<1) {
			for(int j=0; j<L; j+=i) {
				int left = j;
				int right = j+i-1 < L-1 ? j+i-1 : L-1;
				int mid = j+(i>>1)-1;
				merge(arr, arrToMerge, left, mid, right);
			}
		}
	}
	
	// top down merge sort
	public void mergeSortTopDown(int[] arr) {
		int[] arrToMerge = new int[arr.length];
		mergeSort(arr, arrToMerge, 0, arr.length-1);
	} 
	public void mergeSort(int[] arr, int[] arrToMerge, int left, int right) {
		if(left<right) {
			int mid = left + ((right-left)>>1);
			mergeSort(arr, arrToMerge, left, mid);
			mergeSort(arr, arrToMerge, mid+1, right);
			merge(arr, arrToMerge, left, mid, right);
		}
	}
	
	// merge with one 'for' loop, 
	// reference: http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Merge.java.html
	private void merge(int[] arr, int[] arrToMerge, int left, int mid, int right) {
		for(int m=left; m<=right; m++)
			arrToMerge[m] = arr[m]; // copy arr to arrToMerge
		int i=left, j=mid+1;
		for(int k=left; k<=right; k++) {
			if(i>mid) 
				arr[k] = arrToMerge[j++];
			else if(j>right) 
				arr[k] = arrToMerge[i++];
			else if(arrToMerge[i]<=arrToMerge[j]) 
				arr[k] = arrToMerge[i++];
			else 
				arr[k] = arrToMerge[j++];
		}
	}
	/*
	// merge with three 'while' loops
	private void merge(int[] arr, int[] arrToMerge, int left, int mid, int right) {
		int i=left, j=mid+1;
		for(int m=left; m<=right; m++)
			arrToMerge[m] = arr[m];
		int k = left;
		while(i<=mid && j<=right) {
			if(arrToMerge[i] <= arrToMerge[j])
				arr[k++] = arrToMerge[i++];
			else
				arr[k++] = arrToMerge[j++];
		}
		while(i<=mid)
			arr[k++] = arrToMerge[i++];
		while(j<=right)
			arr[k++] = arrToMerge[j++];
	}
	*/
	
	public static void main(String[] args) {
		int N = 10000000;
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		Random rm = new Random();
		
		//System.out.print("Original:\t");
		for(int i=0; i<N; i++) {
			arr1[i] = rm.nextInt(10000);
			//System.out.print(arr1[i] + " ");
		}
		//System.out.println();
		
		arr2 = arr1.clone();

		MergeSort ms = new MergeSort();
		
		long start1 = System.nanoTime();
		ms.mergeSortTopDown(arr1);
		long end1 = System.nanoTime();
		//System.out.print("\n\nTD Sorted:\t");
		//for(int i : arr1)
		//	System.out.print(i + " ");
		//System.out.println();

		long start2 = System.nanoTime();
		ms.mergeSortBottomUp(arr2);
		long end2 = System.nanoTime();
		//System.out.print("\n\nBU Sorted:\t");
		//for(int i : arr2)
		//	System.out.print(i + " ");
		//System.out.println();

		System.out.println("TD time (ms): " + (end1-start1)/1e6);
		System.out.println("BU time (ms): " + (end2-start2)/1e6);
	}
}
