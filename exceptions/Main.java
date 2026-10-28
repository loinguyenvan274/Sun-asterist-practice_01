package exceptions;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        testBusinessException();
        testCheckedExceptionWithResource();
        testExceptionChaining();
    }

    private static void testBusinessException() {
        try {
            BankAccount account = new BankAccount("ACC-12345", 500.0);
            account.withdraw(800.0);
        } catch (IllegalArgumentException e) {
            System.err.println("Input validation error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.err.println("Business error: " + e.getMessage()
                    + " | Current: " + e.getCurrentBalance()
                    + " | Requested: " + e.getRequestedAmount());
        }
    }

    private static void testCheckedExceptionWithResource() {
        ConfigFileReader reader = new ConfigFileReader();
        try {
            String line = reader.readFirstLine("non_existent_config.txt");
            System.out.println("Config content: " + line);
        } catch (IOException e) {
            System.err.println("I/O error handled: " + e.getMessage());
        }
    }

    private static void testExceptionChaining() {
        DatabaseService dbService = new DatabaseService();
        try {
            String user = dbService.findUserById("invalid");
            System.out.println("Found user: " + user);
        } catch (RuntimeException e) {
            System.err.println("Service layer caught: " + e.getMessage());
            System.err.println("Root cause: " + e.getCause().getMessage());
        }
    }
}

/*
 * Note: Tổng hợp xử lý ngoại lệ trong thực tế
 * 1. Thứ tự catch từ cụ thể đến khái quát: Bắt InsufficientBalanceException trước RuntimeException/Exception.
 * 2. Xử lý Checked Exception có phương án cứu vãn (fallback, log) thay vì để ứng dụng crash đột ngột.
 * 3. Trích xuất getCause() để phục vụ việc truy vết gốc rễ lỗi khi sử dụng chained exceptions.
 */
