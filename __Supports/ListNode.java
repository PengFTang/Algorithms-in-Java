// package supports;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
    public static ListNode constructLinkedListFromString(String str) {
    	String[] strs = str.split(",");
		int L = strs.length;
		if(L < 1) return null;
		
		ListNode head = new ListNode(Integer.parseInt(strs[0]));
		ListNode current = head;
		for(int i=1; i<L; i++) {
			ListNode next = new ListNode(Integer.parseInt(strs[i])); 
			current.next = next;
			current = next;
		}
		return head;
	}
    
    public static void printList(ListNode head) {
    	ListNode current = head;
    	while(current!=null) {
    		System.out.print(current.val+"-->");
    		current = current.next;
    	}
    	System.out.println("null");
    }
    
    public static void main(String[] args) {
    	
    }
}
