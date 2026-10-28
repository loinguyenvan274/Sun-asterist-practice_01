package practice_1;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        setWidth(radius * 2);
        setHeight(radius * 2);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        double diameter = radius * 2;
        return diameter * 3.14;
    }

    @Override
    public void displayInfo() {
        System.out.println("Circle [Radius: " + radius
                + ", Diameter: " + (radius * 2)
                + ", Area: " + getArea()
                + ", Circumference: " + getCircumference() + "]");
    }
}

/*
 * Note: Lớp con Circle kế thừa từ Shape
 * Khởi tạo width và height của Shape theo đường kính (2 * r).
 * Bổ sung tính diện tích getArea (pi * r^2) và chu vi getCircumference (d * 3.14).
 */
