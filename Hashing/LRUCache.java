/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    
    class ListNode {
        int val;
        ListNode prev, next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    
    int size = 0;
    int capacity;
    ListNode head, tail;
    Map<Integer, ListNode> map;
    
    public LRUCache(int capacity) {
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
    	ListNode node = map.getOrDefault(key, head);
    	if(node.val != -1) moveToHead(node);
        return node.val;
    }
    
    public void set(int key, int value) {
        if(map.getOrDefault(key, head).val != -1) {
            moveToHead(map.get(key));
            map.get(key).val = value;
        } else {
            ListNode newNode = new ListNode(value);
            addNode(newNode);
            if(++size > capacity) {
                removeNode(tail.prev);
                --size;
            }
            map.put(key, newNode);
        }
    }
    
    private void moveToHead(ListNode node) {
        if(node == head || node.prev == head) {
        	System.out.println("head: " + head.val);
        	return;
        }
        detachNode(node);
        addNode(node);
    }
    
    private void addNode(ListNode newNode) {
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
    }
    
    private void removeNode(ListNode node) {
        detachNode(node);
    	node.val = -1;
    }
    
    private void detachNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
    }
	
	public static void main(String[] args) {
		int capacity = 2;
		LRUCache lru = new LRUCache(capacity);
		lru.set(2, 1);
		lru.set(1, 1);
		System.out.println(lru.get(2));
		lru.set(4, 1);
		System.out.println(lru.get(2));
		System.out.println(lru.get(1));
	}
}
