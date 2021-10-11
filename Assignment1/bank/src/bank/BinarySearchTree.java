package bank;

public class BinarySearchTree<K, T extends Comparable<T>> {
	class Node<K, T> {
		Item<K, T> item;
		Node<K, T> left;
		Node<K, T> right;

		public Node(K key, T data) {
			item = new Item<K, T>(key, data);
			left = null;
			right = null;
		}
	}

	private Node<K, T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	public Object search(K key) {
		Node<K, T> current = root;
		while (current != null) {
			if (current.item.key.equals(key)) {
				return current.item.data;
			} else if (((Comparable<K>) current.item.key).compareTo(key) > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}

	public boolean delete(K key) {
		Node<K, T> parent = root;
		Node<K, T> current = root;
		boolean isLeftChild = false;
		while (!current.item.key.equals(key)) {
			parent = current;
			if (((Comparable<K>) current.item.key).compareTo(key) > 0) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				return false;
			}
		}
		// If program pointer is here that means we have found the node
		// Case 1: if node to be deleted has no children
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			}
			if (isLeftChild == true) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// Case 2 : if node to be deleted has only one child
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.left != null && current.right != null) {
			// now we have found the minimum element in the right sub-tree
			Node<K, T> successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}

	public Node<K, T> getSuccessor(Node<K, T> deleleNode) {
		Node<K, T> successsor = null;
		Node<K, T> successsorParent = null;
		Node<K, T> current = deleleNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// Check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
		// successsorParent
		if (successsor != deleleNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}

	public boolean insert(K key, T data) {
		if (search(key) != null)
			return false;
		Node<K, T> newNode = new Node(key, data);
		if (root == null) {
			root = newNode;
			return true;
		}
		Node<K, T> current = root;
		Node<K, T> parent = null;
		while (true) {
			parent = current;
			if (((Comparable<K>) current.item.key).compareTo(key) > 0) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return true;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return true;
				}
			}
		}
	}

	public void display(Node<K, T> root) {
		if (root != null) {
			display(root.left);
			System.out.print(root.item);
			display(root.right);
		}
	}

	public void print() {
		if (root != null) {
			display(root);
			System.out.println();
		} else {
			System.out.println("Empty Tree");
		}
	}

	public Node<K, T> getRoot() {
		return root;
	}

	public void setRoot(Node<K, T> root) {
		this.root = root;
	}

}
