package com.mukeshdua.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LinkList {

	private static Stack<Integer> getStack(ListNode head) {
		Stack<Integer> s = new Stack<Integer>();
		while (head != null) {
			s.push(head.val);
			head = head.next;
		}
		return s;

	}
	
	void printNthFromLast(int n,ListNode head)
    {
        int len = 0;
        ListNode temp = head;
 
        // 1) count the number of nodes in Linked List
        while (temp != null)
        {
            temp = temp.next;
            len++;
        }
 
        // check if value of n is not more than length of
        // the linked list
        if (len < n)
            return;
 
        temp = head;
 
        // 2) get the (n-len+1)th node from the begining
        for (int i = 1; i < len-n+1; i++)
            temp = temp.next;
 
        System.out.println(temp.val);
    }

	/**
	 * Check if linked list is palindrome
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		Stack<Integer> s = getStack(head);
		while (!s.isEmpty() || head != null) {
			if (s.isEmpty() || head == null) {
				return false;
			}
			if (Integer.valueOf(s.pop().toString()) != head.val) {
				return false;
			}
			head = head.next;
		}

		return true;
	}

	/**
	 * Reverse the link list
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverseList(ListNode head) {
		ListNode reverseHead = null;
		ListNode current = head;
		while (current != null) {
			ListNode next = current.next;
			current.next = reverseHead;
			reverseHead = current;
			current = next;

		}
		return reverseHead;

	}

	/**
	 * Definition for singly-linked list. class ListNode { int val; ListNode next; ListNode(int x) { val = x; next = null; } }
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}

	/*
	 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
	 * 
	 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your
	 * function.
	 */
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	/*
	 * Delete node at particular position
	 */
	ListNode Delete(ListNode head, int position) {
		if (head == null) {
			return null;
		} else if (position == 0) {
			return head.next;
		} else {
			ListNode n = head;
			for (int i = 0; i < position - 1; i++) {
				n = n.next;
			}
			n.next = n.next.next;
			return head;
		}
	}

	/*
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode current = head;
		while (current != null && current.next != null) {
			if (current.next.val == current.val) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		return head;
	}

	/**
	 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int x) { val = x; next = null; } }
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Map<Integer, Integer> headAVal = new HashMap<Integer, Integer>();
		while (headA != null) {
			headAVal.put(headA.val, 1);
			headA = headA.next;
		}
		while (headB != null) {
			if (headAVal.containsKey(headB.val)) {
				return headB;
			}
			headB = headB.next;
		}
		return null;
	}

	/*
	 * Remove all elements from a linked list of integers that have value val.
	 * 
	 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
	 * 
	 * 
	 */
	public ListNode removeElements(ListNode head, int val) {
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode p = helper;

		while (p.next != null) {
			if (p.next.val == val) {
				ListNode next = p.next;
				p.next = next.next;
			} else {
				p = p.next;
			}
		}

		return helper.next;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2==null)
        {
            return null;
        }
        
        int count=0;
        ListNode head=null;
        ListNode node=null;
        
        while(l1 !=null || l2 != null)
        {
            int l1Val= (l1!=null?l1.val:Integer.MAX_VALUE); 
            int l2Val= (l2!=null?l2.val:Integer.MAX_VALUE);
            ListNode temp=null;
            if(l1Val <= l2Val)
            {
            	temp = new ListNode(l1Val);
            	l1=l1.next;
            }
            else if(l2Val < l1Val)
            {
            	temp = new ListNode(l2Val);
            	l2=l2.next;
            }
            if(count == 0)
            {
            	node=temp;
                head= node;
            }
            else
            {
            	node.next=temp;
            	node=node.next;
            }
            count++;      
            
        }
        return head;
    }

	public static void main(String[] args) {
		ListNode l1 = null;//new ListNode(1);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next= new ListNode(4);
		
		mergeTwoLists(l1, l2);
	}
}
