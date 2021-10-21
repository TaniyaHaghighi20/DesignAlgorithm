package bank;

import java.util.ArrayList;

public class Bank5 extends GeneralBank {
	// attributes
	public String name;
	public BinarySearchTreeForBank5<Integer, Account> accounts;

	// constructor
	public Bank5(String name) {
		this.name = name;
		accounts = new BinarySearchTreeForBank5<Integer, Account>();
	}

	@Override
	public String getName() {
		return name;
	}

	// methods
	/*
	 * find account in binary search tree which contains accounts
	 */
	@Override
	public Account findAccount(int id) {
		// find the account for the given id. Returns null if not found
		return accounts.search(id);
	}

	/*
	 * add account in binary search tree which contains accounts checks weather
	 * account exists in the binary search tree
	 */
	@Override
	public boolean addAccount(Account account) {
		if (findAccount(account.getID()) == null) {
			accounts.insert(account.getID(), account);
			return true;
		}
		return false;

	}

	/*
	 * print accounts in binary search tree which contains accounts
	 */
	@Override
	public void printAccounts() {
		accounts.print();
	}

	/*
	 * calculate total balance of the bank
	 */
//	@Override
	public double calcTotalBalance() {
		double total = 0.0;
		accounts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Account>.Node> allAccounts = accounts.getItems();
		for (BinarySearchTreeForBank5<Integer, Account>.Node node : allAccounts) {
			total += node.getItem().getData().getBalance();
		}
		return total;
	}

	/*
	 * lists all the cities that an account have made from there
	 */
//	@Override
	public Object populateDistinctCityList() {
		ArrayList<String> cities = new ArrayList<>();

		accounts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Account>.Node> allAccounts = accounts.getItems();
		for (BinarySearchTreeForBank5<Integer, Account>.Node node : allAccounts) {
			cities.add(node.getItem().getData().getCity());
		}
		return cities;
	}

	/*
	 * returns a binary search tree with total balance in each city
	 */
	@Override
	public BinarySearchTreeForBank5<String, Double> getTotalBalancePerCity() {
		BinarySearchTreeForBank5<String, Double> getTotalBalancePerCity = new BinarySearchTreeForBank5<>();

		ArrayList<BinarySearchTreeForBank5<Integer, Account>.Node> allAccounts = accounts.getItems();
		for (BinarySearchTreeForBank5<Integer, Account>.Node node : allAccounts) {
			if (!(getTotalBalancePerCity.insert(node.getItem().getData().getCity(),
					node.getItem().getData().getBalance()))) {
				double balance = getTotalBalancePerCity.search(node.getItem().getData().getCity());
				balance = balance + node.getItem().getData().getBalance();
				getTotalBalancePerCity.delete(node.getItem().getData().getCity());
				getTotalBalancePerCity.insert(node.getItem().getData().getCity(), balance);
			}
		}
		return getTotalBalancePerCity;
	}

	/*
	 * returns a binary search tree with total count in each city
	 */
	@Override
	public BinarySearchTreeForBank5<String, Integer> getTotalCountPerCity() {
		BinarySearchTreeForBank5<String, Integer> getTotalCountPerCity = new BinarySearchTreeForBank5<>();
		accounts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Account>.Node> allAccounts = accounts.getItems();

		for (BinarySearchTreeForBank5<Integer, Account>.Node node : allAccounts) {
			if (!(getTotalCountPerCity.insert(node.getItem().getData().getCity(), 1))) {
				int balance = getTotalCountPerCity.search(node.getItem().getData().getCity());
				balance = balance + 1;
				getTotalCountPerCity.delete(node.getItem().getData().getCity());
				getTotalCountPerCity.insert(node.getItem().getData().getCity(), balance);
			}

		}
		return getTotalCountPerCity;
	}

