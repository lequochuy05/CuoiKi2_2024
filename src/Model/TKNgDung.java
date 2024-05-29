package Model;

public class TKNgDung {
    private String taiKhoanNG;
    private String matKhauNG;
    private String tenHienThi;
    public TKNgDung(){

    }
    public TKNgDung(String taiKhoanNG, String matKhauNG, String tenHienThi){
        this.taiKhoanNG = taiKhoanNG;
        this.matKhauNG = matKhauNG;
        this.tenHienThi = tenHienThi;
    }

    public void setMatKhauNG(String matKhauNG) {
        this.matKhauNG = matKhauNG;
    }

    public void setTaiKhoanNG(String taiKhoanNG) {
        this.taiKhoanNG = taiKhoanNG;
    }

    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public String getMatKhauNG() {
        return matKhauNG;
    }

    public String getTaiKhoanNG() {
        return taiKhoanNG;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }
}
