# BankNexus: Core Banking & Transaction Management System

A modular, terminal-based Core Banking System developed in Core Java featuring dynamic account management, real-time balance validation, custom exception handling, and automated file I/O audit exports.

---

# 1. Project Overview

Traditional manual banking registers and unlinked tracking systems are vulnerable to calculation errors, missing transaction trails, and unauthorized account overdrafts. BankNexus provides an automated, object-oriented desktop banking engine implemented strictly in Core Java. The application models both Savings and Current banking accounts, strictly enforces minimum balance thresholds and overdraft constraints, tracks atomic deposits and withdrawals with localized timestamps and generates persistent account statement audit files directly using native Java File I/O.

---

# 2. Key Features

- Dual Account Architecture: Implements Savings Accounts with strict minimum balance boundaries and Current Accounts with customizable commercial overdraft facilities.
- Strict Transaction Invariants: Validates deposits, withdrawals, and account-to-account transfers atomically, rejecting negative amounts, zero values, and unauthorized overdraft requests.
- Custom Checked Exceptions: Enforces domain business logic cleanly through InsufficientFundsException to intercept invalid withdrawals without application crashes.
- Inter-Account Fund Transfers: Features an integrated fund transfer engine that executes atomic withdrawals from the source account and credits to the destination account.
- Complete Audit History: Maintains an internal ledger of all transactions with real-time timestamps using Java's LocalDateTime APIs.
- Automated File I/O Persistence: Exports complete customer statements and chronological transaction ledgers directly to disk as text-based audit logs.

---

# 3. Technologies and Tools Used

- Programming Language: Java SE (JDK 17 or higher)
- Design Paradigm: Object-Oriented Programming (Abstraction, Inheritance, Polymorphism, Encapsulation, Custom Checked Exceptions).
- Collections Framework: Map and HashMap for active accounts, List and ArrayList for transaction history.
- Date and Time API: java.time.LocalDateTime and java.time.format.DateTimeFormatter.
- File Persistence: Native Java Character Streams (BufferedWriter and FileWriter).
- Development Environment: Visual Studio Code, Command Prompt, or PowerShell
- Version Control: Git and GitHub

---

# 4. System Architecture

The project follows a clean object-oriented architecture dividing presentation, business domain models, and file exports:

- Presentation Layer (Main.java): Runs the interactive CLI menu loop, collects user inputs using Scanner, validates inputs defensively, and formats console feedback.
- Abstract Domain Entity (Account.java): Defines core account contracts, attributes (accountNumber, accountHolderName, balance), deposits, and polymorphic withdrawal methods.
- Specialized Models (SavingsAccount.java, CurrentAccount.java): Enforce concrete account behaviors such as the Rs. 1000 minimum balance check or overdraft ceiling limits.
- Transaction Ledger (Transaction.java): Models individual banking actions, automatically generating formatted timestamps, transaction types, and resultant balances.
- Persistence Mechanism: Generates and writes structured account statements directly to local storage as formatted text files.

---

# 5. Repository File Structure

- Account.java: Abstract parent class defining fundamental bank account attributes and methods.
- SavingsAccount.java: Concrete subclass enforcing the Rs. 1000 minimum balance rule.
- CurrentAccount.java: Concrete subclass handling commercial credit limits and overdraft withdrawals.
- Transaction.java: Domain class representing a logged financial transaction with formatted timestamps.
- InsufficientFundsException.java: Custom checked exception representing unauthorized withdrawal attempts.
- Main.java: Main driver class containing the CLI menu, seed accounts, transaction handlers, and file exporter.
- README.md: Detailed repository documentation and usage guide.

---

# 6. How to Install and Run

### Prerequisites

Ensure Java Development Kit (JDK 17 or higher) is installed on your computer. Verify by running:

javac -version
java -version

### Setup and Execution

1. Clone the repository:

git clone https://github.com/satyamkashyap3006/Banking-System-Java.git

