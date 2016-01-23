import java.util.ArrayList;
import java.util.Collections;

public class LIS {
	private int[] maxLengthEndHere;
	private int[] arr;
	
	public LIS(int[] arr) {
		this.arr = arr;
		maxLengthEndHere = new int[arr.length];
	}

	private int getLISbottomup() {
		return getLISbottomup(arr);
	}
	
	private int getLISbottomup(int[] arr) {
		int max = 1;
		maxLengthEndHere[0] = 1;
		for(int i=1; i<arr.length; i++) {
			int maxLength = 1;
			for(int j=i-1; j>=0; j--) {
				if(arr[i] >= arr[j] && maxLengthEndHere[j]+1 > maxLength) {
					maxLength = maxLengthEndHere[j] + 1;
				}
			}
			maxLengthEndHere[i] = maxLength;
			max = maxLength > max ? maxLength : max;
		}
		return max;
	}
	
	private ArrayList<Integer> getLISbottomup(int max) {
		ArrayList<Integer> list = new ArrayList<>();
		int j=0;
		for(int i=maxLengthEndHere.length-1; i>=0; i--) {
			if(maxLengthEndHere[i] == max-j) {
				list.add(arr[i]);
				j++;
			}
		}
		Collections.reverse(list);
		return list;
	}
	
	public static void main(String[] args) {
		int[] arr = {10, 0, 10, 30, 40, 45, 50, 10, 10, 10, 100, 100, 10, 11, 12, 101, 13, 105, 14, 16, 200};
		int L = arr.length;
		
		LIS lis = new LIS(arr);

		int max = lis.getLISbottomup();
		ArrayList<Integer> list = lis.getLISbottomup(max);
		System.out.println("maximum length: " + max);
		for(int i=0; i<L; i++)
			System.out.print(lis.maxLengthEndHere[i] + "\t");
		System.out.println();
		System.out.println(list);
		
		//System.out.println(lis.getSequence());
	}
}
