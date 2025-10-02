package QuanLyHangHoa;

import java.io.Serializable;

public class HangHoa implements Serializable {
    private String TenHangHoa;
    private String MaHangHoa;
    private String DonViTinh;
    private String SoLuong;

    public HangHoa(String TenHangHoa, String MaHangHoa, String DonViTinh, String SoLuong) {
        this.TenHangHoa = TenHangHoa;
        this.MaHangHoa = MaHangHoa;
        this.DonViTinh = DonViTinh;
        this.SoLuong = SoLuong;
    }

    public String getTenHangHoa() {
        return TenHangHoa;
    }

    public void setTenHangHoa(String TenHangHoa) {
        this.TenHangHoa = TenHangHoa;
    }

    public String getMaHangHoa() {
        return MaHangHoa;
    }

    public void setMaHangHoa(String MaHangHoa) {
        this.MaHangHoa = MaHangHoa;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    @Override
    public String toString() {
        return "Hàng hóa[Tên hàng hóa: " + TenHangHoa + ", Mã hàng hóa: " + MaHangHoa + ", Đơn vị tính: " + DonViTinh
                + ", Số lượng: " + SoLuong + "]";
    }
}