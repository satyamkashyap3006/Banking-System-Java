public class CurrentAccount extends Account {
    private final double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > (this.balance + this.overdraftLimit)) {
            throw new InsufficientFundsException("Withdrawal rejected: Exceeds overdraft limit of Rs " + overdraftLimit);
        }
        this.balance -= amount;
        this.transactionHistory.add(new Transaction("Withdrawal (Overdraft)", amount, this.balance));
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }
}
