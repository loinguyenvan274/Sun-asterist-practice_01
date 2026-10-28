package exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader {

    public String readFirstLine(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("File is empty: " + filePath);
            }
            return line;
        }
    }
}

/*
 * Note: Checked Exception & Resource Management (Try-with-resources)
 * Bắt buộc khai báo throws hoặc catch khi thao tác với ngoại vi (File, I/O).
 * Cú pháp try-with-resources tự động giải phóng tài nguyên implements AutoCloseable ngay cả khi xảy ra lỗi, tránh rò rỉ tài nguyên (resource leak).
 */
