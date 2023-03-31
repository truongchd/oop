public static class Gambler extends Account {
    public Fee (double initialBalance) {
        super(initialBalance);
    }

    public boolean withdraw(double amount) {
        
        try {
            if (amount < 0.0) {
                throw new Exception("Deposit must be a positive real number");
            }
            if (balance <= amount + 0.5) {
                throw new Exception("Withdraw amount cannot be more than your balance");
            }
            balance -= (amount + 0.5);
            transactions.add(-amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void summary() {
        return 5.0;
    }
}