package bank;

public class MyHashTable<T> {
//	------------------attributes
	private MyLinkedList<T>[] myHashTable;

//	-----------------constructor
	public MyHashTable() {
		super();
		this.myHashTable = new MyLinkedList[100];
		for (int i = 0; i < myHashTable.length; i++) {
			myHashTable[i] = new MyLinkedList<T>();
		}

	}
//	-----------------methods

	public boolean insert(T data) {
		int hash = hashFunction(data);
		return myHashTable[hash].insert(data, data);
	}

	public Object search(T data) {
		int hash = hashFunction(data);
		return myHashTable[hash].search(data);
	}

	public boolean delete(T data) {
		int hash = hashFunction(data);
		return myHashTable[hash].delete(data);
	}

	public void print() {
		for (MyLinkedList<T> myLinkedList : myHashTable) {
			myLinkedList.print();
		}
	}

	public int countOfEmpty() {
		int count = 0;
		for (int i = 0; i < myHashTable.length; i++) {
			if (myHashTable[i].isEmpty()) {
				count++;
			}
		}
		return count;
	}

	public int longestLength() {
		int max = 0;
		for (int i = 0; i < myHashTable.length; i++) {
			int length = myHashTable[i].getLength();
			if (length > max) {
				max = length;
			}
		}
		return max;
	}

	private int hashFunction(T key) {
		return key.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("MyHashTable [");
		for (MyLinkedList<T> linkedList : myHashTable) {
			linkedList.print();
		}
		str.append("]");
		return str.toString();
	}

//	-----------------getters and setters
	public MyLinkedList<T>[] getMyHashTable() {
		return myHashTable;
	}

	public void setMyHashTable(MyLinkedList<T>[] myHashTable) {
		this.myHashTable = myHashTable;
	}

}
