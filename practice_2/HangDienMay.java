package practice_2;

import java.text.DecimalFormat;

public class HangDienMay extends HangHoa {

    private int thoiGianBaoHanh;
    private double congSuat;

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia,
                       int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia);

        if (thoiGianBaoHanh < 0) {
            throw new IllegalArgumentException("Thời gian bảo hành phải >= 0 tháng");
        }
        if (congSuat < 0) {
            throw new IllegalArgumentException("Công suất phải >= 0 KW");
        }

        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        if (thoiGianBaoHanh < 0) {
            throw new IllegalArgumentException("Thời gian bảo hành phải >= 0 tháng");
        }
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        if (congSuat < 0) {
            throw new IllegalArgumentException("Công suất phải >= 0 KW");
        }
        this.congSuat = congSuat;
    }

    @Override
    public double getVAT() {
        return donGia * 0.10;
    }

    @Override
    public String danhGia() {
        if (soLuongTon < 3) {
            return "Bán được";
        }
        return "Không đánh giá";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("[Điện máy]  Mã: %-8s | Tên: %-18s | Tồn: %-4d | Đơn giá: %-10s | VAT: %-9s | BH: %-2d tháng | Công suất: %-5.1f KW | Đánh giá: %s",
                maHang,
                tenHang,
                soLuongTon,
                df.format(donGia),
                df.format(getVAT()),
                thoiGianBaoHanh,
                congSuat,
                danhGia());
    }
}
