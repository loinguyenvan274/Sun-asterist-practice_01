package exceptions;

public class InsufficientBalanceException extends RuntimeException {

    private final double currentBalance;
    private final double requestedAmount;

    public InsufficientBalanceException(String message, double currentBalance, double requestedAmount) {
        super(message);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}

/*
 * Note: Custom Business Exception (Ngoại lệ nghiệp vụ)
 * Sử dụng khi vi phạm quy tắc nghiệp vụ đặc thù thay vì dùng ngoại lệ generic.
 * Cho phép mang theo dữ liệu ngữ cảnh (số dư hiện tại, số tiền yêu cầu) để ghi log hoặc trả response lỗi chuẩn xác cho client.
 */
