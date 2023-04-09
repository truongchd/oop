public class Main {
    public static void main (String[] args) {
        BankAccount[] accounts = new BankAccount[5];
        accounts[0] = new Fee(15);
        accounts[1] = new Fee(14);
        accounts[2] = new Fee(150);
        accounts[3] = new Fee(1);
        accounts[4] = new Fee(135);
        InsertionSort sorted = new InsertionSort();
        sorted.sort(accounts, new BalanceAscending());
        for (int i = 0; i < accounts.length; i++) {
            System.out.println(accounts[i].getBalance());
            for (Double transaction: accounts[i].getTransactions()) {
                System.out.println(transaction);
            }
        }
    }
}
