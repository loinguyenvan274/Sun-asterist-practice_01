package exceptions;

import java.sql.SQLException;

public class DatabaseService {

    public String findUserById(String id) {
        try {
            return executeQuery(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch user with id: " + id, e);
        }
    }

    private String executeQuery(String id) throws SQLException {
        if ("invalid".equals(id)) {
            throw new SQLException("Connection timeout or syntax error");
        }
        return "User_" + id;
    }
}

/*
 * Note: Exception Wrapping / Translation (Chained Exceptions)
 * Bắt ngoại lệ kỹ thuật mức thấp (SQLException) và bọc lại thành unchecked exception ở tầng service.
 * Giúp các tầng trên (Controller/Service) không bị phụ thuộc vào công nghệ lưu trữ, đồng thời giữ nguyên nguyên nhân gốc (cause) để debug.
 */
