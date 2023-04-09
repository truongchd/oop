import java.lang.*;
import java.util.*;

public class Gambler extends BankAccount {
    private Vector<Integer> gamble;
    public Gambler (double initial) {
        super(initial);
        this.gamble = new Vector<>();
    }

    public String deposit(double amount) {
        try {
            if (amount < 0) {
                throw new Exception("Deposit amount cannot be negative");
            }
            this.balance += amount;
            this.transactions.add(amount);
            this.gamble.add(0);
            return "Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String withdraw(double amount){
        try {
            if (amount < 0) {
                throw new Exception("Withdraw amount cannot be negative");
            } else if (amount * 2 >= this.balance) {
                throw new Exception("Withdraw amount must be less than or equal to your current balance");
            }
            double rand = Math.random();
            if (rand > 0.49) {
                this.balance -= amount * 2;
                this.gamble.add(-1);
            } else {
                this.gamble.add(1);
            }
            this.transactions.add(-amount);
            return "Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void endMonth() {
        for (int i = 0; i < this.transactions.size(); i++) {
            System.out.print("Transaction #" + (i+1) + " " + this.transactions.get(i));
            if (this.gamble.get(i) != 0) {
                System.out.print(" " + this.gamble.get(i));
            }
            System.out.print("\n");
        }
        System.out.println("Current balance " + this.balance);
        this.transactions.clear();
        this.gamble.clear();
    }
}
