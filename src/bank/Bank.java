package bank;

/**
 * @author hooman
 */


import java.util.ArrayList;

public class Bank extends GeneralBank {
    private final String name;
    private ArrayList<Account> accounts;

    /**
     * Constructor
     *
     * @param name -> name of the bank
     */
    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<Account>();
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
    public Account findAccount(int id) { // find the account for the given id. Returns null if not found
        for (Account acc : accounts) {
            if (acc.getID() == id) {
                return acc;
            }
        }
        return null;
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
            accounts.add(account);
            return true;
        }
        return false;
    }

    /**
     * This method prints all accounts
     */
    @Override
    public void printAccounts() {
        for (Account acc : accounts) {
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
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    /**
     * This method adds accounts' cities to a list (don't accept duplicate)
     *
     * @return a list of cities
     */
    public ArrayList<String> populateDistinctCityList() {
        ArrayList<String> cities = new ArrayList<>();
        boolean isExist = false;
        // for each account
        for (Account acc : accounts) {
            String city = acc.getCity();
            //for each city
            for (String key : cities) {
                //if account's city is existed in list
                if (key.equalsIgnoreCase(city)) {
                    isExist = true;
                    break;
                }
            }
            //adding city to the list if it was not exist in it
            if (!isExist) {
                cities.add(city);

            }
            isExist = false;
        }
        return cities;
    }


    /**
     * This method calculate total balance for each city
     *
     * @return a list of total balance (The indexes of this list correspond to the list of cities)
     * ' populateDistinctCityList()' is called in this method
     */
    @Override
    public ArrayList<Double> getTotalBalancePerCity() {
        //get existed cities
        ArrayList<String> cities = populateDistinctCityList();
        ArrayList<Double> totalBalancePerCity = new ArrayList<>();
        //for each city
        for (String city : cities) {
            double balance = 0;
            //for each account
            for (Account acc : accounts) {
                // adding account's balance to the parameter if the account's city is the same as 'city'
                if (acc.getCity().equals(city)) {
                    balance += balance + acc.getBalance();
                }
            }
            totalBalancePerCity.add(balance);
        }
        return totalBalancePerCity;
    }

    /**
     * This method calculate total balance for each city
     *
     * @param cities a list of distinct city
     * @return a list of total balance (The indexes of this list correspond to the list of cities)
     */
    public ArrayList<Double> getTotalBalancePerCity(ArrayList<String> cities) {
        ArrayList<Double> totalBalancePerCity = new ArrayList<>();
        double balance;
        //for each city
        for (String city : cities) {
            balance = 0;
            //for each account
            for (Account acc : accounts) {
                // adding account's balance to the parameter if the account's city is the same as 'city'
                if (acc.getCity().equals(city)) {
                    balance = balance + acc.getBalance();
                }
            }
            // add total balance of a city to the list
            totalBalancePerCity.add(balance);
        }
        return totalBalancePerCity;
    }


    /**
     * This method calculate the number of existed accounts in each existed city
     *
     * @return a list of number (The indexes of this list correspond to the list of cities)
     * ' populateDistinctCityList()' is called in this method
     */
    @Override
    public ArrayList<Integer> getTotalCountPerCity() {
        //get existed cities
        ArrayList<String> cities = populateDistinctCityList();
        ArrayList<Integer> totalCountPerCity = new ArrayList<>();
        int count;
        //for each city
        for (String city : cities) {
            count = 0;
            //for each account
            for (Account acc : accounts) {
                // count+1 if the account's city is the same as 'city'
                if (acc.getCity().equals(city)) {
                    count++;
                }
            }
            // add total count of a city to the list
            totalCountPerCity.add(count);
        }
        return totalCountPerCity;
    }


    /**
     * This method calculate the number of existed accounts in each existed city
     *
     * @return a list of number (The indexes of this list correspond to the list of cities)
     * ' populateDistinctCityList()' is called in this method
     */
    public ArrayList<Integer> getTotalCountPerCity(ArrayList<String> cities) {
        ArrayList<Integer> totalCountPerCity = new ArrayList<>();
        int count;
        //for each city
        for (String city : cities) {
            count = 0;
            //for each account
            for (Account acc : accounts) {
                // count+1 if the account's city is the same as 'city'
                if (acc.getCity().equals(city)) {
                    count++;
                }
            }
            // add total count of a city to the list
            totalCountPerCity.add(count);
        }
        return totalCountPerCity;
    }


    /**
     * Prints the (city name, total balance of the city, average balance) for all cities as a report
     */
    @Override
    public void reportCity() {
        //get existed cities
        ArrayList<String> cities = populateDistinctCityList();
        //get total count per city (number of accounts in a city )
        ArrayList<Integer> counts = getTotalCountPerCity();
        // get total balance for each city
        ArrayList<Double> totals = getTotalBalancePerCity();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (int i = 0; i < counts.size(); i++)
            System.out.println(cities.get(i) + " \t \t " + totals.get(i) + " \t \t " + totals.get(i) / (double) counts.get(i));
    }

    /**
     * Prints the (city name, total balance of the city, average balance) for all cities as a report
     *
     * @param cities list of existed cities
     * @param counts list of total count per city (number of accounts in a city )
     * @param totals list of total balance for each city
     */
    public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts, ArrayList<Double> totals) {
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (int i = 0; i < counts.size(); i++)
            System.out.println(cities.get(i) + " \t \t " + totals.get(i) + " \t \t " + totals.get(i) / (double) counts.get(i));
    }

    /**
     * calculate number of existed accounts in a range according to the account's balances
     *
     * @param ranges a list of ranges ([index0,index1] is the first range ( the same way for other range ))
     * @return a list of numbers
     */
    @Override
    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
        ArrayList<Integer> totalCountPerRange = new ArrayList<>();
        int counter;
        // a loop on range (the first number of the list to the one before the last number)
        for (int i = 0; i < ranges.size() - 1; i++) {
            counter = 0;
            for (Account acc : accounts) {
                // if an account's balance is between two consecutive value on the list
                if (ranges.get(i) <= acc.getBalance() && acc.getBalance() < ranges.get(i + 1)) {
                    counter++;
                }
            }
            totalCountPerRange.add(counter);
        }
        return totalCountPerRange;
    }

    /**
     * Print number of existed accounts between two range
     *
     * @param ranges a list of ranges
     *               ' getTotalCountPerRange(ranges)' is called in this method
     */
    @Override
    public void reportRanges(ArrayList<Integer> ranges) {
        // get number of accounts in a range
        ArrayList<Integer> counts = getTotalCountPerRange(ranges);
        System.out.println();
        for (int i = 0; i < ranges.size() - 1; i++) {
            System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = " + counts.get(i));
        }
        System.out.println();
    }

    /**
     * Print number of existed accounts between two range
     *
     * @param ranges a list of ranges
     * @param counts a list of  numbers (existed accounts in a range)
     */
    public void reportRanges(ArrayList<Integer> ranges, ArrayList<Integer> counts) {
        System.out.println();
        for (int i = 0; i < ranges.size() - 1; i++) {
            System.out.println("Number of accounts between " + ranges.get(i) + " and " + ranges.get(i + 1) + " = " + counts.get(i));
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Bank1{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}

