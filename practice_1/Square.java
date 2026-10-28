package practice_1;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return getWidth();
    }

    public void setSide(double side) {
        setWidth(side);
        setHeight(side);
    }

    @Override
    public void displayInfo() {
        System.out.println("Square [Side: " + getSide()
                + ", Area: " + getArea()
                + ", Perimeter: " + getPerimeter() + "]");
    }
}

/*
 * Note: Lớp mở rộng Square kế thừa Rectangle
 * Minh họa tính đa hình và tái sử dụng công thức getArea(), getPerimeter() từ Rectangle khi width == height.
 */
