package practice_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {

    private final List<Vehicle> vehicles;

    public VehicleManager() {
        this.vehicles = new ArrayList<>();
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }

        if (searchByVehicleNumber(vehicle.getVehicleNumber()) != null) {
            return false;
        }

        for (Vehicle v : vehicles) {
            if (v.getOwner().getCmndNumber().equals(vehicle.getOwner().getCmndNumber())) {
                if (!v.getOwner().getFullName().equalsIgnoreCase(vehicle.getOwner().getFullName())
                        || !v.getOwner().getEmail().equalsIgnoreCase(vehicle.getOwner().getEmail())) {
                    throw new IllegalArgumentException("Số CMND " + vehicle.getOwner().getCmndNumber()
                            + " đã tồn tại nhưng thông tin họ tên/email không khớp với chủ xe đã lưu");
                }
            }
        }

        vehicles.add(vehicle);
        return true;
    }

    public boolean themPhuongTien(Vehicle vehicle) {
        return addVehicle(vehicle);
    }

    public Vehicle searchByVehicleNumber(String vehicleNumber) {
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            return null;
        }
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(vehicleNumber.trim())) {
                return v;
            }
        }
        return null;
    }

    public Vehicle timKiemTheoBienSo(String bienSo) {
        return searchByVehicleNumber(bienSo);
    }

    public List<Vehicle> findVehiclesByOwnerCmnd(String cmnd) {
        List<Vehicle> result = new ArrayList<>();
        if (cmnd == null || cmnd.trim().isEmpty()) {
            return result;
        }
        for (Vehicle v : vehicles) {
            if (v.getOwner().getCmndNumber().equals(cmnd.trim())) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> timXeTheoCmnd(String cmnd) {
        return findVehiclesByOwnerCmnd(cmnd);
    }

    public int deleteVehiclesByManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            return 0;
        }
        String normalized = Vehicle.validateAndNormalizeManufacturer(manufacturer);
        int initialSize = vehicles.size();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(normalized));
        return initialSize - vehicles.size();
    }

    public int xoaTheoHangSanXuat(String hangSanXuat) {
        return deleteVehiclesByManufacturer(hangSanXuat);
    }

    public Map<String, Integer> getManufacturerWithMostVehicles() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Vehicle v : vehicles) {
            countMap.put(v.getManufacturer(), countMap.getOrDefault(v.getManufacturer(), 0) + 1);
        }

        int maxCount = 0;
        for (int count : countMap.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        Map<String, Integer> result = new LinkedHashMap<>();
        if (maxCount == 0) {
            return result;
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public Map<String, Integer> getHangCoNhieuXeNhat() {
        return getManufacturerWithMostVehicles();
    }

    public List<Vehicle> sortVehiclesByNumberDescending() {
        List<Vehicle> sortedList = new ArrayList<>(vehicles);
        sortedList.sort(Comparator.comparing(Vehicle::getVehicleNumber).reversed());
        return sortedList;
    }

    public List<Vehicle> sapXepBienSoGiamDan() {
        return sortVehiclesByNumberDescending();
    }

    public Map<String, Integer> getVehicleCountByType() {
        Map<String, Integer> stats = new LinkedHashMap<>();
        stats.put("Ô tô", 0);
        stats.put("Xe máy", 0);
        stats.put("Xe tải", 0);

        for (Vehicle v : vehicles) {
            if (v instanceof Car) {
                stats.put("Ô tô", stats.get("Ô tô") + 1);
            } else if (v instanceof Motorcycle) {
                stats.put("Xe máy", stats.get("Xe máy") + 1);
            } else if (v instanceof Truck) {
                stats.put("Xe tải", stats.get("Xe tải") + 1);
            }
        }
        return stats;
    }

    public Map<String, Integer> thongKeTheoLoaiXe() {
        return getVehicleCountByType();
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public int getCount() {
        return vehicles.size();
    }

    public void displayAll() {
        if (vehicles.isEmpty()) {
            System.out.println("Danh sách phương tiện đang trống.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}
