import java.util.Random;

public class RangeMin {
	
	TreeNode root = null;
	int size = 0;
	
	private static class TreeNode {
	    private int val;
	    private TreeNode left;
	    private TreeNode right;
	    private TreeNode(int x) { val = x; }	
	    
	    // print out tree
	    private static String toString(TreeNode root) {
		    return toString(root, new StringBuffer(), true, new StringBuffer()).toString();
		}
	    private static StringBuffer toString(TreeNode root, StringBuffer prefix, boolean isTail, StringBuffer sb) {
		    if(root.right!=null) toString(root.right, new StringBuffer().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		    String str = " " + root.val + " ";
		    sb.append(prefix).append(isTail ? "└──" : "┌──").append(str).append("\n");
		    if(root.left!=null) toString(root.left, new StringBuffer().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		    return sb;
		}
	}
	
	// constructor
	public RangeMin(int[] nums) {
		size = nums.length;
		if(size==0) root = null;
		root = generateTree(nums, 0, size-1); // generate segment tree
	}
	
	// get range minimum
	public int rangeMin(int start, int end) {
		assert(end>=0 && start<=end && end<size);
		return rangeMin(start, end, 0, size-1, root);
	}
	private int rangeMin(int start, int end, int lo, int hi, TreeNode root) {
		int mid = lo + ((hi-lo)>>1);
		if(start==lo && end==hi) {
			return root.val;
		}
		else if(start<=mid && end>=mid+1) {
			return Math.min(rangeMin(start, mid, lo, mid, root.left), rangeMin(mid+1, end, mid+1, hi, root.right));
		}
		else if(start>mid) {
			return rangeMin(start, end, mid+1, hi, root.right);
		}
		else {
			return rangeMin(start, end, lo, mid, root.left);
		}
	}
	
	private TreeNode generateTree(int[] nums, int lo, int hi) {
		TreeNode root = null;
		if(lo==hi) {
			return new TreeNode(nums[lo]);
		}
		else if(lo==hi-1) {
			root = new TreeNode(Math.min(nums[lo], nums[hi]));
			root.left = new TreeNode(nums[lo]);
			root.right = new TreeNode(nums[hi]);
			return root;
		}
		else {
			TreeNode left = generateTree(nums, lo, lo+((hi-lo)>>1));
			TreeNode right = generateTree(nums, lo+((hi-lo)>>1)+1, hi);
			root = new TreeNode(Math.min(left.val, right.val));
			root.left = left;
			root.right = right;
			return root;
		}
	}
	
	// for the purpose of validating the algorithm
	private static void validate() {
		Random ran = new Random();
		int N = 100 + ran.nextInt(50);
		int[] nums = new int[N];
		System.out.print("array size: " + N + "\narray elements: ");
		for(int i=0; i<N; i++) {
			nums[i] = ran.nextInt(N);
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		
		RangeMin rm = new RangeMin(nums);
		
		// print generated tree
		System.out.println(TreeNode.toString(rm.root));
		
		System.out.println("\ntree generated, validating...");
		for(int start=0; start<N; start++) {
			int minBF = nums[start];
			for(int end=start; end<N; end++) {
				int min = rm.rangeMin(start, end);
				minBF = nums[end] < minBF ? nums[end] : minBF;
				if(min!=minBF) {
					System.out.println("inconsistency occured at ("+start+","+end+")\t" 
							+ min + ", " + minBF);
					System.exit(0);
				}
			}
			System.out.println("start="+start+" validated");
		}
		System.out.println("All tests passed...");
	}
	
	public static void main(String[] args) {

		//RangeMin.validate();
		
		//int[] nums = {1,2,3,4,5};
		//int[] nums = {10, 2, 4, 0, 1, 2, 5, 3, 0, 2};

		Random ran = new Random();
		int N = 20;
		int[] nums = new int[N];
		System.out.print("array elements: ");
		for(int i=0; i<N; i++) {
			nums[i] = ran.nextInt(100) - 50;
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		
		RangeMin rm = new RangeMin(nums);

		int start = ran.nextInt(N/2), end = start+ran.nextInt(N/2);
		System.out.print("elements in range: ");
		for(int i=start; i<=end; i++)
			System.out.print(nums[i] + " ");
		System.out.println();
		
		int min = rm.rangeMin(start, end);
		
		System.out.println("min in range: " + min);
	}
}
