/*
original problem description: http://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/

To be short, our goal is to merge to sorted array in place. For example: 
Input: ar1[] = {1, 5, 9, 10, 15, 20};
       ar2[] = {2, 3, 8, 13};
Output: ar1[] = {1, 2, 3, 5, 8, 9}
        ar2[] = {10, 13, 15, 20} 
*/

public class InplaceMerge {

	// Method 3 (O(mlogm + nlogn)): 
	public void merge(int[] arr1, int[] arr2) {
		int i1=0, i2=0, L1=arr1.length, L2=arr2.length, counter=0;
		while(counter++<L1) { // counter of items to be placed in arr1
			if(i2<L2 && arr2[i2]<=arr1[i1]) ++i2; // arr2[i2] will be placed in arr2
			else ++i1; // arr1[i1] will be placed in arr1 
		}
		i2 = 0; // reset arr2 pointer to 0
		while(i1<L1) {
			int temp = arr1[i1];
			arr1[i1] = arr2[i2];
			arr2[i2] = temp;
			++i1;
			++i2;
		}
		heapSort(arr1); // sort arr1 in place
		heapSort(arr2); // sort arr2 in place
	}
	
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

	// Method 2:
	/* 
	 * Algorithm:
	 * 1. scan two arrays and count how many items in arr2 will be in the final arr1
	 * 2. assume k elements in arr2 will be moved into arr1, swap the first k elements in arr2 with the last k elements in arr1.
	 * 3. for the last k elements in arr1, insert every element into its correct location
	 * 4. similar as step 3, insert every element of the unchanged portion of arr2 into its correct location
	 */
	public void merge(int[] arr1, int[] arr2) {
		int i1=0, i2=0, L1=arr1.length, L2=arr2.length, counter=0;
		while(counter++<L1) { // counter of items to be placed in arr1
			if(arr1[i1]<=arr2[i2]) ++i1; // arr1[i1] will be placed in arr1
			else ++i2; // arr2[i2] will be placed in arr2
		}
		int moveLoc = i1; // all items in arr1 starting from moveLoc will be moved into arr2 
		i2 = 0; // reset arr2 pointer to 0
		while(i1<L1) {
			int temp = arr1[i1];
			arr1[i1] = arr2[i2];
			arr2[i2] = temp;
			++i1;
			++i2;
		}
		int lo1=0, lo2=0, hi1=moveLoc-1, hi2=L1-moveLoc-1;
		while(hi1>=0 && lo1>=0) {
			lo1 = insert(arr1, L1, lo1, hi1++);
		}
		while(hi2>=0 && lo2>=0) {
			lo2 = insert(arr2, L2, lo2, hi2++);
		}
	}
	
	private int insert(int[] arr, int L, int lo, int hi) {
		if(hi>=L-1) return -1;
		int end = hi+1;
		int target = arr[hi+1];
		while(lo<=hi) {
			int mid = lo + ((hi-lo)>>1);
			if(arr[mid] > target) hi = mid-1;
			else if(arr[mid] < target) lo = mid+1;
			else break;
		}
		for(int i=end; i>lo; i--) {
			arr[i] = arr[i-1];
		}
		arr[lo] = target;
		return lo;
	}
	
	// Method 1: place items on waiting list
	/*
	 * Algorithm:
	 * 1. finish arr1 first. to do that, we use two pointers i1 and i2, pointing to the current location of arr1 and arr2
	 * 2. whenver arr1[i1] > arr2[i2], we replace arr1[i1] with arr2[i2]. Moreover, we put arr1[i1] into a waiting list, and the waiting list will be the left-most part of arr2. The waiting list will always kepted in sorted order.
	 * 3. if the waiting list is not empty, the next minimum item could be at any of the three locations: i1 of arr1, i2 of arr2, and 0 of arr2, where arr2[0] is the first and also minimum element of the waiting list.
	 * 4. when arr1 is done. arr2 is composed of two subarrays where each subarray is sorted. We then insert every element in the second part into the correct location of the first part.
	 */
	/*
	public void merge(int[] arr1, int[] arr2) {
		int i1=0, i2=0, j=-1, L1=arr1.length, L2=arr2.length, counter1=0, counter2=0;
		while(i1<L1) {
			// arr1[i1] is the minimum, thus move i1 to i1+1;
			if((i2<L2 && arr1[i1]<=arr2[i2]) && (j==-1 || arr1[i1]<=arr2[j])) {
				++i1;
			}
			// arr2[i1] is the minimum, thus put arr2[i2] into arr1[i1] and put arr1[i1] into the end of the waiting list.
			else if((i2<L2 && arr2[i2]<=arr1[i1]) && (j==-1 || arr2[i2]<=arr2[j])) {
				int temp = arr1[i1];
				arr1[i1] = arr2[i2];
				arr2[i2] = temp;
				j=0;
				++i1;
				++i2;
			}
			// if the minimum (the first element, or arr2[0]) is in the waiting list, put it into arr1[i1] and move all elements of the waiting list to its left so we have a spot to put arr1[i1] into it.
			else {
				int temp = arr1[i1];
				arr1[i1] = arr2[0];
				for(int k=0; k<i2-1; k++) {
					arr2[k]=arr2[k+1];
				}
				arr2[i2-1] = temp;
				++i1;
			}
		}
		
		while(i2<L2) {
			int insert = Arrays.binarySearch(arr2, 0, i2, arr2[i2]);
			int temp = arr2[i2];
			insert = insert<0 ? -insert-1 : insert;
			for(int i=i2; i>insert; i--) {
				arr2[i] = arr2[i-1];
			}
			arr2[insert] = temp;
			++i2;
		}
		
	}
	*/
	
	public static void main(String[] args) {
		InplaceMerge p = new InplaceMerge();

		int[] arr1 = {1,1,1,1,2};
		int[] arr2 = {1,1,1,1};
		//int[] arr1 = {10,11,12,13,14,15};
		//int[] arr2 = {1,2,3,4,5,6,7,8};
		//int[] arr1 = {1, 5, 9, 10, 15, 20}; //{10,11,12,13,14,15};
		//int[] arr2 = {2, 3, 8, 13}; //{1,2,3,4,5,6,7,8};
		
		p.merge(arr1, arr2);

		System.out.print("arr1: ");
		for(int i : arr1)
			System.out.print(i + " ");
		System.out.println();

		System.out.print("arr2: ");
		for(int i : arr2)
			System.out.print(i + " ");
		System.out.println();
	}
}
