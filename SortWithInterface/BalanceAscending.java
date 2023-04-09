public class BalanceAscending implements MyComparator{
    public boolean less(BankAccount a1, BankAccount a2) {
        return a1.getBalance() < a2.getBalance();
    }

    public boolean equals(BankAccount a1, BankAccount a2) {
        return a1.getBalance() == a2.getBalance();
    }
}
