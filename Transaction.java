import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String description;
    private final double amount;
    private final double remainingBalance;
    private final String timestamp;

    public Transaction(String description, double amount, double remainingBalance) {
        this.description = description;
        this.amount = amount;
        this.remainingBalance = remainingBalance;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %-22s | Amount: Rs %-10.2f | Balance: Rs %-10.2f",
                timestamp, description, amount, remainingBalance);
    }
}
