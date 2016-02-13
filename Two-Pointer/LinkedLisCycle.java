/**
  * The following code solves the problem of detecting the staring node of a cycle in a linked list if there is one.
  * The key idea is to use a slow and a fast pointer traversing the list, where slow moves one step forward at a time and the fast moves two steps forward at a time.
  * If they is no cycle, fast will get to the null node at the end; otherwise, slow and fast will meet up at a node inside the cycle.
  * For the second round, we use two pointers to traverse the list, one at the head and the other at the meeting point. When the two new pointers meet up, that's the entrence of the cycle.
  */

    public ListNode detectCycle(ListNode head) {
        ListNode fast=head, slow=head, meet=null;
        
        while(fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                meet = fast;
                break;
            }
        }
        
        slow = head;
        while(meet!=null) {
            if(slow == meet) return meet;
            else {
                slow = slow.next;
                meet = meet.next;
            }
        }
        
        return null;
    }
