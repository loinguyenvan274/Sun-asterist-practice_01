package practice_1;

public class Main {

    public static void main(String[] args) {
        Shape generalShape = new Shape(10.0, 20.0);
        Rectangle rectangle = new Rectangle(5.0, 8.0);
        Circle circle = new Circle(7.0);
        Square square = new Square(4.0);

        generalShape.displayInfo();
        rectangle.displayInfo();
        circle.displayInfo();
        square.displayInfo();
    }
}

/*
 * Note: Lớp thực thi kiểm thử (Main)
 * Khởi tạo các đối tượng Shape, Rectangle, Circle (và lớp mở rộng Square) rồi hiển thị thông tin chi tiết.
 */
