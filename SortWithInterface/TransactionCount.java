public class TransactionCount implements MyComparator{
    public boolean less(BankAccount a1, BankAccount a2) {
        return a1.getTransactions().size() < a2.getTransactions().size();
    }
}