	/*
	 * reports all cities with their balance and the average of their balances
	 */
	@Override
	public void reportCity() {

		BinarySearchTreeForBank5<String, Double> balances = getTotalBalancePerCity();
		BinarySearchTreeForBank5<String, Integer> counts = getTotalCountPerCity();
		System.out.println();
		balances.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<String, Double>.Node> balances1 = balances.getItems();

		counts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<String, Integer>.Node> counts1 = counts.getItems();

		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
		for (int i = 0; i < balances1.size(); i++) {
			String city = (balances1.get(i).getItem().getKey());
			Double tBalance = (balances1.get(i).getItem().getData());
			System.out.println(
					city + "\t \t " + tBalance + " \t \t " + (tBalance / (double) counts1.get(i).getItem().getData()));

		}
	}

	/*
	 * returns a list of total counts per ranges
	 */
	@Override
	public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
		ArrayList<Integer> getTotalCountPerRange = new ArrayList<Integer>();
		BinarySearchTreeForBank5<Integer, Integer> counts = getCountPerRange(ranges);
		counts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Integer>.Node> allAccounts = counts.getItems();
		for (BinarySearchTreeForBank5<Integer, Integer>.Node node : allAccounts) {
			Integer count = node.getItem().getData();
			Integer maxRange = node.getItem().getKey();
			int index = 0;
			for (int i = 0; i < ranges.size(); i++) {
				if (ranges.get(i) == maxRange) {
					index = i - 1;
					break;
				}
			}
			getTotalCountPerRange.add(index, count);
		}
		return getTotalCountPerRange;
	}

	/*
	 * returns a binary search tree with counts of each specific range
	 */
	public BinarySearchTreeForBank5<Integer, Integer> getCountPerRange(ArrayList<Integer> ranges) {
		BinarySearchTreeForBank5<Integer, Integer> getTotalCountPerRange = new BinarySearchTreeForBank5<>();
		accounts.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Account>.Node> allAccounts = accounts.getItems();
		for (int i = 0; i < ranges.size() - 1; i++) {
			getTotalCountPerRange.insert(ranges.get(i + 1), 0);
			for (BinarySearchTreeForBank5<Integer, Account>.Node node : allAccounts) {
				Account temp = node.getItem().getData();
				if (ranges.get(i) <= temp.getBalance() && temp.getBalance() < ranges.get(i + 1)) {
					int index = getTotalCountPerRange.search(ranges.get(i + 1));
					int value = index + 1;
					getTotalCountPerRange.delete(ranges.get(i + 1));
					getTotalCountPerRange.insert(ranges.get(i + 1), value);
				}
			}
		}
		return getTotalCountPerRange;
	}

	/*
	 * reports count of each range in bank
	 */
	@Override
	public void reportRanges(ArrayList<Integer> ranges) {
		BinarySearchTreeForBank5<Integer, Integer> countsPerRange = getCountPerRange(ranges);
		countsPerRange.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<Integer, Integer>.Node> countsPerRange1 = countsPerRange.getItems();
		System.out.println();
		for (int i = 0; i < ranges.size() - 1; i++) {
			System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = "
					+ countsPerRange1.get((i)).getItem().getData());
		}
		System.out.println();
	}

	/*
	 * string for printing bank5
	 */
	@Override
	public String toString() {
		return "Bank5{" + "name='" + name + '\'' + ", accounts=" + accounts + '}';
	}

	/*
	 * reports cities and their balances and average of balances
	 */
	public void reportCity(ArrayList<String> cities, BinarySearchTreeForBank5<String, Double> balances3,
			BinarySearchTreeForBank5<String, Integer> counts3) {
		System.out.println();

		balances3.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<String, Double>.Node> balances1 = balances3.getItems();

		counts3.printLevelOrder();
		ArrayList<BinarySearchTreeForBank5<String, Integer>.Node> counts1 = counts3.getItems();

		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
		for (int i = 0; i < balances1.size(); i++) {
			String city = (balances1.get(i).getItem().getKey());
			Double tBalance = (balances1.get(i).getItem().getData());
			System.out.println(
					city + "\t \t " + tBalance + " \t \t " + (tBalance / (double) counts1.get(i).getItem().getData()));

		}
	}
}
