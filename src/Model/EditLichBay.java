package Model;

public class EditLichBay {
    private String maChuyenBay;
    private String ngayBay;
    private String gioBay;
    private String diemkhoihanh;
    private String diemHaCanh;
    private String hangBay;
    private int sucChua;

    public EditLichBay() {

    }

    public EditLichBay(String maChuyenBay, String ngayBay, String gioBay, String diemkhoihanh, String diemHaCanh,
                       String hangBay, int sucChua) {
        this.maChuyenBay = maChuyenBay;
        this.ngayBay = ngayBay;
        this.gioBay = gioBay;
        this.diemkhoihanh = diemkhoihanh;
        this.diemHaCanh = diemHaCanh;
        this.hangBay = hangBay;
        this.sucChua = sucChua;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(String ngayBay) {
        this.ngayBay = ngayBay;
    }

    public String getGioBay() {
        return gioBay;
    }

    public void setGioBay(String gioBay) {
        this.gioBay = gioBay;
    }

    public String getDiemkhoihanh() {
        return diemkhoihanh;
    }

    public void setDiemkhoihanh(String diemkhoihanh) {
        this.diemkhoihanh = diemkhoihanh;
    }

    public String getDiemHaCanh() {
        return diemHaCanh;
    }

    public void setDiemHaCanh(String diemHaCanh) {
        this.diemHaCanh = diemHaCanh;
    }

    public String getHangBay() {
        return hangBay;
    }

    public void setHangBay(String hangBay) {
        this.hangBay = hangBay;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    @Override
    public String toString() {
        return "EditLichBay [maChuyenBay=" + maChuyenBay + ", ngayBay=" + ngayBay + ", gioBay=" + gioBay
                + ", diemkhoihanh=" + diemkhoihanh + ", diemHaCanh=" + diemHaCanh + ", hangBay=" + hangBay
                + ", sucChua=" + sucChua + "]";
    }
}