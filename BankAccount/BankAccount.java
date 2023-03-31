import java.util.List;

abstract public class BankAccount {       
    protected double balance;
    protected List<Double> transactions;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public boolean deposit(double amount) {
        balance += amount;
        transactions.add(amount);
        return true;
    }

    public boolean withdraw(double amount) {
        balance -= amount;
        transactions.add(-amount);
    };

    abstract public void endMonth();
}