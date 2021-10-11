package bank;

import java.util.ArrayList;
import java.util.HashMap;

class Bank2 {
	public String name;
	public HashMap<Integer, Account> accounts;

	public Bank2(String name) {
		this.name = name;
		accounts = new HashMap<Integer, Account>();
	}

	public Account findAccount(int id) {
		// find the account for the given id. Returns null if not found
		for (Integer key : accounts.keySet()) {
			if (key == id) {
				return accounts.get(id);
			}
		}
		return null;

	}

	public boolean addAccount(Account account) {
		if (findAccount(account.getID()) == null) {
			accounts.put(account.getID(), account);
			return true;
		}
		return false;

	}

	public void printAccounts() {
		for (Account acc : accounts.values()) {
			acc.print();
		}
	}

	public HashMap<String, Double> getTotalBalancePerCity() {
		HashMap<String, Double> getTotalBalancePerCity = new HashMap<>();
		for (Integer key : accounts.keySet()) {
			String city = accounts.get(key).getCity();

			Object balance = getTotalBalancePerCity.get(city);

			if (balance != null) {
				getTotalBalancePerCity.put(city, accounts.get(key).getBalance() + (Double) balance);
			} else {
				getTotalBalancePerCity.put(city, accounts.get(key).getBalance());
			}

		}
		return getTotalBalancePerCity;
	}

	public HashMap<String, Integer> getTotalCountPerCity() {
		HashMap<String, Integer> getTotalCountPerCity = new HashMap<>();
		for (Integer id : accounts.keySet()) {
			if (contains(getTotalCountPerCity, accounts.get(id).getCity())) {
				Object count = getTotalCountPerCity.get(accounts.get(id).getCity());
				getTotalCountPerCity.put(accounts.get(id).getCity(), (int) count + 1);
			} else {
				getTotalCountPerCity.put(accounts.get(id).getCity(), 1);
			}
		}
		return getTotalCountPerCity;

	}

	private <K, V> boolean contains(HashMap<K, V> hashmap, K key) {
		for (K k : hashmap.keySet()) {
			if (key.equals(k)) {
				return true;
			}
		}
		return false;
	}

	public void reportCity(HashMap<String, Double> balances, HashMap<String, Integer> counts) {
		System.out.println();
		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
		for (String city : balances.keySet()) {
			System.out.println(
					city + "\t \t " + balances.get(city) + " \t \t " + balances.get(city) / (double) counts.get(city));
		}
	}

	public HashMap<Integer, Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
		HashMap<Integer, Integer> totalCountPerRange = new HashMap<>();
		for (int i = 0; i < ranges.size() - 1; i++) {
			totalCountPerRange.put(i, 0);
			for (Integer id : accounts.keySet()) {
				double bal = accounts.get(id).getBalance();
				if (ranges.get(i) <= bal && bal <= ranges.get(i + 1)) {
					Object count = totalCountPerRange.get(i);
					if (count == null) {
						totalCountPerRange.put(i, 1);
					} else {
						totalCountPerRange.put(i, (int) count + 1);
					}
				}
			}
		}

		return totalCountPerRange;
	}

	public void reportRanges(ArrayList<Integer> ranges, HashMap<Integer, Integer> countsPerRange) {
		for (int i = 0; i < ranges.size() - 1; i++) {
			System.out.println("range: " + ranges.get(i) + "-" + ranges.get(i + 1) + " -> " + countsPerRange.get(i));
		}
	}

}
