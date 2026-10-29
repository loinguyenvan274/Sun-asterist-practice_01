package practice_2;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HangSanhSu extends HangHoa {

    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HangSanhSu(String maHang, String tenHang, int soLuongTon, double donGia,
                      String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia);

        if (nhaSanXuat == null || nhaSanXuat.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà sản xuất không được để trống");
        }
        if (ngayNhapKho == null) {
            throw new IllegalArgumentException("Ngày nhập kho không được để trống");
        }
        if (ngayNhapKho.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày nhập kho không được sau ngày hiện tại");
        }

        this.nhaSanXuat = nhaSanXuat.trim();
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        if (nhaSanXuat == null || nhaSanXuat.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà sản xuất không được để trống");
        }
        this.nhaSanXuat = nhaSanXuat.trim();
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(LocalDate ngayNhapKho) {
        if (ngayNhapKho == null) {
            throw new IllegalArgumentException("Ngày nhập kho không được để trống");
        }
        if (ngayNhapKho.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày nhập kho không được sau ngày hiện tại");
        }
        this.ngayNhapKho = ngayNhapKho;
    }

    public long getThoiGianLuuKhoNgay() {
        return ChronoUnit.DAYS.between(ngayNhapKho, LocalDate.now());
    }

    @Override
    public double getVAT() {
        return donGia * 0.10;
    }

    @Override
    public String danhGia() {
        if (soLuongTon > 50 && getThoiGianLuuKhoNgay() > 10) {
            return "Bán chậm";
        }
        return "Không đánh giá";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("[Sành sứ]   Mã: %-8s | Tên: %-18s | Tồn: %-4d | Đơn giá: %-10s | VAT: %-9s | NSX: %-15s | Ngày nhập: %-10s | Lưu kho: %-3d ngày | Đánh giá: %s",
                maHang,
                tenHang,
                soLuongTon,
                df.format(donGia),
                df.format(getVAT()),
                nhaSanXuat,
                ngayNhapKho.format(DATE_FORMATTER),
                getThoiGianLuuKhoNgay(),
                danhGia());
    }
}
