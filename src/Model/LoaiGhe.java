package Model;

public class LoaiGhe {

    private int economyclass;
    private  int businessclass;
    private  int firstclass;

    public LoaiGhe(int economyclass, int businessclass, int firstclass) {
        this.economyclass = economyclass;
        this.businessclass = businessclass;
        this.firstclass = firstclass;
    }

    public int getEconomyclass() {
        return economyclass;
    }

    public void setEconomyclass(int economyclass) {
        this.economyclass = economyclass;
    }

    public int getBusinessclass() {
        return businessclass;
    }

    public void setBusinessclass(int businessclass) {
        this.businessclass = businessclass;
    }


    public int getFirstclass() {
        return firstclass;
    }

    public void setFirstclass(int firstclass) {
        this.firstclass = firstclass;
    }

    @Override
    public String toString() {
        return "LoaiGhe{" +
                "economyclass=" + economyclass +
                ", businessclass=" + businessclass +
                ", firstclass=" + firstclass +
                '}';
    }
}
