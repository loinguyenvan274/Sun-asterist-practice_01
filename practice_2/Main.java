package practice_2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        DSHH danhSachHangHoa = new DSHH();
        khoiTaoDuLieuMau(danhSachHangHoa);

        Scanner scanner = new Scanner(System.in);
        boolean dangChay = true;

        while (dangChay) {
            hienThiMenu();
            System.out.print("Chọn chức năng (0-6): ");
            String luaChon = scanner.nextLine().trim();

            switch (luaChon) {
                case "1":
                    themHangThucPham(scanner, danhSachHangHoa);
                    break;
                case "2":
                    themHangDienMay(scanner, danhSachHangHoa);
                    break;
                case "3":
                    themHangSanhSu(scanner, danhSachHangHoa);
                    break;
                case "4":
                    System.out.println("\n--- TOÀN BỘ DANH SÁCH HÀNG HÓA ---");
                    danhSachHangHoa.xuatDanhSach();
                    break;
                case "5":
                    System.out.println();
                    danhSachHangHoa.xuatThongKeQuanLy();
                    break;
                case "6":
                    timKiemHangHoa(scanner, danhSachHangHoa);
                    break;
                case "0":
                    System.out.println("Kết thúc chương trình.");
                    dangChay = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 6.");
            }
        }

        scanner.close();
    }

    private static void hienThiMenu() {
        System.out.println("\n============== HỆ THỐNG QUẢN LÝ KHO SIÊU THỊ ==============");
        System.out.println("1. Thêm hàng thực phẩm");
        System.out.println("2. Thêm hàng điện máy");
        System.out.println("3. Thêm hàng sành sứ");
        System.out.println("4. Xuất danh sách hàng hóa (kèm VAT & đánh giá tiêu thụ)");
        System.out.println("5. Thống kê quản lý kho (Số lượng tồn kho & tiền thuế VAT)");
        System.out.println("6. Tìm kiếm hàng hóa theo mã");
        System.out.println("0. Thoát chương trình");
        System.out.println("============================================================");
    }

    private static void khoiTaoDuLieuMau(DSHH dshh) {
        try {
            dshh.them(new HangThucPham(
                    "TP01",
                    "Sữa tươi tiệt trùng",
                    10,
                    32000,
                    LocalDate.now().minusMonths(2),
                    LocalDate.now().minusDays(5),
                    "Vinamilk"
            ));

            dshh.them(new HangThucPham(
                    "TP02",
                    "Bánh mì gối",
                    25,
                    20000,
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(5),
                    "Kinh Đô"
            ));

            dshh.them(new HangDienMay(
                    "DM01",
                    "Tủ lạnh Inverter",
                    2,
                    8500000,
                    24,
                    1.5
            ));

            dshh.them(new HangDienMay(
                    "DM02",
                    "Máy giặt lồng ngang",
                    15,
                    9200000,
                    12,
                    2.0
            ));

            dshh.them(new HangSanhSu(
                    "SS01",
                    "Bộ bát đĩa hoa mai",
                    60,
                    450000,
                    "Bát Tràng",
                    LocalDate.now().minusDays(20)
            ));

            dshh.them(new HangSanhSu(
                    "SS02",
                    "Ly sứ cao cấp",
                    30,
                    65000,
                    "Minh Long",
                    LocalDate.now().minusDays(5)
            ));
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo dữ liệu mẫu: " + e.getMessage());
        }
    }

    private static void themHangThucPham(Scanner scanner, DSHH dshh) {
        System.out.println("\n--- THÊM MỚI HÀNG THỰC PHẨM ---");
        try {
            System.out.print("Nhập mã hàng: ");
            String maHang = scanner.nextLine().trim();

            if (dshh.timKiem(maHang) != null) {
                System.out.println("Lỗi: Mã hàng '" + maHang + "' đã tồn tại trong hệ thống. Thêm thất bại!");
                return;
            }

            System.out.print("Nhập tên hàng: ");
            String tenHang = scanner.nextLine().trim();

            System.out.print("Nhập số lượng tồn (>= 0): ");
            int soLuongTon = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập đơn giá (> 0): ");
            double donGia = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Nhập ngày sản xuất (dd/MM/yyyy): ");
            LocalDate ngaySanXuat = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

            System.out.print("Nhập ngày hết hạn (dd/MM/yyyy): ");
            LocalDate ngayHetHan = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

            System.out.print("Nhập nhà cung cấp: ");
            String nhaCungCap = scanner.nextLine().trim();

            HangThucPham htp = new HangThucPham(maHang, tenHang, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
            boolean ketQua = dshh.them(htp);

            if (ketQua) {
                System.out.println("Thêm hàng thực phẩm thành công!");
            } else {
                System.out.println("Thêm hàng thực phẩm thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Định dạng số không hợp lệ.");
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Định dạng ngày phải là dd/MM/yyyy (ví dụ: 25/12/2026).");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu: " + e.getMessage());
        }
    }

    private static void themHangDienMay(Scanner scanner, DSHH dshh) {
        System.out.println("\n--- THÊM MỚI HÀNG ĐIỆN MÁY ---");
        try {
            System.out.print("Nhập mã hàng: ");
            String maHang = scanner.nextLine().trim();

            if (dshh.timKiem(maHang) != null) {
                System.out.println("Lỗi: Mã hàng '" + maHang + "' đã tồn tại trong hệ thống. Thêm thất bại!");
                return;
            }

            System.out.print("Nhập tên hàng: ");
            String tenHang = scanner.nextLine().trim();

            System.out.print("Nhập số lượng tồn (>= 0): ");
            int soLuongTon = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập đơn giá (> 0): ");
            double donGia = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Nhập thời gian bảo hành (tháng >= 0): ");
            int thoiGianBaoHanh = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập công suất (KW >= 0): ");
            double congSuat = Double.parseDouble(scanner.nextLine().trim());

            HangDienMay hdm = new HangDienMay(maHang, tenHang, soLuongTon, donGia, thoiGianBaoHanh, congSuat);
            boolean ketQua = dshh.them(hdm);

            if (ketQua) {
                System.out.println("Thêm hàng điện máy thành công!");
            } else {
                System.out.println("Thêm hàng điện máy thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Định dạng số không hợp lệ.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu: " + e.getMessage());
        }
    }

    private static void themHangSanhSu(Scanner scanner, DSHH dshh) {
        System.out.println("\n--- THÊM MỚI HÀNG SÀNH SỨ ---");
        try {
            System.out.print("Nhập mã hàng: ");
            String maHang = scanner.nextLine().trim();

            if (dshh.timKiem(maHang) != null) {
                System.out.println("Lỗi: Mã hàng '" + maHang + "' đã tồn tại trong hệ thống. Thêm thất bại!");
                return;
            }

            System.out.print("Nhập tên hàng: ");
            String tenHang = scanner.nextLine().trim();

            System.out.print("Nhập số lượng tồn (>= 0): ");
            int soLuongTon = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập đơn giá (> 0): ");
            double donGia = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Nhập nhà sản xuất: ");
            String nhaSanXuat = scanner.nextLine().trim();

            System.out.print("Nhập ngày nhập kho (dd/MM/yyyy): ");
            LocalDate ngayNhapKho = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

            HangSanhSu hss = new HangSanhSu(maHang, tenHang, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
            boolean ketQua = dshh.them(hss);

            if (ketQua) {
                System.out.println("Thêm hàng sành sứ thành công!");
            } else {
                System.out.println("Thêm hàng sành sứ thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Định dạng số không hợp lệ.");
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Định dạng ngày phải là dd/MM/yyyy (ví dụ: 15/10/2026).");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu: " + e.getMessage());
        }
    }

    private static void timKiemHangHoa(Scanner scanner, DSHH dshh) {
        System.out.print("\nNhập mã hàng cần tìm: ");
        String maHang = scanner.nextLine().trim();
        HangHoa hh = dshh.timKiem(maHang);
        if (hh != null) {
            System.out.println("Tìm thấy hàng hóa:");
            System.out.println(hh);
        } else {
            System.out.println("Không tìm thấy hàng hóa với mã: " + maHang);
        }
    }
}
