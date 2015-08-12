package com.mukeshdua.algorithms;

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

	// Check if linked list is palindrome
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

	// Reverse the link list
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

}
