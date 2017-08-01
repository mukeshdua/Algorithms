package com.mukeshdua.algorithms;

import java.util.NoSuchElementException;

public class DoubleLinkList {
	
		 
	    private ListDoubleNode head;
	    private ListDoubleNode tail;
	    private int size;
	     
	    public DoubleLinkList() {
	        size = 0;
	    }
	   
	    /**
	     * returns the size of the linked list
	     * @return
	     */
	    public int size() { return size; }
	     
	    /**
	     * return whether the list is empty or not
	     * @return
	     */
	    public boolean isEmpty() { return size == 0; }
	     
	    /**
	     * adds element at the starting of the linked list
	     * @param element
	     */
	    public void addFirst(int value) {
	        ListDoubleNode tmp = new ListDoubleNode(value, head, null);
	        if(head != null ) {head.prev = tmp;}
	        head = tmp;
	        if(tail == null) { tail = tmp;}
	        size++;
	        System.out.println("adding: "+value);
	    }
	     
	    /**
	     * adds element at the end of the linked list
	     * @param element
	     */
	    public void addLast(int value) {
	         
	        ListDoubleNode tmp = new ListDoubleNode(value, null, tail);
	        if(tail != null) {tail.next = tmp;}
	        tail = tmp;
	        if(head == null) { head = tmp;}
	        size++;
	        System.out.println("adding: "+value);
	    }
	     
	    /**
	     * this method walks forward through the linked list
	     */
	    public void iterateForward(){
	         
	        System.out.println("iterating forward..");
	        ListDoubleNode tmp = head;
	        while(tmp != null){
	            System.out.println(tmp.value);
	            tmp = tmp.next;
	        }
	    }
	     
	    /**
	     * this method walks backward through the linked list
	     */
	    public void iterateBackward(){
	         
	        System.out.println("iterating backword..");
	        ListDoubleNode tmp = tail;
	        while(tmp != null){
	            System.out.println(tmp.value);
	            tmp = tmp.prev;
	        }
	    }
	     
	    /**
	     * this method removes element from the start of the linked list
	     * @return
	     */
	    public int removeFirst() {
	        if (size == 0) throw new NoSuchElementException();
	        ListDoubleNode tmp = head;
	        head = head.next;
	        head.prev = null;
	        size--;
	        System.out.println("deleted: "+tmp.value);
	        return tmp.value;
	    }
	     
	    /**
	     * this method removes element from the end of the linked list
	     * @return
	     */
	    public int removeLast() {
	        if (size == 0) throw new NoSuchElementException();
	        ListDoubleNode tmp = tail;
	        tail = tail.prev;
	        tail.next = null;
	        size--;
	        System.out.println("deleted: "+tmp.value);
	        return tmp.value;
	    }
	     
	    public static void main(String a[]){
	         
	    	DoubleLinkList dll = new DoubleLinkList();
	        dll.addFirst(10);
	        dll.addFirst(34);
	        dll.addLast(56);
	        dll.addLast(364);
	        dll.iterateForward();
	        dll.removeFirst();
	        dll.removeLast();
	        dll.iterateBackward();
	    }
	
}
