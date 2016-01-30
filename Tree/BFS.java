package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * iterative and recursive breadth first traversal of binary tree
 * @author Peng F. Tang
 * pengftang@gmail.com
 * @param <T> Generic type
 */

public class BFS<T> {
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
		BFS<String> bfs = new BFS<>();
		
		String str = "1,2,3,4,,6,7,8,9,,11,,13,,,,17,18,,19,,20";
		
		TreeNode<String> node = new TreeNode<String>("");
		TreeNode<String> root = node.create(str);
		
		List<List<String>> res = bfs.recursiveBFS(root);
		System.out.println(res);
		
		List<List<String>> res2 = bfs.iterativeBFS(root);
		System.out.println(res2);
 	}
}
