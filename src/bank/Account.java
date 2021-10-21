package bank;

/**
 *
 * @author hooman
 */
class Account
{
    private int id;
    private String name;
    private double balance;
    private String city;
    
    public Account(int id, String name, double balance, String city)
    {
        this.id = id;
        this.name=name;
        this.city=city;
        if (balance>=0)
        {
            this.balance = balance;
        }
      	else
        {
            throw new ArithmeticException();
        }   
    }
    
    public int getID ()
    {
        return id;
    }
	
    public String getCity ()
    {
        return city;
    }
	
    public void setCity(String city)
    {
            this.city=city;
    }
    
    public double getBalance ()
    {
        return balance;
    }

    public String getName() {
        return name;
    }

    boolean withdraw(double amount)
    {
        if (balance>=amount)
        {
            balance-=amount;
            return true;
        }
        else
        {
            return false;
        } 
    }  
    boolean deposit(double amount)
    {
        if (amount>=0)
        {
            balance+=amount;
            return true;
        }
        else
        {
            return false;
        }  
    }
    public void print ()
    {
        //System.out.println ("ID: "+id +" Name: " +name+ " Balance: " + balance+ " City: "+ city); // old way
	System.out.println(this);
    }
    public String toString()
    {
            return "ID: "+id +" Name: " +name+ " Balance: " + balance+ " City: "+ city;
    }
}