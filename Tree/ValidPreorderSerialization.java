/*
One way to serialize a binary tree is to use pre-oder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
 */
 
public class ValidPreorderSerialization {
	
	public static void main(String[] args) {
		ValidPreorderSerialization q = new ValidPreorderSerialization();

		//String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		String preorder = "1,#,2,#,3,#,4,#,5,#,6,#,7,#,8,#,9,#,10,#,11,#,12,#,13,#,14,#,15,#,#";
		
		long start = System.nanoTime();
		boolean res = q.isValidSerialization(preorder);
		long end = System.nanoTime();
		double duration = (end-start)/1000000.;
		
		System.out.println(res);
		System.out.println("duration: " + duration + "ms");
	} 
	/*
	 * Solution:
	 * Interesting fact: number of sentinel nodes = number of non-sentinel nodes + 1
	 * Key idea:
		- count: counter of non-sentinel nodes - sentinel nodes up to index i. So we increase counter if current value is not # (it is like push item into stack), otherwise decrease it (it is like pop item out of stack)
		- if current value is # and counter is already 0, return false (it is like the case when stack is already empty and you cannot pop anymore)
		- however, if counter is 0 and we already moved to the last location, return true because of the above interesting fact
	 */
	public boolean isValidSerialization(String preorder) {
	    String[] strs = preorder.split(",");
	    int N = strs.length, count = 0, i = -1;
	    while(++i<N-1) {
	        if(!"#".equals(strs[i])) ++count;
	        else if(--count<0) return false;
	    }
	    return count==0 && "#".equals(strs[i]);
	}
}
