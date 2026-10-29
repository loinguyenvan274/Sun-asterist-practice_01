package practice_2;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HangThucPham extends HangHoa {

    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HangThucPham(String maHang, String tenHang, int soLuongTon, double donGia,
                        LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia);

        if (ngaySanXuat == null) {
            throw new IllegalArgumentException("Ngày sản xuất không được để trống");
        }
        if (ngayHetHan == null) {
            throw new IllegalArgumentException("Ngày hết hạn không được để trống");
        }
        if (ngayHetHan.isBefore(ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày hết hạn phải sau hoặc bằng ngày sản xuất");
        }
        if (nhaCungCap == null || nhaCungCap.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà cung cấp không được để trống");
        }

        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap.trim();
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        if (ngaySanXuat == null) {
            throw new IllegalArgumentException("Ngày sản xuất không được để trống");
        }
        if (this.ngayHetHan != null && this.ngayHetHan.isBefore(ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày sản xuất không được sau ngày hết hạn");
        }
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        if (ngayHetHan == null) {
            throw new IllegalArgumentException("Ngày hết hạn không được để trống");
        }
        if (this.ngaySanXuat != null && ngayHetHan.isBefore(this.ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày hết hạn phải sau hoặc bằng ngày sản xuất");
        }
        this.ngayHetHan = ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        if (nhaCungCap == null || nhaCungCap.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà cung cấp không được để trống");
        }
        this.nhaCungCap = nhaCungCap.trim();
    }

    public boolean isHetHan() {
        return ngayHetHan.isBefore(LocalDate.now());
    }

    @Override
    public double getVAT() {
        return donGia * 0.05;
    }

    @Override
    public String danhGia() {
        if (soLuongTon > 0 && isHetHan()) {
            return "Khó bán";
        }
        return "Không đánh giá";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("[Thực phẩm] Mã: %-8s | Tên: %-18s | Tồn: %-4d | Đơn giá: %-10s | VAT: %-9s | NSX: %-10s | HSD: %-10s | NCC: %-15s | Đánh giá: %s",
                maHang,
                tenHang,
                soLuongTon,
                df.format(donGia),
                df.format(getVAT()),
                ngaySanXuat.format(DATE_FORMATTER),
                ngayHetHan.format(DATE_FORMATTER),
                nhaCungCap,
                danhGia());
    }
}
