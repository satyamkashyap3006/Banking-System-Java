
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Account> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed default sample accounts
        accounts.put("SB101", new SavingsAccount("SB101", "Satyam Kashyap", 5000.0));
        accounts.put("CA201", new CurrentAccount("CA201", "Campus Retail Store", 10000.0, 5000.0));

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("       VIT CORE BANKING TERMINAL          ");
            System.out.println("==========================================");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Statement & Export Audit Log");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleCreateAccount();
                    break;
                case "2":
                    handleDeposit();
                    break;
                case "3":
                    handleWithdraw();
                    break;
                case "4":
                    handleTransfer();
                    break;
                case "5":
                    handleStatementAndExport();
                    break;
                case "6":
                    System.out.println("Session closed. Exiting terminal application.");
                    return;
                default:
                    System.out.println("Invalid input. Please choose an option from 1 to 6.");
            }
        }
    }

    // Function 1: Create Account
    private static void handleCreateAccount() {
        System.out.print("Enter unique Account Number: ");
        String accNum = scanner.nextLine().trim();
        if (accounts.containsKey(accNum)) {
            System.out.println("Error: An account with this number already exists.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Choose Account Type (1 for Savings, 2 for Current): ");
        String type = scanner.nextLine().trim();

        System.out.print("Enter Initial Deposit Amount (Rs): ");
        double initialDeposit = Double.parseDouble(scanner.nextLine().trim());

        if (type.equals("1")) {
            if (initialDeposit < 1000.0) {
                System.out.println("Error: Minimum opening balance for Savings Account is Rs 1000.");
                return;
            }
            accounts.put(accNum, new SavingsAccount(accNum, name, initialDeposit));
            System.out.println("Savings Account created successfully.");
        } else if (type.equals("2")) {
            System.out.print("Enter Overdraft Limit (Rs): ");
            double odLimit = Double.parseDouble(scanner.nextLine().trim());
            accounts.put(accNum, new CurrentAccount(accNum, name, initialDeposit, odLimit));
            System.out.println("Current Account created successfully.");
        } else {
            System.out.println("Invalid account type selected.");
        }
    }

    // Function 2: Deposit Funds
    private static void handleDeposit() {
        Account acc = findAccount();
        if (acc == null) return;

        System.out.print("Enter Deposit Amount (Rs): ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        try {
            acc.deposit(amount);
            System.out.printf("Success! New Balance: Rs %.2f%n", acc.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }

    // Function 3: Withdraw Funds
    private static void handleWithdraw() {
        Account acc = findAccount();
        if (acc == null) return;

        System.out.print("Enter Withdrawal Amount (Rs): ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        try {
            acc.withdraw(amount);
            System.out.printf("Success! Remaining Balance: Rs %.2f%n", acc.getBalance());
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }

    // Function 4: Transfer Funds
    private static void handleTransfer() {
        System.out.print("Enter Source Account Number: ");
        Account source = accounts.get(scanner.nextLine().trim());
        if (source == null) {
            System.out.println("Error: Source account not found.");
            return;
        }

        System.out.print("Enter Beneficiary Account Number: ");
        Account destination = accounts.get(scanner.nextLine().trim());
        if (destination == null) {
            System.out.println("Error: Beneficiary account not found.");
            return;
        }

        if (source.getAccountNumber().equals(destination.getAccountNumber())) {
            System.out.println("Error: Source and destination accounts cannot be identical.");
            return;
        }

        System.out.print("Enter Transfer Amount (Rs): ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        try {
            source.withdraw(amount);
            destination.deposit(amount);
            System.out.printf("Transfer Successful! Transferred Rs %.2f from %s to %s.%n",
                    amount, source.getAccountNumber(), destination.getAccountNumber());
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Transfer Aborted: " + e.getMessage());
        }
    }

    // Function 5: View Statement & Export Audit Log
    private static void handleStatementAndExport() {
        Account acc = findAccount();
        if (acc == null) return;

        System.out.println("\n----------------- ACCOUNT STATEMENT -----------------");
        System.out.printf("Account No: %s | Holder: %s | Type: %s%n",
                acc.getAccountNumber(), acc.getAccountHolderName(), acc.getAccountType());
        System.out.printf("Current Available Balance: Rs %.2f%n", acc.getBalance());
        System.out.println("Transaction History:");
        for (Transaction t : acc.getTransactionHistory()) {
            System.out.println("  " + t);
        }
        System.out.println("------------------------------------------------------");

        // Character-stream File I/O
        String fileName = acc.getAccountNumber() + "_statement.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ACCOUNT STATEMENT - " + acc.getAccountNumber() + "\n");
            writer.write("Holder: " + acc.getAccountHolderName() + " | Type: " + acc.getAccountType() + "\n");
            writer.write("Balance: Rs " + acc.getBalance() + "\n\n");
            writer.write("--- TRANSACTIONS ---\n");
            for (Transaction t : acc.getTransactionHistory()) {
                writer.write(t.toString());
                writer.newLine();
            }
            System.out.println("Audit statement exported to local file: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to write audit log to file: " + e.getMessage());
        }
    }

    private static Account findAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();
        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Error: Account not found.");
        }
        return acc;
    }
}