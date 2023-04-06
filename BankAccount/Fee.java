import java.util.*;

public class Fee extends BankAccount {
    public Fee(double initial) {
        super(initial);
    }
    
    public String deposit(double amount) {
        try {
            if (amount < 0) {
                throw new Exception("Deposit amount cannot be negative");
            }
            this.balance += amount;
            this.transactions.add(amount);
            return "Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String withdraw(double amount){
        try {
            if (amount < 0) {
                throw new Exception("Withdraw amount cannot be negative");
            } else if (amount >= this.balance) {
                throw new Exception("Withdraw amount must be less than or equal to your current balance");
            }
            this.balance -= amount;
            this.transactions.add(-amount);
            return "Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void endMonth() {
        for (int i = 0; i < this.transactions.size(); i++) {
            System.out.println("Transaction #" + (i+1) + " " + this.transactions.get(i));
        }
        this.balance -= 5.0;
        System.out.println("Current balance " + this.balance);
        this.transactions.clear();
    }
}
