public class NickelDime extends Account {
    public NickelDime (double initialBalance) {
        super(initialBalance);
    }

    public void summary() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.print(transactions.get(i));
            if (transactions.get(i) < 0) System.out.print(" and 0.5 extra");
            System.out.print("\n");
        }
        transactions.clear();
    }
}
