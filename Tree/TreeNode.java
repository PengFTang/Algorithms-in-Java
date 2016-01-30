package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class TreeNode<T> {
		public T val;
	    public TreeNode<T> left=null, right=null;
	    public TreeNode(T x) { 
	    	val = x;
	    }	
	    // construct a binary tree with a formated string
	    public TreeNode<T> create(String str) {
	    	String[] strs = str.split(",");
			int L = strs.length;
			if(L < 1)  throw new NoSuchElementException("invalid input string");
			
			TreeNode<T> root = new TreeNode<>((T)convert(strs[0]));
			Queue<TreeNode<T>> q = new LinkedList<>();
			q.add(root);
			int i = 0;
			while(i<L-1) {
				if(q.isEmpty()) throw new NoSuchElementException("invalid input string");
				TreeNode<T> current = q.peek();
				q.poll();
				if(i<L-1 && !strs[++i].equals("")) {
					current.left = new TreeNode<>((T)convert(strs[i]));
					q.add(current.left);
				}
				if(i<L-1 && !strs[++i].equals("")) {
					current.right = new TreeNode<>((T)convert(strs[i]));
					q.add(current.right);
				}
			}
			return root;
	    }
	    
	    private Object convert(String str) {
	    	if(val instanceof Integer) return Integer.parseInt(str);
	    	if(val instanceof Character) return str.charAt(0);
	    	return str;
	    }
	}
