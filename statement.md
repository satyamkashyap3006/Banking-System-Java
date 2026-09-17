# Project Statement: BankNexus (Core Banking System)

---

## 1. Problem Statement

Manual banking ledgers, physical recording books, and disconnected spreadsheet systems are prone to human operational errors, unauthorized account overdrafts, delayed audit tracking, and accounting discrepancies. Traditional manual record-keeping lacks built-in transactional integrity, making it difficult to enforce mandatory financial rules—such as minimum balance limits for individual savings accounts or defined credit lines for commercial current accounts. Furthermore, when cash deposits, withdrawals, or inter-account transfers are recorded manually, there is no automated, tamper-evident audit trail with exact transaction timestamps, increasing the risk of financial discrepancies and administrative delays during reconciliation.

---

## 2. Scope of the Project

The scope of BankNexus encompasses developing an automated, lightweight, terminal-based Core Banking System written in Core Java (SE) that eliminates manual recording vulnerabilities. The system models realistic retail banking operations under strict Object-Oriented Programming (OOP) paradigms:

- Dynamic Account Lifecycle: Facilitates the onboarding and runtime registration of Savings and Current accounts with distinct operational rules.
- Transactional Processing Engine: Atomically processes deposits, withdrawals, and account-to-account fund transfers while validating input amounts defensively.
- Constraint Enforcement: Enforces account-specific financial boundaries, including mandatory minimum opening/maintaining balances and predefined overdraft limits.
- Auditing & Local Persistence: Automatically maintains chronological transaction histories with timestamps and exports dedicated account statement audit logs to persistent local storage files using native Java character streams.
- Portability & Zero External Dependencies: Built entirely on standard Java Development Kit (JDK 17+) constructs without requiring external database engines, ensuring instant execution on any system running a Java Virtual Machine (JVM).

---

## 3. Target Users

- Bank Clerks & Cashiers: Front-desk banking personnel who perform daily deposit, withdrawal, and fund transfer operations for retail customers through a streamlined command-line interface.
- Branch Administrators & Compliance Auditors: Bank supervisors who need instant verification of active account holdings and direct generation of customer statements for audit and reconciliation purposes.
- Academic Evaluators & Students: Instructors and engineering peers evaluating robust object-oriented system design, inheritance hierarchies, custom exception handling, and file persistence in Java.

---

## 4. High-Level Features

- Polymorphic Dual Account System: Implements an abstract base account structure extended by concrete `SavingsAccount` (enforcing a minimum balance of Rs. 1,000) and `CurrentAccount` (featuring commercial overdraft coverage).
- Robust Input Validation & Exception Handling: Catches negative values, zero amounts, and insufficient balances using custom domain exceptions (`InsufficientFundsException`), protecting internal state integrity.
- Atomic Inter-Account Fund Transfer: Transfers funds securely between source and beneficiary accounts in a unified operation with validation rollback.
- Automated Audit Trail: Automatically logs every financial transaction with an exact description, transferred amount, resultant balance, and a localized timestamp.
- Statement Export via File I/O: Generates and exports comprehensive, human-readable account statement text files (`<AccountNumber>_statement.txt`) to local disk using buffered file writers.
