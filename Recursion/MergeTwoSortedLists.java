	public ListNode merge(ListNode l1, ListNode l2) {
		if(l1==null) return l2;
		if(l2==null) return l1;
		if(l1.val<=l2.val) {
			l1.next = merge(l1.next, l2); // l1's first component will be the final head
			return l1; // l1 will be the final merged list
		}
		else {
			l2.next = merge(l1, l2.next); // l2's first component will be the final head
			return l2; // l2 will be the final merged list
		}
	}
	
