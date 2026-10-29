package practice_3;

public class Motorcycle extends Vehicle {

    private double capacity;

    public Motorcycle(String vehicleNumber, String manufacturer, int yearOfManufacture, String color, VehicleOwner owner,
                      double capacity) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);

        if (capacity <= 0) {
            throw new IllegalArgumentException("Dung tích công suất xe máy phải lớn hơn 0");
        }

        this.capacity = capacity;
    }

    @Override
    public String getVehicleType() {
        return "Xe máy";
    }

    public double getCapacity() {
        return capacity;
    }

    public double getDungTich() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Dung tích công suất xe máy phải lớn hơn 0");
        }
        this.capacity = capacity;
    }

    public void setDungTich(double dungTich) {
        setCapacity(dungTich);
    }

    @Override
    public String toString() {
        return String.format("[%s] Biển số: %-5s | Hãng: %-7s | Năm SX: %-4d | Màu: %-8s | Công suất: %-5.1f cc | %s",
                getVehicleType(), vehicleNumber, manufacturer, yearOfManufacture, color, capacity, owner);
    }
}