2. Compile all source files into the bin folder:

For Windows Command Prompt, Linux, or macOS:
javac -d bin *.java

For Windows PowerShell:
javac -d bin Main.java Account.java SavingsAccount.java CurrentAccount.java Transaction.java InsufficientFundsException.java

3. Run the application:

java -cp bin Main

---

# 7. Instructions for Testing

The application comes pre-loaded with sample accounts (Savings Account "SB101" with Rs. 5000.00 and Current Account "CA201" with Rs. 10000.00 and Rs. 5000.00 overdraft). Use the following test scenarios to verify system behavior:

### Test Case 1: Account Creation and Deposit
Choose Option 1 (Create New Account) and register an account by entering unique Account Number as "SB102", Holder Name as "Satyam Kashyap", choose Type 1 (Savings), and enter Initial Deposit as "2000.00". Next, select Option 2 (Deposit Funds), enter Account Number "SB102", and deposit "1500.00". The system outputs "Success! New Balance: Rs 3500.00", confirming valid balance mutation.

### Test Case 2: Minimum Balance Violation (InsufficientFundsException)
Choose Option 3 (Withdraw Funds), provide Account Number "SB101" (Current Balance: Rs. 5000.00), and enter a Withdrawal Amount of "4500.00". Because Savings accounts require a mandatory minimum balance of Rs. 1000.00, the remaining balance would fall to Rs. 500.00. The system cleanly catches the custom InsufficientFundsException and outputs "Transaction Failed: Withdrawal rejected: Must maintain minimum balance of Rs 1000.0", leaving the original balance unchanged.

### Test Case 3: Inter-Account Transfer and File Export
Choose Option 4 (Transfer Funds) to transfer "1000.00" from source account "SB101" to beneficiary account "CA201". The system displays "Transfer Successful! Transferred Rs 1000.00 from SB101 to CA201." Next, choose Option 5 (View Statement & Export Audit Log) for "SB101". The terminal prints the complete ledger containing initial deposits, withdrawals, and transfers, and successfully writes an audit log file named "SB101_statement.txt" to the local directory.

---

# 8. Screenshots / Results

<img width="478" height="286" alt="Screenshot 2026-09-15 at 9 59 12 PM" src="https://github.com/user-attachments/assets/a8dd8d59-9b06-43c6-9d06-9396a319cf21" />
<br>
<img width="379" height="237" alt="Screenshot 2026-09-15 at 9 59 21 PM" src="https://github.com/user-attachments/assets/bdfb6766-87f5-4388-8325-afcefeff503d" />
<br>
<img width="370" height="236" alt="Screenshot 2026-09-15 at 9 59 28 PM" src="https://github.com/user-attachments/assets/1973d2f8-5c30-4766-b03b-98d5a6e707d4" />
<br>
<img width="559" height="255" alt="Screenshot 2026-09-15 at 9 59 38 PM" src="https://github.com/user-attachments/assets/4aed60e5-477b-4165-acba-1fda5b6711bf" />
<br>
<img width="812" height="370" alt="Screenshot 2026-09-15 at 9 59 53 PM" src="https://github.com/user-attachments/assets/486cb122-5940-4f54-95eb-29e9db1bf306" />
<br>
<img width="393" height="200" alt="Screenshot 2026-09-15 at 10 00 09 PM" src="https://github.com/user-attachments/assets/4f61601a-3d73-4123-aea5-dff938094e54" />

---

# 9. Future Enhancements

- Graphical User Interface: Build an interactive visual interface using JavaFX or Swing.
- Persistent Relational Database: Integrate SQLite or MySQL via JDBC to replace in-memory maps and text files.
- Role-Based Access Control: Implement customer and bank manager authentication with encrypted passwords.
- Automated Interest Scheduler: Implement a background scheduler using ScheduledExecutorService to calculate and disburse monthly interest automatically.
