package Model;

public class TKAdmin {
    private String taiKhoanAD;
    private String matKhauAD;
    public TKAdmin(){

    }
    public TKAdmin(String taiKhoanAD, String matKhauAD){
        this.taiKhoanAD = taiKhoanAD;
        this.matKhauAD = matKhauAD;
    }

    public void setMatKhauAD(String matKhauAD) {
        this.matKhauAD = matKhauAD;
    }

    public void setTaiKhoanAD(String taiKhoanAD) {
        this.taiKhoanAD = taiKhoanAD;
    }

    public String getMatKhauAD() {
        return matKhauAD;
    }

    public String getTaiKhoanAD() {
        return taiKhoanAD;
    }
}
