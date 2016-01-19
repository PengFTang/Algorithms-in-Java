package sorting;

import java.util.Random;

public class HeapSort {

	public void heapSort(int[] arr) {
		int N = arr.length-1;
		heapify(arr);
		
		while(N>0) {
			swap(arr, 0, N--);
			sink(arr, 0, N);
		}
	}
	// swap elements such that new array is a max-heap
	private void heapify(int[] arr) {
		for(int k=(arr.length>>1)-1; k>=0; k--) {
			sink(arr, k, arr.length-1);
		}
	}
	// swap parent with either of its two children if needed
	private void sink(int[] arr, int index, int N) {
		while((index<<1)+1 <= N) {
			int j = (index<<1)+1;
			if(j<N && arr[j]<arr[j+1]) j++;
			if(arr[index]>=arr[j]) break;
			swap(arr, index, j);
			index = j;
		}
	}
	
	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static void main(String[] args) {
		HeapSort heap = new HeapSort();
		
		int N = 10;
		int[] arr = new int[N];
		Random rm = new Random();
		System.out.println("Original array: ");
		for(int i=0; i<arr.length; i++) {
			arr[i] = rm.nextInt(100);
			System.out.print(arr[i] + " ");
		}
		
		
		heap.heapSort(arr);
		
		System.out.println("\n\nSorted array: ");
		for(int n : arr)
			System.out.print(n + " ");
		System.out.println();
	}
}
