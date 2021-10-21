package bank;

import java.util.ArrayList;

public class Bank3 extends GeneralBank {
    public String name;
    public MyHashMap<Integer, Account> accounts;
    private final int myHashmapSize = 100;


    /**
     * Constructor
     *
     * @param name -> name of the bank
     */
    public Bank3(String name) {
        this.name = name;
        accounts = new MyHashMap<Integer, Account>();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * This method finds account according to its id
     *
     * @param id unique id of account
     * @return account if existed and null if not existed
     * used in 'addAccount()'
     */
    @Override
    public Account findAccount(int id) {
        // find the account for the given id. Returns null if not found
        return accounts.search(id);
    }

    /**
     * This method adds accounts to 'accounts' arraylist if the new Account id is unique
     *
     * @param account new Account
     * @return true if account added successfully and false if the new id is not unique
     * using 'findAccount()' method to check if there is an account with this ID or not
     */
    @Override
    public boolean addAccount(Account account) {
        return accounts.insert(account.getID(), account);
    }


    /**
     * This method prints all accounts
     */
    @Override
    public void printAccounts() {
        accounts.print();
    }


//    public double calcTotalBalance() {
//        return 0;
//    }


    public Object populateDistinctCityList() {
        return null;
    }


    /**
     * This method calculate total balance for each city
     *
     * @return a Map<city name / total balance>
     */
    @Override
    public MyHashMap<String, Double> getTotalBalancePerCity() {
        MyHashMap<String, Double> getTotalBalancePerCity = new MyHashMap<>();
        MyLinkedList<Integer, Account>[] list = accounts.getMyHashTable();
        //loop on the linkedLists of accounts' hashMap
        for (MyLinkedList<Integer, Account> li : list) {
            Node<Integer, Account> n = li.getHead();
            // Until loop doesn't reach to the tail
            while (n != null) {
                String city = n.item.data.getCity();
                Object balance = getTotalBalancePerCity.get(city);
                if (balance != null) {
                    getTotalBalancePerCity.delete(city);
                    getTotalBalancePerCity.insert(city, n.item.data.getBalance() + (Double) balance);
                } else {
                    getTotalBalancePerCity.insert(city, n.item.data.getBalance());
                }
                n = n.next;
            }
        }
        return getTotalBalancePerCity;
    }


    /**
     * This method calculate total balance for all users
     *
     * @return total bank balance
     */

    public double calcTotalBalance() {
        double total = 0;
        for (MyLinkedList<Integer, Account> link : accounts.getMyHashTable()) {
            Node<Integer, Account> n = link.getHead();
            while (n != null) {
                total = total + n.item.data.getBalance();
                n = n.next;
            }
        }
        return total;
    }

    /**
     * This method calculate the number of existed accounts in each existed city
     *
     * @return a list of number (The indexes of this list correspond to the list of cities)
     */
    @Override
    public MyHashMap<String, Integer> getTotalCountPerCity() {
        MyHashMap<String, Integer> getTotalCountPerCity = new MyHashMap<>();
        //array of linkList of the accounts hashMap
        MyLinkedList<Integer, Account>[] list = accounts.getMyHashTable();
        //loop on the linkedLists of accounts' hashMap
        for (MyLinkedList<Integer, Account> li : list) {
            Node<Integer, Account> n = li.getHead();
            // Until loop doesn't reach to the tail
            while (n != null) {
                String city = n.item.data.getCity();
                Object number = getTotalCountPerCity.get(city);
                if (number != null) {
                    getTotalCountPerCity.delete(city);
                    getTotalCountPerCity.insert(city, 1 + (Integer) number);
                } else {
                    getTotalCountPerCity.insert(city, 1);
                }
                n = n.next;
            }
        }
        return getTotalCountPerCity;
    }

    /**
     * Prints the (city name, total balance of the city, average balance) for all cities as a report
     */
    @Override
    public void reportCity() {
        // get total balance for each city
        MyHashMap<String, Double> balances = getTotalBalancePerCity();
        //get total count per city (number of accounts in a city )
        MyHashMap<String, Integer> counts = getTotalCountPerCity();
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        //array of linkList of the accounts hashMap
        MyLinkedList<String, Double>[] list = balances.getMyHashTable();
        //loop on the linkedLists of accounts' hashMap
        for (MyLinkedList<String, Double> li : list) {
            Node<String, Double> n = li.getHead();
            // Until loop doesn't reach to the tail
            while (n != null) {
                System.out.println(n.item.key + "\t \t " + balances.get(n.item.key) + " \t \t "
                        + balances.get(n.item.key) / (double) counts.get(n.item.key));
                n = n.next;
            }
        }
    }

    /**
     * Prints the (city name, total balance of the city, average balance) for all cities as a report
     */
    public void reportCity(ArrayList<String> cities, MyHashMap<String, Double> balances, MyHashMap<String, Integer> counts) {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        //array of linkList of the accounts hashMap
        MyLinkedList<String, Double>[] list = balances.getMyHashTable();
        //loop on the linkedLists of accounts' hashMap
        for (MyLinkedList<String, Double> li : list) {
            Node<String, Double> n = li.getHead();
            // Until loop doesn't reach to the tail
            while (n != null) {
                System.out.println(n.item.key + "\t \t " + balances.get(n.item.key) + " \t \t "
                        + balances.get(n.item.key) / (double) counts.get(n.item.key));
                n = n.next;
            }
        }
    }

    /**
     * calculate number of existed accounts in a range according to the account's balances
     *
     * @param ranges a list of ranges ([index0,index1] is the first range ( the same way for other range ))
     * @return a list of numbers
     */
    @Override
    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
        ArrayList<Integer> getTotalCountPerRange = new ArrayList<>();
        //array of linkList of the accounts hashMap
        MyLinkedList<Integer, Account>[] list = accounts.getMyHashTable();
        for (int i = 0; i < ranges.size() - 1; i++) {
            getTotalCountPerRange.add(i, 0);

            //loop on the linkedLists of accounts' hashMap
            for (MyLinkedList<Integer, Account> li : list) {
                Node<Integer, Account> n = li.getHead();
                // Until loop doesn't reach to the tail
                while (n != null) {
                    Account temp = n.item.data;
                    if (ranges.get(i) <= temp.getBalance() && temp.getBalance() < ranges.get(i + 1)) {
                        getTotalCountPerRange.set(i, getTotalCountPerRange.get(i) + 1);
                    }
                    n = n.next;
                }
            }
        }

        return getTotalCountPerRange;
    }

    /**
     * Print number of existed accounts between two range
     *
     * @param ranges a list of ranges
     *               ' getTotalCountPerRange(ranges)' is called in this method
     */
    @Override
    public void reportRanges(ArrayList<Integer> ranges) {
        ArrayList<Integer> countsPerRange = getTotalCountPerRange(ranges);
        System.out.println();
        for (int i = 0; i < ranges.size() - 1; i++) {
            System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = "
                    + countsPerRange.get(i));
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Bank3{" + "name='" + name + '\'' + ", accounts=" + accounts + ", myHashmapSize=" + myHashmapSize + '}';
    }
}
