/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 * @author Hooman
 */
public class Item<T, E> implements Comparable<T> {
	public T key;
	public E data;

	public Item() {
		// key=0;
		// data=0;
	}

	public Item(T key, E data) {
		this.key = key;
		this.data = data;
	}

	@Override
	public String toString() {
		return key + " -> " + data + " , ";
	}

	public T getKey() {
		return key;
	}

	public E getData() {
		return data;
	}

	@Override
	public int compareTo(T o) {
		if (o instanceof String) {
			if (((String) this.key).compareTo((String) o) == 1) {
				return 1;
			}
			return 0;
		} else {
			if ((Integer) this.key > (Integer) o) {
				return 1;
			}
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
