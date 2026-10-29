package practice_3;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Vehicle {

    public static final List<String> ALLOWED_MANUFACTURERS = Arrays.asList("Honda", "Yamaha", "Toyota", "Suzuki");

    protected final String vehicleNumber;
    protected String manufacturer;
    protected int yearOfManufacture;
    protected String color;
    protected VehicleOwner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int yearOfManufacture, String color, VehicleOwner owner) {
        if (vehicleNumber == null || vehicleNumber.trim().length() != 5) {
            throw new IllegalArgumentException("Biển số xe phải có đúng 5 ký tự");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Màu xe không được để trống");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Chủ xe không được để trống");
        }

        int currentYear = Year.now().getValue();
        if (yearOfManufacture <= 2000 || yearOfManufacture > currentYear) {
            throw new IllegalArgumentException("Năm sản xuất phải lớn hơn 2000 và nhỏ hơn hoặc bằng " + currentYear);
        }

        this.vehicleNumber = vehicleNumber.trim().toUpperCase();
        this.manufacturer = validateAndNormalizeManufacturer(manufacturer);
        this.yearOfManufacture = yearOfManufacture;
        this.color = color.trim();
        this.owner = owner;
    }

    public static String validateAndNormalizeManufacturer(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Hãng sản xuất không được để trống");
        }
        for (String allowed : ALLOWED_MANUFACTURERS) {
            if (allowed.equalsIgnoreCase(input.trim())) {
                return allowed;
            }
        }
        throw new IllegalArgumentException("Hãng sản xuất chỉ bao gồm: Honda, Yamaha, Toyota, Suzuki");
    }

    public abstract String getVehicleType();

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getBienSo() {
        return vehicleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getHangSanXuat() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = validateAndNormalizeManufacturer(manufacturer);
    }

    public void setHangSanXuat(String hangSanXuat) {
        setManufacturer(hangSanXuat);
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getNamSanXuat() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        int currentYear = Year.now().getValue();
        if (yearOfManufacture <= 2000 || yearOfManufacture > currentYear) {
            throw new IllegalArgumentException("Năm sản xuất phải lớn hơn 2000 và nhỏ hơn hoặc bằng " + currentYear);
        }
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setNamSanXuat(int namSanXuat) {
        setYearOfManufacture(namSanXuat);
    }

    public String getColor() {
        return color;
    }

    public String getMauXe() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Màu xe không được để trống");
        }
        this.color = color.trim();
    }

    public void setMauXe(String mauXe) {
        setColor(mauXe);
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public VehicleOwner getChuXe() {
        return owner;
    }

    public void setOwner(VehicleOwner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Chủ xe không được để trống");
        }
        this.owner = owner;
    }

    public void setChuXe(VehicleOwner chuXe) {
        setOwner(chuXe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleNumber, vehicle.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleNumber);
    }

    @Override
    public String toString() {
        return String.format("[%s] Biển số: %-5s | Hãng: %-7s | Năm SX: %-4d | Màu: %-8s | %s",
                getVehicleType(), vehicleNumber, manufacturer, yearOfManufacture, color, owner);
    }
}
