package Model;

public class LuuTru {
    private String maChuyenBay;

    public LuuTru(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    @Override
    public String toString() {
        return "LuuTru [maChuyenBay=" + maChuyenBay + "]";
    }
}
