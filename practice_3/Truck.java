package practice_3;

public class Truck extends Vehicle {

    private double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int yearOfManufacture, String color, VehicleOwner owner,
                 double tonnage) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);

        if (tonnage <= 0) {
            throw new IllegalArgumentException("Trọng tải xe tải phải lớn hơn 0");
        }

        this.tonnage = tonnage;
    }

    @Override
    public String getVehicleType() {
        return "Xe tải";
    }

    public double getTonnage() {
        return tonnage;
    }

    public double getTrongTai() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        if (tonnage <= 0) {
            throw new IllegalArgumentException("Trọng tải xe tải phải lớn hơn 0");
        }
        this.tonnage = tonnage;
    }

    public void setTrongTai(double trongTai) {
        setTonnage(trongTai);
    }

    @Override
    public String toString() {
        return String.format("[%s] Biển số: %-5s | Hãng: %-7s | Năm SX: %-4d | Màu: %-8s | Trọng tải: %-5.1f tấn | %s",
                getVehicleType(), vehicleNumber, manufacturer, yearOfManufacture, color, tonnage, owner);
    }
}
