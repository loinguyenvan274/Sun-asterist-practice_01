package practice_2;

import java.text.DecimalFormat;
import java.util.Arrays;

public class DSHH {

    private HangHoa[] danhSach;
    private int count;

    public DSHH() {
        this(20);
    }

    public DSHH(int capacity) {
        if (capacity <= 0) {
            capacity = 20;
        }
        this.danhSach = new HangHoa[capacity];
        this.count = 0;
    }

    public boolean them(HangHoa hangHoa) {
        if (hangHoa == null) {
            return false;
        }

        if (timViTri(hangHoa.getMaHang()) != -1) {
            return false;
        }

        if (count >= danhSach.length) {
            danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
        }

        danhSach[count++] = hangHoa;
        return true;
    }

    public boolean add(HangHoa hangHoa) {
        return them(hangHoa);
    }

    public int timViTri(String maHang) {
        if (maHang == null || maHang.trim().isEmpty()) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            if (danhSach[i].getMaHang().equalsIgnoreCase(maHang.trim())) {
                return i;
            }
        }
        return -1;
    }

    public HangHoa timKiem(String maHang) {
        int viTri = timViTri(maHang);
        if (viTri != -1) {
            return danhSach[viTri];
        }
        return null;
    }

    public HangHoa findByCode(String code) {
        return timKiem(code);
    }

    public int getCount() {
        return count;
    }

    public HangHoa[] getDanhSach() {
        return Arrays.copyOf(danhSach, count);
    }

    public int getTongSoLuongTonHangThucPham() {
        int tong = 0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangThucPham) {
                tong += danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public int getTongSoLuongTonHangDienMay() {
        int tong = 0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangDienMay) {
                tong += danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public int getTongSoLuongTonHangSanhSu() {
        int tong = 0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangSanhSu) {
                tong += danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public int getTongSoLuongTon() {
        int tong = 0;
        for (int i = 0; i < count; i++) {
            tong += danhSach[i].getSoLuongTon();
        }
        return tong;
    }

    public double getTongVATHangThucPham() {
        double tong = 0.0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangThucPham) {
                tong += danhSach[i].getVAT() * danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public double getTongVATHangDienMay() {
        double tong = 0.0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangDienMay) {
                tong += danhSach[i].getVAT() * danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public double getTongVATHangSanhSu() {
        double tong = 0.0;
        for (int i = 0; i < count; i++) {
            if (danhSach[i] instanceof HangSanhSu) {
                tong += danhSach[i].getVAT() * danhSach[i].getSoLuongTon();
            }
        }
        return tong;
    }

    public double getTongVAT() {
        double tong = 0.0;
        for (int i = 0; i < count; i++) {
            tong += danhSach[i].getVAT() * danhSach[i].getSoLuongTon();
        }
        return tong;
    }

    public void xuatDanhSach() {
        if (count == 0) {
            System.out.println("Danh sách hàng hóa đang trống.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(danhSach[i]);
        }
    }

    public void xuatThongKeQuanLy() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("================================ THỐNG KÊ QUẢN LÝ KHO ================================");
        System.out.println("1. Số lượng tồn kho theo từng loại:");
        System.out.println("   - Hàng thực phẩm : " + getTongSoLuongTonHangThucPham() + " sản phẩm");
        System.out.println("   - Hàng điện máy  : " + getTongSoLuongTonHangDienMay() + " sản phẩm");
        System.out.println("   - Hàng sành sứ   : " + getTongSoLuongTonHangSanhSu() + " sản phẩm");
        System.out.println("   -> Tổng tồn kho  : " + getTongSoLuongTon() + " sản phẩm");
        System.out.println("2. Tổng tiền thuế VAT tồn kho theo từng loại:");
        System.out.println("   - Hàng thực phẩm (5%) : " + df.format(getTongVATHangThucPham()) + " VNĐ");
        System.out.println("   - Hàng điện máy  (10%): " + df.format(getTongVATHangDienMay()) + " VNĐ");
        System.out.println("   - Hàng sành sứ   (10%): " + df.format(getTongVATHangSanhSu()) + " VNĐ");
        System.out.println("   -> Tổng tiền VAT      : " + df.format(getTongVAT()) + " VNĐ");
        System.out.println("======================================================================================");
    }
}
