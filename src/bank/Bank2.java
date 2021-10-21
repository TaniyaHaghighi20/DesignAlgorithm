package bank;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author hooman
 */

import java.util.ArrayList;
import java.util.HashMap;

class Bank2 extends GeneralBank {
    public String name;
    public HashMap<Integer, Account> accounts;

    /**
     * Constructor
     *
     * @param name -> name of the bank
     */
    public Bank2(String name) {
        this.name = name;
        accounts = new HashMap<Integer, Account>();
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
        return accounts.get(id);
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
        //if account id doesn't exist, it will add
        if (findAccount(account.getID()) == null) {
            accounts.put(account.getID(), account);
            return true;
        }
        return false;

    }

    /**
     * This method prints all accounts
     */
    @Override
    public void printAccounts() {
        for (Account acc : accounts.values()) {
            acc.print();
        }
    }


    /**
     * This method calculate total balance for all users
     *
     * @return total bank balance
     */

    public double calcTotalBalance() {
        double total = 0;
        for (Integer key : accounts.keySet()) {
            total += accounts.get(key).getBalance();
        }
        return total;
    }

    public Object populateDistinctCityList() {
        return null;
    }

    /**
     * This method calculate total balance for each city
     *
     * @return a Map<city name / total balance>
     */
    @Override
    public HashMap<String, Double> getTotalBalancePerCity() {
        HashMap<String, Double> getTotalBalancePerCity = new HashMap<>();
        //for each account
        for (Integer key : accounts.keySet()) {
            //get city
            String city = accounts.get(key).getCity();
            Object balance = getTotalBalancePerCity.get(city);
            if (balance != null) {
                getTotalBalancePerCity.replace(city, accounts.get(key).getBalance() + (Double) balance);
            } else {
                getTotalBalancePerCity.put(city, accounts.get(key).getBalance());
            }
        }
        return getTotalBalancePerCity;
    }


    /**
     * This method calculate the number of existed accounts in each existed city
     *
     * @return a list of number (The indexes of this list correspond to the list of cities)
     */
    @Override
    public HashMap<String, Integer> getTotalCountPerCity() {
        HashMap<String, Integer> getTotalCountPerCity = new HashMap<>();
        // for each account
        for (Integer key : accounts.keySet()) {
            //get account's city
            String city = accounts.get(key).getCity();
            Object number = getTotalCountPerCity.get(city);
            if (number != null) {
                getTotalCountPerCity.put(city, 1 + (Integer) number);
            } else {
                getTotalCountPerCity.put(city, 1);
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
        HashMap<String, Double> balances = getTotalBalancePerCity();
        //get total count per city (number of accounts in a city )
        HashMap<String, Integer> counts = getTotalCountPerCity();
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (String city : balances.keySet()) {
            System.out.println(city + "\t \t " + balances.get(city) + " \t \t " + balances.get(city) / (double) counts.get(city));
        }
    }

    /**
     * Prints the (city name, total balance of the city, average balance) for all cities as a report
     */
    public void reportCity(HashMap<String, Double> balances, HashMap<String, Integer> counts) {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (String city : balances.keySet()) {
            System.out.println(city + "\t \t " + balances.get(city) + " \t \t " + balances.get(city) / (double) counts.get(city));
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
        // a loop on range (the first number of the list to the one before the last number)
     for (int i = 0; i < ranges.size() - 1; i++) {
            getTotalCountPerRange.add(i,0);
            for (Integer key : accounts.keySet()) {
                Account temp = accounts.get(key);
                if (ranges.get(i) <= temp.getBalance() && temp.getBalance() < ranges.get(i + 1)) {
//                    if (getTotalCountPerRange.isEmpty() || getTotalCountPerRange.get(i) == null) {
                        getTotalCountPerRange.set(i, getTotalCountPerRange.get(i) + 1);
//                        break;
//                    }
//                    else {

//                        break;
//                    }
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
            System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = " + countsPerRange.get(i));
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Bank2{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
 
