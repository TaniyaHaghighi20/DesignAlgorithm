package bank;

import java.util.ArrayList;

public abstract class GeneralBank {
    abstract public Account findAccount(int id);

    abstract public boolean addAccount(Account account);

    abstract public void printAccounts();

//    abstract public double calcTotalBalance();

//    abstract public Object populateDistinctCityList();

    abstract public Object getTotalBalancePerCity();

    abstract public Object getTotalCountPerCity();

    abstract public void reportCity();

    abstract public Object getTotalCountPerRange(ArrayList<Integer> ranges);

    abstract public void reportRanges(ArrayList<Integer> ranges);

    abstract public String getName();

}
