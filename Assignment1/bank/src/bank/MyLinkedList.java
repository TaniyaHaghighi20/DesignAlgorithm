package bank;

public class MyLinkedList<T> {
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates and open the template
	 * in the editor.
	 */

	class Node {
		Item item;
		Node next;

		// Node previous;// double direction
		public Node(T key, T data) {
			this.item = new Item(key, data);
			next = null;
			// previous = null;
		}
	}

//	public static Node head = null;
	private Node head;
	private int length;

	public MyLinkedList() {
		super();
		this.length = 0;
		this.head = null;
	}

	public boolean insert(T key, T data) {
		Node node = new Node(key, data);
		node.next = null;
		if (head == null)
			head = node;
		else {
			Node last = head;
			while (last.next != null) {
				if (last.item.key.equals(key))
					return false;// unique key instead of search
				last = last.next;
			}
			last.next = node;
		}
		length++;
		return true;
	}

	public boolean delete(T key) {
		if (head == null)
			return false;
		if (head.item.key.equals(key)) {
			head = head.next;
			length--;
			return true;
		}
		Node prev = head;
		Node pointer = head.next;
		while (pointer != null) {
			if (pointer.item.key.equals(key)) {
				prev = pointer.next;
				return true;
			}
			prev = pointer;
			pointer = pointer.next;
		}
		return false;
	}

	public Object search(T key) {
		Node pointer = head;
		while (pointer != null) {
			if (pointer.item.key.equals(key)) {
				return pointer.item.data;
			}
			pointer = pointer.next;
		}
		return null;
	}

	public void print() {
		Node pointer = head;
		while (pointer != null) {
			System.out.print(pointer.item);
			pointer = pointer.next;
		}
		System.out.println();
	}

	public int getLength() {
		return length;
	}

	public boolean isEmpty() {
		if (getHead() == null) {
			return true;
		}
		return false;
	}

	public Node getHead() {
		return head;
	}

}
