package bank;

public class Item<K, T> {
	public K key;
	public T data;

	public Item() {
		// key=0;
		// data=0;
	}

	public Item(K key, T data) {
		this.key = key;
		this.data = data;
	}

	@Override
	public String toString() {
		return key + " -> " + data + " , ";
	}
}
