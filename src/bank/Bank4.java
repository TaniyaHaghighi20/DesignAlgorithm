package bank;


import java.util.ArrayList;

public class Bank4 extends GeneralBank {
    // attributes
    public String name;
    public BinarySearchTree<Integer, Account> accounts;

    // constructor
    public Bank4(String name) {
        this.name = name;
        accounts = new BinarySearchTree<Integer, Account>();
    }

    @Override
    public String getName() {
        return name;
    }

    //----------------------------------------------------------methods-----------------------------------------------
    /**
     * find account in binary search tree which contains accounts
     */
    @Override
    public Account findAccount(int id) {
        return accounts.search(id);
    }

    /**
     * add account in binary search tree which contains accounts
     */
    @Override
    public boolean addAccount(Account account) {
        return accounts.insert(account.getID(), account);
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
        BinarySearchTree<Integer, Account>.Node currentNode = accounts.root;
        while (currentNode != null) {
            total += currentNode.getItem().getData().getBalance();
            currentNode = accounts.next(currentNode);
        }
        return total;
    }

    /*
     * lists all the cities that an account have made from there
     */
//	@Override
    public Object populateDistinctCityList() {
        ArrayList<String> cities = new ArrayList<>();
        BinarySearchTree<Integer, Account>.Node currentNode = accounts.root;
        while (currentNode != null) {
            cities.add(currentNode.getItem().getData().getCity());
            currentNode = accounts.next(currentNode);
        }
        return cities;
    }

    /*
     * returns a binary search tree with total balance in each city
     */
    @Override
    public BinarySearchTree<String, Double> getTotalBalancePerCity() {
        BinarySearchTree<String, Double> getTotalBalancePerCity = new BinarySearchTree<>();
        BinarySearchTree<Integer, Account>.Node currentNode = accounts.root;
        while (currentNode != null) {
            if (!(getTotalBalancePerCity.insert(currentNode.getItem().getData().getCity(),
                    currentNode.getItem().getData().getBalance()))) {
                double balance = getTotalBalancePerCity.search(currentNode.getItem().getData().getCity());
                balance = balance + currentNode.getItem().getData().getBalance();
                getTotalBalancePerCity.delete(currentNode.getItem().getData().getCity());
                getTotalBalancePerCity.insert(currentNode.getItem().getData().getCity(), balance);
            }
            currentNode = accounts.next(currentNode);
        }
        return getTotalBalancePerCity;
    }

    /*
     * returns a binary search tree with total count in each city
     */
    @Override
    public BinarySearchTree<String, Integer> getTotalCountPerCity() {

        BinarySearchTree<String, Integer> getTotalCountPerCity = new BinarySearchTree<>();

        BinarySearchTree<Integer, Account>.Node currentNode = accounts.root;
        while (currentNode != null) {
            if (!(getTotalCountPerCity.insert(currentNode.getItem().getData().getCity(), 1))) {
                int balance = getTotalCountPerCity.search(currentNode.getItem().getData().getCity());
                balance = balance + 1;
                getTotalCountPerCity.delete(currentNode.getItem().getData().getCity());
                getTotalCountPerCity.insert(currentNode.getItem().getData().getCity(), balance);
            }
            currentNode = accounts.next(currentNode);
        }
        return getTotalCountPerCity;
    }

    /*
     * reports all cities with their balance and the average of their balances
     */
    @Override
    public void reportCity() {

        BinarySearchTree<String, Double> balances = getTotalBalancePerCity();
        BinarySearchTree<String, Integer> counts = getTotalCountPerCity();
        System.out.println();

        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        BinarySearchTree<String, Double>.Node currentBalance = balances.root;
        BinarySearchTree<String, Integer>.Node currentCount = counts.root;

        while (currentBalance != null) {
            System.out.println(currentBalance.getItem().getKey() + "\t \t " + currentBalance.getItem().getData()
                    + " \t \t " + (currentBalance.getItem().getData() / (double) currentCount.getItem().getData()));
            currentCount = counts.next(currentCount);
            currentBalance = balances.next(currentBalance);
        }
    }

    /*
     * returns a binary search tree with counts of each specific range
     */
    private BinarySearchTree<Integer, Integer> getCountPerRange(ArrayList<Integer> ranges) {
        BinarySearchTree<Integer, Integer> getTotalCountPerRange = new BinarySearchTree<>();
        for (int i = 0; i < ranges.size() - 1; i++) {
            getTotalCountPerRange.insert(ranges.get(i + 1), 0);
            BinarySearchTree<Integer, Account>.Node currentNode = accounts.root;
            while (currentNode != null) {
                Account temp = currentNode.getItem().getData();
                if (ranges.get(i) <= temp.getBalance() && temp.getBalance() < ranges.get(i + 1)) {
                    int index = getTotalCountPerRange.search(ranges.get(i + 1));
                    int value = index + 1;
                    getTotalCountPerRange.delete(ranges.get(i + 1));
                    getTotalCountPerRange.insert(ranges.get(i + 1), value);
                }
                currentNode = accounts.next(currentNode);
            }
        }
        return getTotalCountPerRange;
    }

    /*
     * returns a list of total counts per ranges
     */
    @Override
    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
        ArrayList<Integer> getTotalCountPerRange = new ArrayList<Integer>();
        BinarySearchTree<Integer, Integer> counts = getCountPerRange(ranges);
        BinarySearchTree<Integer, Integer>.Node currentNode = counts.root;
        while (currentNode != null) {
            Integer count = currentNode.getItem().getData();
            Integer maxRange = currentNode.getItem().getKey();
            int index = 0;
            for (int i = 0; i < ranges.size(); i++) {
                if (ranges.get(i).equals(maxRange)) {
                    index = i - 1;
                    break;
                }
            }
            getTotalCountPerRange.add(index, count);
            currentNode = counts.next(currentNode);
        }
        return getTotalCountPerRange;
    }

    /*
     * reports count of each range in bank
     */
    @Override
    public void reportRanges(ArrayList<Integer> ranges) {
        BinarySearchTree<Integer, Integer> countsPerRange = getCountPerRange(ranges);

        BinarySearchTree<Integer, Integer>.Node currentCountPerRange = countsPerRange.root;
        int i = 0;
        while (currentCountPerRange != null) {
            System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = "
                    + currentCountPerRange.getItem().getData());
            i++;
            currentCountPerRange = countsPerRange.next(currentCountPerRange);
        }
        System.out.println();
    }

    /*
     * string for printing bank4
     */
    @Override
    public String toString() {
        return "Bank4{" + "name='" + name + '\'' + ", accounts=" + accounts + '}';
    }

    /*
     * reports cities and their balances and average of balances
     */
    public void reportCity(ArrayList<String> cities, BinarySearchTree<String, Double> balances3,
                           BinarySearchTree<String, Integer> counts3) {
        System.out.println();

        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        BinarySearchTree<String, Double>.Node currentBalance = balances3.root;
        BinarySearchTree<String, Integer>.Node currentCount = counts3.root;
        int i = 0;
        while (currentBalance != null) {
            System.out.println(cities.get(i) + "\t \t " + currentBalance.getItem().getData() + " \t \t "
                    + (currentBalance.getItem().getData() / (double) currentCount.getItem().getData()));
            currentCount = counts3.next(currentCount);
            currentBalance = balances3.next(currentBalance);
            i++;
        }
    }
}
