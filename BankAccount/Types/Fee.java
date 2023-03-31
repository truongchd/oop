public class Fee extends Account {
    public Fee (double initialBalance) {
        super(initialBalance);
    }

    public void withdraw() {

    }

    public void summary() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
        transactions.clear();
        balance -= 5.0;
    }
}