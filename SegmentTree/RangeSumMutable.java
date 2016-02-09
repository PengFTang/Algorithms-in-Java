package segmenttree;

/*
Goal: Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive. The array can be modified by updateItem method.
*/

public class RangeSumMutable {
	
	int N; // length of array
	int[] nums; // array copy
	TreeNode root; // range sum tree root
	
	private class TreeNode {
		public int val;
		public TreeNode left=null, right=null;
		public TreeNode(int val) { this.val = val; }
		
		// visualize the segment tree
		public String toString() {
		    return toString(this, new StringBuffer(), true, new StringBuffer()).toString();
		}
		public StringBuffer toString(TreeNode root, StringBuffer prefix, boolean isTail, StringBuffer sb) {
		    if(root.right!=null) {
		        toString(root.right, new StringBuffer().append(prefix).append(isTail ? "│  " : "   "), false, sb);
		    }
		    String str = Integer.toString(root.val);
		    sb.append(prefix).append(isTail ? "└──" : "┌──").append(str).append("\n");
		    if(root.left!=null) {
		        toString(root.left, new StringBuffer().append(prefix).append(isTail ? "   " : "│  "), true, sb);
		    }
		    return sb;
		}
	}

    public RangeSumMutable(int[] nums) {
    	N = nums.length;
    	if(N>0) {
        	this.nums = nums;
    		int[] sums = new int[N+1];
    		for(int i=0; i<N; i++)
    			sums[i+1] = sums[i]+nums[i];
    		root = new TreeNode(sums[N]);
    		constructSegmentTree(sums, 0, N-1, root);
    	}
    }
	private void constructSegmentTree(int[] sums, int lo, int hi, TreeNode root) {
		if(lo==hi) return;
		int mid = lo + ((hi-lo)>>1); // divide into two subtrees
		root.left = new TreeNode(sums[mid+1] - sums[lo]); // add root's left child
		root.right = new TreeNode(sums[hi+1] - sums[mid+1]); // add root's right child
		constructSegmentTree(sums, lo, mid, root.left); // move to root's left child and continue with constructing
		constructSegmentTree(sums, mid+1, hi, root.right); // move to root's right child and continue with constructing
	}

    void updateItem(int i, int val) {
    	updateSegmentTree(i, 0, N-1, val-nums[i], root);
    	nums[i] = val; // update array copy
    }
    // update all tree nodes containing index i
    private void updateSegmentTree(int i, int lo, int hi, int diff, TreeNode root) {
    	root.val += diff; 
    	if(lo==i && hi==i) return;
    	int mid = lo + ((hi-lo)>>1);
    	if(i>mid)   updateSegmentTree(i, mid+1, hi, diff, root.right);
    	else        updateSegmentTree(i, lo, mid, diff, root.left);
    }

    public int rangeSum(int i, int j) {
    	if(i>j || i<0 || j>=N) return 0;
    	return range(i, j, 0, N-1, root);
    }
    private int range(int i, int j, int lo, int hi, TreeNode root) {
    	if(i==lo && j==hi) return root.val; // full cover
    	int mid = lo + ((hi-lo)>>1);
    	if(j<=mid)		return range(i, j, lo, mid, root.left); // cover left half
    	else if(i>mid) 	return range(i, j, mid+1, hi, root.right); // cover right half
    	else			return range(i, mid, lo, mid, root.left) + range(mid+1, j, mid+1, hi, root.right); // mixed cover
    }
    
    
	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
		RangeSumMutable na = new RangeSumMutable(nums);
		
		System.out.println("original segment tree:\n" + na.root.toString());

		System.out.println("range sum (0, 5): " + na.rangeSum(0, 5));
		System.out.println("range sum (3, 6): " + na.rangeSum(3, 6));
		System.out.println();
		
		System.out.println("update index 0 to 10");
		na.updateItem(0, 10);
		
		System.out.println("update index 1 to 3");
		na.updateItem(1, 3);
		
		System.out.println("update index 6 to 70");
		na.updateItem(6, 70);

		System.out.println();

		System.out.println("updated segment tree:\n" + na.root.toString());
		
		System.out.println("range sum (0, 5): " + na.rangeSum(0, 5));
		System.out.println("range sum (3, 6): " + na.rangeSum(3, 6));
	}
}
