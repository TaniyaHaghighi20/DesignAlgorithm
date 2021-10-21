/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.*;
import java.util.ArrayList;

public class BinarySearchTreeForBank5<T, E> extends SearchStructure<T, E> {

	private ArrayList<Node> items = new ArrayList<>();



	public ArrayList<Node> getItems() {
		return items;
	}

	public class Node {
		Item<T, E> item;
		Color color;
		Node left;
		Node right;

		public Node(T key, E data) {
			item = new Item(key, data);
			left = null;
			right = null;
			color = Color.white;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public Item<T, E> getItem() {
			return item;
		}
	}

	public Node root;

	public BinarySearchTreeForBank5() {
		this.root = null;
	}

	@Override
	public E search(T key) {
		Node current = root;
		while (current != null) {
			if (current.item.key.equals(key)) {
				return current.item.data;
			} else if (current.item.compareTo(key) > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}

	@Override
	public boolean delete(T key) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (!current.item.key.equals(key)) {
			parent = current;
			if (current.item.compareTo(key) > 0) {
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
			Node successor = getSuccessor(current);
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

	public Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.right;
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

	@Override
	public boolean insert(T key, E data) {
		if (search(key) != null)
			return false;
		Node newNode = new Node(key, data);
		if (root == null) {
			root = newNode;
			return true;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (current.item.compareTo(key) > 0) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					items.clear();
					return true;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					items.clear();
					return true;
				}
			}
		}
	}

	public void display(Node root) {
		if (root != null) {
			display(root.left);
			System.out.print(root.item);
			display(root.right);
		}
	}

	public void printLevelOrder() {
		int h = height(root);
		int i;
		if (items.isEmpty()) {
			for (i = 1; i <= h; i++) {
				printCurrentLevel(root, i);
			}
		}
	}

	/*
	 * Compute the "height" of a tree -- the number of nodes along the longest path
	 * from the root node down to the farthest leaf node.
	 */
	public int height(Node root) {
		if (root == null)
			return 0;
		else {
			/* compute height of each subtree */
			int lheight = height(root.left);
			int rheight = height(root.right);

			/* use the larger one */
			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	/* Print nodes at the current level */
	public void printCurrentLevel(Node root, int level) {
		if (root == null)
			return;
		if (level == 1)
			items.add(root);
		else if (level > 1) {
			printCurrentLevel(root.left, level - 1);
			printCurrentLevel(root.right, level - 1);
		}
	}

	@Override
	public void print() {
		if (root != null) {
			display(root);
			System.out.println();
		} else {
			System.out.println("Empty Tree");
		}
	}
}
