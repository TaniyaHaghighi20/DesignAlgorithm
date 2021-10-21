/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

public class MyLinkedList<T, E> extends SearchStructure<T, E> {
	private Node<T,E> head = null;
//	public int length = 0;

	//--------------------------------------------------------Accessor-------------------------------------------------

	public Node<T,E> getHead() {
		return head;
	}


	//--------------------------------------------------------Methods-------------------------------------------------

	/**
	 * This method insert a node to the linkedList (not accept duplicate)
	 * @param key the key of the item
	 * @param data the data of the item
	 * @return true if the key is unique
	 */
	@Override
	public boolean insert(T key, E data) {
		Node<T,E> node = new Node(key, data);
		node.next = null;
		if (head == null)
			head = node;
		else {
			Node<T, E> last = head;
			while (last.next != null) {
				if (last.item.key.equals(key)) {
					return false;// unique key instead of search
				} else {
					last = last.next;
				}
			}
			if (last == head) {
				if (head.item.key.equals(key)) {
					return false;
				}
			}
			last.next = node;
		}
//		length++;
		return true;
	}

	@Override
	public boolean delete(T key) {
		if (head == null)
			return false;
		if (head.item.key.equals(key)) {
			head = head.next;
			return true;
		}
		Node<T,E> prev = head;
		Node<T,E> pointer = head.next;
		while (pointer != null) {
			if (pointer.item.key.equals(key)) {
				prev.next = pointer.next;
				return true;
			}
			prev = pointer;
			pointer = pointer.next;
		}
		return false;
	}

	@Override
	public E search(T key) {
		Node<T,E> pointer = head;
		while (pointer != null) {
			if (pointer.item.key.equals(key)) {
				return pointer.item.data;
			}
			pointer = pointer.next;
		}
		return null;
	}

	@Override
	public void print() {
		Node<T, E> pointer = head;
		while (pointer != null) {
			System.out.print(pointer.item);
			pointer = pointer.next;
		}
		System.out.println();
	}

   /* @Override
    public String toString() {
        Node<T,E> nextNode = head;
        StringBuilder sb = new StringBuilder();
        while (nextNode == null) {
            sb.append(nextNode.toString());
            nextNode = head.next;
        }
        return sb.toString();

    }*/
}
