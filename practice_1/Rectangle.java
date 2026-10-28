package practice_1;

public class Rectangle extends Shape {

    public Rectangle(double width, double height) {
        super(width, height);
    }

    public double getArea() {
        return getWidth() * getHeight();
    }

    public double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }

    @Override
    public void displayInfo() {
        System.out.println("Rectangle [Width: " + getWidth()
                + ", Height: " + getHeight()
                + ", Area: " + getArea()
                + ", Perimeter: " + getPerimeter() + "]");
    }
}

/*
 * Note: Lớp con Rectangle kế thừa từ Shape
 * Bổ sung các phương thức tính diện tích (getArea = w * l) và chu vi (getPerimeter = 2 * (l + w)).
 */
