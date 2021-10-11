package bank;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank4 {
	public String name;
	public BinarySearchTree<Integer, Account> accounts;

	public Bank4(String name) {
		this.name = name;
		accounts = new BinarySearchTree<Integer, Account>();
	}

	public Account findAccount(int id) {
		// find the account for the given id. Returns null if not found
		return (Account) accounts.search(id);
	}

	public boolean addAccount(Account account) {
		return accounts.insert(account.getID(), account);
	}

	public void printAccounts() {
		accounts.print();
	}

	public HashMap<String, Double> getTotalBalancePerCity() {

		return null;
	}

	public HashMap<String, Integer> getTotalCountPerCity() {

		return null;

	}

	public void reportCity(HashMap<String, Double> balances, HashMap<String, Integer> counts) {

	}

	public HashMap<Integer, Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {

		return null;
	}

	public void reportRanges(ArrayList<Integer> ranges, HashMap<Integer, Integer> countsPerRange) {

	}
}
