import java.util.*;

public class NickelDime extends BankAccount{
    public NickelDime(double initial) {
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
            } else if (amount + 0.5 >= this.balance) {
                throw new Exception("Withdraw amount must be less than or equal to your current balance");
            }
            this.balance -= (amount + 0.5);
            this.transactions.add(-amount);
            return "Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void endMonth() {
        for (int i = 0; i < this.transactions.size(); i++) {
            System.out.print("Transaction #" + (i+1) + " " + this.transactions.get(i));
            if (this.transactions.get(i) < 0) {
                System.out.print(" plus 0.5 extra fee");
            }
            System.out.print("\n");
        }
        System.out.println("Current balance " + this.balance);
        this.transactions.clear();
    }
}
