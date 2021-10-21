package bank;

import static java.lang.Math.abs;

public class MyHashMap<T, E> {
//	------------------attributes

    private MyLinkedList<T, E>[] myHashTable;

    //	-----------------constructor
    public MyHashMap() {
        super();
        this.myHashTable = new MyLinkedList[20000];
        for (int i = 0; i < myHashTable.length; i++) {
            myHashTable[i] = new MyLinkedList<T, E>();
        }

    }
//	-----------------methods

    public boolean insert(T key, E data) {
        int hash = hashFunction(key);
        if (hash >= 0) {
            return myHashTable[hash].insert(key, data);
        } else {
            return false;
        }
    }

    public E search(T key) {
        int hash = hashFunction(key);
        if (hash >= 0) {
            return myHashTable[hash].search(key);
        } else {
            return null;
        }
    }

    public boolean delete(T data) {
        int hash = hashFunction(data);
        return myHashTable[hash].delete(data);
    }

    public E get(T key) {
        return search(key);
    }

    public void print() {
        for (MyLinkedList<T, E> myLinkedList : myHashTable) {
            myLinkedList.print();
        }
    }


    /**
     * hash function
     *
     * @param key the key number of the hash map
     * @return hash
     */
    private int hashFunction(T key) {
        int i = key.hashCode();
        return i % 20000;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("MyHashTable [");
        for (MyLinkedList<T, E> linkedList : myHashTable) {
            linkedList.print();
        }
        str.append("]");
        return str.toString();
    }
/*
	public int countOfEmpty() {
		int count = 0;
		for (int i = 0; i < myHashTable.length; i++) {
			if (myHashTable[i].isEmpty()) {
				count++;
			}
		}
		return count;
	}*/
/*
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
*/


    //	-----------------getters and setters
    public MyLinkedList<T, E>[] getMyHashTable() {
        return myHashTable;
    }

    public void setMyHashTable(MyLinkedList<T, E>[] myHashTable) {
        this.myHashTable = myHashTable;
    }

//	----------------------------------------------------------
}
