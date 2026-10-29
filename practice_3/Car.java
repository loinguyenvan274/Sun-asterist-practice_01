package practice_3;

public class Car extends Vehicle {

    private int numberOfSeats;
    private String engineType;

    public Car(String vehicleNumber, String manufacturer, int yearOfManufacture, String color, VehicleOwner owner,
               int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);

        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Số chỗ ngồi phải lớn hơn 0");
        }
        if (engineType == null || engineType.trim().isEmpty()) {
            throw new IllegalArgumentException("Kiểu động cơ không được để trống");
        }

        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType.trim();
    }

    @Override
    public String getVehicleType() {
        return "Ô tô";
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getSoChoNgoi() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Số chỗ ngồi phải lớn hơn 0");
        }
        this.numberOfSeats = numberOfSeats;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        setNumberOfSeats(soChoNgoi);
    }

    public String getEngineType() {
        return engineType;
    }

    public String getLoaiDongCo() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        if (engineType == null || engineType.trim().isEmpty()) {
            throw new IllegalArgumentException("Kiểu động cơ không được để trống");
        }
        this.engineType = engineType.trim();
    }

    public void setLoaiDongCo(String loaiDongCo) {
        setEngineType(loaiDongCo);
    }

    @Override
    public String toString() {
        return String.format("[%s] Biển số: %-5s | Hãng: %-7s | Năm SX: %-4d | Màu: %-8s | Chỗ ngồi: %-2d | Động cơ: %-10s | %s",
                getVehicleType(), vehicleNumber, manufacturer, yearOfManufacture, color, numberOfSeats, engineType, owner);
    }
}
