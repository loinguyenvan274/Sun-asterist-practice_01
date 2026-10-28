package exceptions;

public class BankAccount {

    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number must not be null or empty");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient funds for withdrawal",
                balance,
                amount
            );
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

/*
 * Note: Input Validation & Business Rule Enforcement
 * 1. IllegalArgumentException: Kiểm tra tính hợp lệ của tham số đầu vào (defensive programming).
 * 2. InsufficientBalanceException: Chủ động ném ngoại lệ nghiệp vụ khi số dư không đủ, ngăn chặn trạng thái dữ liệu sai lệch.
 */
