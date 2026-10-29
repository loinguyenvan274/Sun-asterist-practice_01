package practice_3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        khoiTaoDuLieuMau(manager);

        Scanner scanner = new Scanner(System.in);
        boolean dangChay = true;

        while (dangChay) {
            hienThiMenu();
            System.out.print("Chọn chức năng (0-8): ");
            String luaChon = scanner.nextLine().trim();

            switch (luaChon) {
                case "1":
                    themPhuongTien(scanner, manager);
                    break;
                case "2":
                    timKiemTheoBienSo(scanner, manager);
                    break;
                case "3":
                    timKiemTheoCmnd(scanner, manager);
                    break;
                case "4":
                    xoaTheoHangSanXuat(scanner, manager);
                    break;
                case "5":
                    hienThiHangNhieuXeNhat(manager);
                    break;
                case "6":
                    sapXepBienSoGiamDan(manager);
                    break;
                case "7":
                    thongKeTheoLoaiXe(manager);
                    break;
                case "8":
                    System.out.println("\n--- TOÀN BỘ DANH SÁCH PHƯƠNG TIỆN ---");
                    manager.displayAll();
                    break;
                case "0":
                    System.out.println("Kết thúc chương trình.");
                    dangChay = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 8.");
            }
        }

        scanner.close();
    }

    private static void hienThiMenu() {
        System.out.println("\n============== HỆ THỐNG QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG ==============");
        System.out.println("1. Thêm phương tiện giao thông (Ô tô, Xe máy, Xe tải)");
        System.out.println("2. Tìm kiếm phương tiện theo biển số xe");
        System.out.println("3. Tìm phương tiện theo số CMND của chủ xe");
        System.out.println("4. Xóa tất cả phương tiện của một hãng sản xuất bất kỳ");
        System.out.println("5. Cho biết hãng sản xuất có nhiều phương tiện được quản lý nhất");
        System.out.println("6. Sắp xếp các phương tiện theo biển số xe giảm dần");
        System.out.println("7. Thống kê số lượng phương tiện theo từng loại (Ô tô, Xe máy, Xe tải)");
        System.out.println("8. Hiển thị toàn bộ danh sách phương tiện");
        System.out.println("0. Thoát chương trình");
        System.out.println("======================================================================");
    }

    private static void khoiTaoDuLieuMau(VehicleManager manager) {
        try {
            VehicleOwner owner1 = new VehicleOwner("001201009999", "Nguyễn Văn An", "an.nguyen@gmail.com");
            VehicleOwner owner2 = new VehicleOwner("001202008888", "Trần Thị Bình", "binh.tran@yahoo.com");
            VehicleOwner owner3 = new VehicleOwner("001203007777", "Lê Hoàng Cường", "cuong.le@outlook.com");

            manager.addVehicle(new Car("29A11", "Toyota", 2021, "Trắng", owner1, 5, "Xăng"));
            manager.addVehicle(new Car("30B22", "Honda", 2022, "Đen", owner2, 7, "Hybrid"));
            manager.addVehicle(new Motorcycle("29C33", "Honda", 2019, "Đỏ", owner1, 150.0));
            manager.addVehicle(new Motorcycle("59D44", "Yamaha", 2020, "Xanh", owner3, 125.0));
            manager.addVehicle(new Truck("29E55", "Suzuki", 2018, "Bạc", owner3, 1.5));
            manager.addVehicle(new Truck("51F66", "Toyota", 2023, "Vàng", owner2, 3.5));
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo dữ liệu mẫu: " + e.getMessage());
        }
    }

    private static void themPhuongTien(Scanner scanner, VehicleManager manager) {
        System.out.println("\n--- CHỌN LOẠI PHƯƠNG TIỆN CẦN THÊM ---");
        System.out.println("1. Ô tô (Car)");
        System.out.println("2. Xe máy (Motorcycle)");
        System.out.println("3. Xe tải (Truck)");
        System.out.print("Chọn loại (1-3): ");
        String loai = scanner.nextLine().trim();

        if (!loai.equals("1") && !loai.equals("2") && !loai.equals("3")) {
            System.out.println("Loại phương tiện không hợp lệ.");
            return;
        }

        try {
            System.out.print("Nhập biển số xe (đúng 5 ký tự, không trùng): ");
            String bienSo = scanner.nextLine().trim();

            if (manager.searchByVehicleNumber(bienSo) != null) {
                System.out.println("Lỗi: Biển số xe '" + bienSo + "' đã tồn tại trong hệ thống. Thêm thất bại!");
                return;
            }

            System.out.print("Nhập hãng sản xuất (Honda, Yamaha, Toyota, Suzuki): ");
            String hangSanXuat = scanner.nextLine().trim();

            System.out.print("Nhập năm sản xuất (> 2000 và <= năm hiện tại): ");
            int namSanXuat = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập màu xe: ");
            String mauXe = scanner.nextLine().trim();

            System.out.println("-> Nhập thông tin chủ xe:");
            System.out.print("   Nhập số CMND/CCCD (đúng 12 chữ số): ");
            String cmnd = scanner.nextLine().trim();

            System.out.print("   Nhập họ tên chủ xe: ");
            String hoTen = scanner.nextLine().trim();

            System.out.print("   Nhập email chủ xe: ");
            String email = scanner.nextLine().trim();

            VehicleOwner owner = new VehicleOwner(cmnd, hoTen, email);
            Vehicle vehicle;

            if (loai.equals("1")) {
                System.out.print("Nhập số chỗ ngồi (> 0): ");
                int soCho = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("Nhập kiểu động cơ (ví dụ: Xăng, Dầu, Điện, Hybrid): ");
                String loaiDongCo = scanner.nextLine().trim();

                vehicle = new Car(bienSo, hangSanXuat, namSanXuat, mauXe, owner, soCho, loaiDongCo);
            } else if (loai.equals("2")) {
                System.out.print("Nhập công suất dung tích xe máy (> 0 cc): ");
                double dungTich = Double.parseDouble(scanner.nextLine().trim());

                vehicle = new Motorcycle(bienSo, hangSanXuat, namSanXuat, mauXe, owner, dungTich);
            } else {
                System.out.print("Nhập trọng tải xe tải (> 0 tấn): ");
                double trongTai = Double.parseDouble(scanner.nextLine().trim());

                vehicle = new Truck(bienSo, hangSanXuat, namSanXuat, mauXe, owner, trongTai);
            }

            boolean thanhCong = manager.addVehicle(vehicle);
            if (thanhCong) {
                System.out.println("Thêm phương tiện thành công!");
            } else {
                System.out.println("Thêm phương tiện thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Dữ liệu số không hợp lệ.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        }
    }

    private static void timKiemTheoBienSo(Scanner scanner, VehicleManager manager) {
        System.out.print("\nNhập biển số xe cần tìm (5 ký tự): ");
        String bienSo = scanner.nextLine().trim();
        Vehicle v = manager.searchByVehicleNumber(bienSo);
        if (v != null) {
            System.out.println("Tìm thấy phương tiện:");
            System.out.println(v);
        } else {
            System.out.println("Không tìm thấy phương tiện có biển số: " + bienSo);
        }
    }

    private static void timKiemTheoCmnd(Scanner scanner, VehicleManager manager) {
        System.out.print("\nNhập số CMND/CCCD chủ xe (12 chữ số): ");
        String cmnd = scanner.nextLine().trim();
        List<Vehicle> dsXe = manager.findVehiclesByOwnerCmnd(cmnd);
        if (dsXe.isEmpty()) {
            System.out.println("Không tìm thấy phương tiện nào thuộc chủ xe có CMND: " + cmnd);
        } else {
            System.out.println("Tìm thấy " + dsXe.size() + " phương tiện thuộc chủ xe CMND " + cmnd + ":");
            for (Vehicle v : dsXe) {
                System.out.println(" - " + v);
            }
        }
    }

    private static void xoaTheoHangSanXuat(Scanner scanner, VehicleManager manager) {
        System.out.print("\nNhập hãng sản xuất cần xóa (Honda, Yamaha, Toyota, Suzuki): ");
        String hang = scanner.nextLine().trim();
        try {
            int soLuongDaXoa = manager.deleteVehiclesByManufacturer(hang);
            if (soLuongDaXoa > 0) {
                System.out.println("Đã xóa thành công " + soLuongDaXoa + " phương tiện của hãng " + hang + ".");
            } else {
                System.out.println("Không có phương tiện nào của hãng " + hang + " để xóa.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void hienThiHangNhieuXeNhat(VehicleManager manager) {
        System.out.println("\n--- HÃNG SẢN XUẤT CÓ NHIỀU PHƯƠNG TIỆN ĐƯỢC QUẢN LÝ NHẤT ---");
        Map<String, Integer> topHangs = manager.getManufacturerWithMostVehicles();
        if (topHangs.isEmpty()) {
            System.out.println("Danh sách đang trống, chưa có dữ liệu phương tiện.");
            return;
        }
        for (Map.Entry<String, Integer> entry : topHangs.entrySet()) {
            System.out.println("Hãng: " + entry.getKey() + " | Số lượng: " + entry.getValue() + " xe");
        }
    }

    private static void sapXepBienSoGiamDan(VehicleManager manager) {
        System.out.println("\n--- DANH SÁCH PHƯƠNG TIỆN SẮP XẾP THEO BIỂN SỐ GIẢM DẦN ---");
        List<Vehicle> danhSachSapXep = manager.sortVehiclesByNumberDescending();
        if (danhSachSapXep.isEmpty()) {
            System.out.println("Danh sách phương tiện đang trống.");
            return;
        }
        for (Vehicle v : danhSachSapXep) {
            System.out.println(v);
        }
    }

    private static void thongKeTheoLoaiXe(VehicleManager manager) {
        System.out.println("\n--- THỐNG KÊ SỐ LƯỢNG PHƯƠNG TIỆN THEO TỪNG LOẠI ---");
        Map<String, Integer> stats = manager.getVehicleCountByType();
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(" - " + entry.getKey() + " : " + entry.getValue() + " xe");
        }
        System.out.println("-> Tổng cộng: " + manager.getCount() + " xe");
    }
}
