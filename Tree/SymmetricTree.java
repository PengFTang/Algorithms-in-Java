package tree;

public class SymmetricTree {
  // iterative method:
  public boolean isSymmetricIterative(TreeNode root) {
    if(root==null) return true;
    Queue<TreeNode> q1=new LinkedList<>(), q2=new LinkedList<>();
    q1.add(root.left); 
    q2.add(root.right);
    while(!q1.isEmpty() && !q2.isEmpty()) {
        int size1=q1.size(), size2=q2.size();
        if(size1!=size2) return false;
        for(int i=0; i<size1; i++) {
            TreeNode current1=q1.remove(), current2=q2.remove();
            if(current1==null && current2==null) continue;
            if(current1==null || current2==null) return false; 
            if(current1.val!=current2.val) return false;
            q1.add(current1.left);
            q1.add(current1.right);
            q2.add(current2.right);
            q2.add(current2.left);
        }
    }
    return q1.isEmpty() && q2.isEmpty();
  }

  // recursive method:
  public boolean isSymmetricRecursive(TreeNode root) {
    if(root==null) return true;
    return helper(root.left, root.right);
  }
  private boolean helper(TreeNode root1, TreeNode root2) {
    if(root1==null && root2==null) return true;
    if(root1==null || root2==null) return false;
    if(root1.val!=root2.val) return false;
    return helper(root1.left, root2.right) && helper(root1.right, root2.left);
  }
}
