public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 1000.0;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if ((this.balance - amount) < MINIMUM_BALANCE) {
            throw new InsufficientFundsException("Withdrawal rejected: Must maintain minimum balance of Rs " + MINIMUM_BALANCE);
        }
        this.balance -= amount;
        this.transactionHistory.add(new Transaction("Withdrawal", amount, this.balance));
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
