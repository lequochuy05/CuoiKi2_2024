package Model;

public class KhachHang {
    private String tenKhachHang;
    private String ngaySinh;
    private boolean gioiTinh;
    private String cccd;
    private String soDienThoai;
    private String maChuyenBay;
    private String tinhTrang;
    private String loaiGhe;
    private int soGhe;
    private String url;

    public KhachHang() {

    }

    public KhachHang(String tenKhachHang, String ngaySinh, boolean gioiTinh, String cccd, String soDienThoai,
                     String maChuyenBay, String tinhTrang, String loaiGhe, int soGhe, String url) {
        this.tenKhachHang = tenKhachHang;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.maChuyenBay = maChuyenBay;
        this.tinhTrang = tinhTrang;
        this.loaiGhe = loaiGhe;
        this.soGhe = soGhe;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    @Override
    public String toString() {
        return "KhachHang [tenKhachHang=" + tenKhachHang + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
                + ", cccd=" + cccd + ", soDienThoai=" + soDienThoai + ", maChuyenBay=" + maChuyenBay + ", tinhTrang="
                + tinhTrang + ", loaiGhe=" + loaiGhe + ", soGhe=" + soGhe + ", url=" + url + "]";
    }

}