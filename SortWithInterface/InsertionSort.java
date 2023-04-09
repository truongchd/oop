public class InsertionSort {
    public void sort(BankAccount[] account, MyComparator compare) {
        boolean done = false;
        BankAccount tmp;
        int counta = 0;
        while (!done) {
            done = true;
            for (int i = 0; i < account.length - 1; i++) {
                boolean current = compare.less(account[i], account[i + 1]);
                if (current) continue;
                tmp = account[i];
                account[i] = account[i + 1];
                account[i + 1] = tmp;
                done = false;
                for (int j = 0; j < account.length; j++) {
                    System.out.println(account[j].getBalance());
                }
                System.out.println("-----------------");
                counta++;
            }
        }
    }
}
