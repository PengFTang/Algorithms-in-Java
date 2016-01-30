package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * iterative and recursive breadth first traversal of binary tree
 * @author Peng F. Tang
 * pengftang@gmail.com
 * @param <T> Generic type
 */

public class BFS<T> {
	private static class TreeNode<T> {
		public T val;
	    public TreeNode<T> root=null, left=null, right=null;
	    public TreeNode(T x) { 
	    	val = x;
	    }	
	    // construct a binary tree with a formated string
	    public TreeNode(String str) {
	    	String[] strs = str.split(",");
			int L = strs.length;
			if(L < 1)  throw new NoSuchElementException("invalid input string");
			
			root = new TreeNode<>((T)convert(strs[0]));
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
	    }
	    
	    private Object convert(String str) {
	    	if(val instanceof Integer) return Integer.parseInt(str);
	    	if(val instanceof Character) return str.charAt(0);
	    	return str;
	    }
	}
	
	public List<List<T>> iterativeBFS(TreeNode<T> root) {
		List<List<T>> res = new ArrayList<>();
		if(root==null) return res;
		Queue<TreeNode<T>> q = new LinkedList<>();
		q.offer(root);
		List<T> list = new ArrayList<>();
		list.add(root.val);
		while(!q.isEmpty()) {
			res.add(list);
			list = new ArrayList<>();
			int size = q.size();
			for(int i=0; i<size; i++) {
				TreeNode<T> current = q.poll();
				if(current.left!=null) {
					q.add(current.left);
					list.add(current.left.val);
				}
				if(current.right!=null) {
					q.add(current.right);
					list.add(current.right.val);
				}
			}
		}
		return res;
	}
	
	public List<List<T>> recursiveBFS(TreeNode<T> root) {
		List<List<T>> res = new ArrayList<>();
		if(root==null) return res;
		List<TreeNode<T>> nodes = new ArrayList<>();
		nodes.add(root);
		List<T> list = new ArrayList<>();
		list.add(root.val);
		res.add(list);
		bfs(nodes, res);
		return res;
	}
	private void bfs(List<TreeNode<T>> nodes, List<List<T>> res) {
		//if(nodes.isEmpty()) return;
		List<TreeNode<T>> newNodes = new ArrayList<>();
		List<T> list = new ArrayList<>();
		for(TreeNode<T> node : nodes) {
			if(node.left!=null) {
				newNodes.add(node.left);
				list.add(node.left.val);
			}
			if(node.right!=null) {
				newNodes.add(node.right);
				list.add(node.right.val);
			}
		}
		if(!list.isEmpty()) {
			res.add(list);
			bfs(newNodes, res);
		}
	}
	
	public static void main(String[] args) {
		BFS<Character> bfs = new BFS<>();
		
		String str = "1,2,3,4,,6,7,8,9,,11,,13,,,,17,18,,19,,20";
		
		TreeNode<Character> root = new TreeNode<Character>(str).root;
		
		List<List<Character>> res = bfs.recursiveBFS(root);
		System.out.println(res);
		
		List<List<Character>> res2 = bfs.iterativeBFS(root);
		System.out.println(res2);
 	}
}
