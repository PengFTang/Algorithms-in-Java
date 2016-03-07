// package supports;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    
    public static TreeNode constructTreeFromString(String str) {
    	String[] strs = str.split(",");
		int L = strs.length;
		if(L < 1) return null;
		
		TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
		
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int i = 0;
		while(i<L-1) {
			TreeNode current = q.peek();
			q.poll();
			if(i<L-1 && !strs[++i].equals("#")) {
				current.left = new TreeNode(Integer.parseInt(strs[i]));
				current.left.left = null;
				current.left.right = null;
				q.add(current.left);
			}
			if(i<L-1 && !strs[++i].equals("#")) {
				current.right = new TreeNode(Integer.parseInt(strs[i]));
				current.right.left = null;
				current.right.right = null;
				q.add(current.right);
			}
		}
		return root;
	}	
    
	public static void preorderTraversal(TreeNode root) {
		//TreeNode root = this;
		//if(root == null) return;
		System.out.print(root.val + "\t");
		if(root.left!=null)
			preorderTraversal(root.left);
		if(root.right!=null)
			preorderTraversal(root.right);
	}
	public static void inorderTraversal(TreeNode root) {
		//TreeNode root = this;
		if(root == null) return;
		if(root.left!=null)
			inorderTraversal(root.left);
		System.out.print(root.val + "\t");
		if(root.right!=null)
			inorderTraversal(root.right);
	}
	public static void postorderTraversal(TreeNode root) {
		//TreeNode root = this;
		if(root == null) return;
		if(root.left!=null)
			postorderTraversal(root.left);
		if(root.right!=null)
			postorderTraversal(root.right);
		System.out.print(root.val + "\t");
	}
	
	public static String toString(TreeNode root) {
	    return toString(root, new StringBuffer(), true, new StringBuffer()).toString();
	}
	public static StringBuffer toString(TreeNode root, StringBuffer prefix, boolean isTail, StringBuffer sb) {
	    if(root.right!=null) toString(root.right, new StringBuffer().append(prefix).append(isTail ? "©¦   " : "    "), false, sb);
	    String str = " " + root.val + " ";
	    sb.append(prefix).append(isTail ? "©¸©¤©¤" : "©°©¤©¤").append(str).append("\n");
	    if(root.left!=null) toString(root.left, new StringBuffer().append(prefix).append(isTail ? "    " : "©¦   "), true, sb);
	    return sb;
	}
}
