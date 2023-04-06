import java.util.*;

abstract public class BankAccount {
    protected double balance;
    protected Vector<Double> transactions;

    public BankAccount(double initial) {
        try {
            if (initial < 0) {
                throw new Exception("Initial balance cannot be negative");
            }
            this.balance = initial;
            this.transactions = new Vector<Double>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    abstract public String deposit(double amount);

    abstract public String withdraw(double amount);

    abstract public void endMonth();
}
