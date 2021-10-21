package bank;

public class Node<T, E> {
	public Item<T, E> item;
	public Node<T,E> next;

	// Node previous;// double direction
	public Node(T key, E data) {
		this.item = new Item(key, data);
		next = null;
		// previous = null;
	}

	@Override
	public String toString() {
		return "Node{" + "item=" + item + ", next=" + next + '}';
	}
}
