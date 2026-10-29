package practice_3;

import java.util.Objects;
import java.util.regex.Pattern;

public class VehicleOwner {

    private final String cmndNumber;
    private String fullName;
    private String email;

    private static final Pattern CMND_PATTERN = Pattern.compile("^\\d{12}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public VehicleOwner(String cmndNumber, String fullName, String email) {
        if (cmndNumber == null || !CMND_PATTERN.matcher(cmndNumber.trim()).matches()) {
            throw new IllegalArgumentException("Số CMND/CCCD phải có đúng 12 chữ số");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên chủ xe không được để trống");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Email không đúng định dạng");
        }

        this.cmndNumber = cmndNumber.trim();
        this.fullName = fullName.trim();
        this.email = email.trim();
    }

    public String getCmndNumber() {
        return cmndNumber;
    }

    public String getCmnd() {
        return cmndNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHoTen() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên chủ xe không được để trống");
        }
        this.fullName = fullName.trim();
    }

    public void setHoTen(String hoTen) {
        setFullName(hoTen);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Email không đúng định dạng");
        }
        this.email = email.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VehicleOwner that = (VehicleOwner) o;
        return Objects.equals(cmndNumber, that.cmndNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmndNumber);
    }

    @Override
    public String toString() {
        return String.format("[Chủ xe: %s | CMND: %s | Email: %s]", fullName, cmndNumber, email);
    }
}
