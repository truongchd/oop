import java.util.*;

public class main {
    public static void main(String[] args) {
        BankAccount fee = new Fee(5.0);
        BankAccount nick = new NickelDime(5.0);
        BankAccount gam = new Gambler(5.0);
        //check fee
        System.out.println(fee.deposit(15));
        System.out.println(fee.withdraw(15));
        fee.endMonth();
        //check nickeldime
        System.out.println(nick.deposit(15));
        System.out.println(nick.withdraw(15));
        nick.endMonth();
        //check gamble
        System.out.println(gam.deposit(15));
        System.out.println(gam.withdraw(2));
        gam.endMonth();
    }
}
