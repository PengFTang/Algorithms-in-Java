package sorting;

import methods.*;
import java.util.Random;

public class QuickSort {

	public void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length-1, new Random());
	}
	// partition an array with specified range [left, right]
    private void quickSort(int[] nums, int left, int right, Random rm) {
        int L = right-left;
        if(L<=0) return; // one item in range, thus no need to sort
        int pivot = rm.nextInt(L) + left; // randomly choose pivot
        swap(nums, left, pivot); // swap pivot with leftmost item
        pivot = left; // update pivot index to leftmost index
        int lo = left+1, hi = right;
        while(lo<=hi) {
            if(nums[lo]==nums[pivot]) ++lo; // move right if low item equals pivot item
            else if(nums[lo]<nums[pivot]) swap(nums, lo++, pivot++); // swap low item with pivot if low item is less than pivot item
            else if(nums[hi]>nums[pivot]) --hi; // move left if high item is larger than pivot item
            else swap(nums, lo, hi); // swap if low item > high item 
        }
        quickSort(nums, lo, right, rm); // sort left part
        quickSort(nums, left, pivot-1, rm); // sort right part
    }
    // swap two items in an array
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    // display array
    private void printArray(int[] arr) {
    	for(int i : arr) System.out.print(i + " ");
    	System.out.println();
    }
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		
		//int[] arr = {5,5,5,5,5,5,5,5,5};
		
		while(true) {
			int[] arr = new int[50];
			Random rm = new Random();
			for(int i=0; i<arr.length; i++) arr[i] = rm.nextInt(20);
			System.out.print("original: ");
			qs.printArray(arr);
			
			qs.quickSort(arr);
	
			System.out.print("sorted: ");
			qs.printArray(arr);
			
			if(!Methods.isSorted(arr)) {
				System.out.println("wrong!");
				System.exit(0);
			}
		}
	}
}
