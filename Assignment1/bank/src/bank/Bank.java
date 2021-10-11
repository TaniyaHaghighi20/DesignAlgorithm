package bank;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<Account> accounts;

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<Account>();
	}

	public Account findAccount(int id) { // find the account for the given id. Returns null if not found
		for (Account acc : accounts) {
			if (acc.getID() == id) {
				return acc;
			}
		}
		return null;
	}

	public boolean addAccount(Account account) {
		if (findAccount(account.getID()) == null) {
			accounts.add(account);
			return true;
		}
		return false;

	}

	public void printAccounts() {
		for (Account acc : accounts) {
			acc.print();
		}
	}

	public double calcTotalBalance() {
		double total = 0;
		for (Account acc : accounts) {
			total += acc.getBalance();
		}
		return total;
	}

	public ArrayList<String> populateDistinctCityList() {
		// implement with set. It has space complexity more than the other way.
//        Set<String> cityNames = new HashSet<>();
		ArrayList<String> cities = new ArrayList<>();
		boolean isExist = false;
		for (Account acc : accounts) {
			String city = acc.getCity();
//            cityNames.add(city);
			for (String key : cities) {
				if (key.equalsIgnoreCase(city)) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				cities.add(city);

			}
			isExist = false;
		}
//        cities.addAll(cityNames);
		return cities;
	}

	public ArrayList<Double> getTotalBalancePerCity(ArrayList<String> cities) {
		ArrayList<Double> tbc = new ArrayList<>();
		tbc.add(0.0);

		// way 1
		/*
		 * if we use an array for store the remain indexes (for example at the first
		 * trace in account arraylist we find the acc located in tehran and in the each
		 * time we remove the index of the acc from the 'remainIndex' arraylist), it
		 * takes O(n) to create this arraylist and (for example if we have 10 cities and
		 * in each city we have 1m acc), in the next time when we want to trace the
		 * accounts arraylist we need to trace 9m... next time 8m and .... 1m. in this
		 * way we have O(n)+O(n-alpha)+O(n-beta)+...+O(n-gamma), but if we don't use
		 * this arraylist we need to trace account arraylist 10 times and in each time
		 * we should trace all accounts and it take O(n)+O(n)+...+O(n)
		 */
//        ArrayList<Integer> remainIndexes = new ArrayList<>();
//        for (int i = 0; i < accounts.size(); i++) {
//            remainIndexes.add(i);
//        }

		// way 2:
//        for (int i = 0; i < cities.size(); i++) {
//            for (Account account : accounts) {
//                if (cities.get(i).equals(account.getCity())) {
//                    double prev =tbc.get(i) ;
//                    prev += account.getBalance();
//                    tbc.add(i, prev);
//                }
//            }
//        }

		// way 3:
		for (Account acc : accounts) {
			String city = acc.getCity();
			int index = cities.indexOf(city);
			if (tbc.get(index) == 0.0) {
				tbc.add(index, 0.0);
			}
			tbc.set(index, tbc.get(index) + acc.getBalance());
		}

		// way 4:
//        HashMap<String, Integer> indexCities = new HashMap<>();
//        for (String city : cities) {
//            indexCities.put(city, cities.indexOf(city));
//        }
//        for (Account acc : accounts) {
//            String city = acc.getCity();
//            int index = indexCities.get(city);
//            tbc.add(index, tbc.get(index) + acc.getBalance());
//        }
		return tbc;
	}

	public ArrayList<Integer> getTotalCountPerCity(ArrayList<String> cities) {
		ArrayList<Integer> tcc = new ArrayList<>();
		// tcc.add(0);
		// way 1:
//        for (int i = 0; i < cities.size(); i++) {
//            for (Account account : accounts) {
//                if (cities.get(i).equals(account.getCity())) {
//                    int prev =tcc.get(i) ;
//                    tcc.add(i, prev+1);
//                }
//            }
//        }

		// way 2:

		for (int i = 0; i < cities.size(); i++) {
			tcc.add(0);
		}

		for (int i = 0; i < cities.size(); i++) {
			for (Account acc : accounts) {
				if (acc.getCity().equalsIgnoreCase(cities.get(i))) {
					int no = tcc.get(i) + 1;
					tcc.set(i, no);

				}
			}
		}
//        for (Account acc : accounts) {
//            String city = acc.getCity();
//            int index = cities.indexOf(city);
//            boolean isExist = false;
//            int counter;

//            if (tcc.get(index) == 0) {
//                tcc.add(index, 0);
//            }
//            tcc.add(index, tcc.get(index) + 1);
//        }

		// way 3:
//        HashMap<String, Integer> indexCities = new HashMap<>();
//        for (String city : cities) {
//            indexCities.put(city, cities.indexOf(city));
//        }
//        for (Account acc : accounts) {
//            String city = acc.getCity();
//            int index = indexCities.get(city);
//            tcc.add(index, tcc.get(index) + 1);
//        }
		return tcc;

	}

	public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts, ArrayList<Double> totals) {
		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
		for (int i = 0; i < counts.size(); i++)
			System.out.println(
					cities.get(i) + " \t \t " + totals.get(i) + " \t \t " + totals.get(i) / (double) counts.get(i));
	}

	public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
		ArrayList<Integer> tcp = new ArrayList<>();
		int counter;
		for (int i = 0; i < ranges.size() - 1; i++) {
			counter = 0;
			for (Account acc : accounts) {
				if (ranges.get(i) <= acc.getBalance() && acc.getBalance() < ranges.get(i + 1)) {
					counter++;
					tcp.add(i, counter);
				}
			}
		}
		return tcp;
	}

	public void reportRanges(ArrayList<Integer> ranges, ArrayList<Integer> counts) {
		System.out.println();
		for (int i = 0; i < ranges.size() - 1; i++) {
			System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = "
					+ counts.get(i));
		}
		System.out.println();
	}
}
