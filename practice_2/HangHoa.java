package practice_2;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class HangHoa {

    protected final String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;

    public HangHoa(String maHang, String tenHang, int soLuongTon, double donGia) {
        if (maHang == null || maHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã hàng không được để trống");
        }
        if (tenHang == null || tenHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên hàng không được để trống");
        }
        if (soLuongTon < 0) {
            throw new IllegalArgumentException("Số lượng tồn phải >= 0");
        }
        if (donGia <= 0) {
            throw new IllegalArgumentException("Đơn giá phải > 0");
        }

        this.maHang = maHang.trim();
        this.tenHang = tenHang.trim();
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    public abstract double getVAT();

    public abstract String danhGia();

    public double tinhVAT() {
        return getVAT();
    }

    public String evaluateConsumption() {
        return danhGia();
    }

    public String getMaHang() {
        return maHang;
    }

    public String getProductCode() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public String getName() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        if (tenHang == null || tenHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên hàng không được để trống");
        }
        this.tenHang = tenHang.trim();
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public int getInventoryQuantity() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        if (soLuongTon < 0) {
            throw new IllegalArgumentException("Số lượng tồn phải >= 0");
        }
        this.soLuongTon = soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getUnitPrice() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia <= 0) {
            throw new IllegalArgumentException("Đơn giá phải > 0");
        }
        this.donGia = donGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HangHoa hangHoa = (HangHoa) o;
        return Objects.equals(maHang, hangHoa.maHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHang);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("Mã: %-10s | Tên: %-20s | Tồn kho: %-5d | Đơn giá: %-12s | VAT: %-10s | Đánh giá: %-15s",
                maHang, tenHang, soLuongTon, df.format(donGia), df.format(getVAT()), danhGia());
    }
}
