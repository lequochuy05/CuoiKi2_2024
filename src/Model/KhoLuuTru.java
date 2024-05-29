package Model;

import java.util.ArrayList;

import DAO.LichBayDAO;

public class KhoLuuTru {
    private ArrayList<LuuTru> dsmachuyebay;

    public KhoLuuTru() {
        this.dsmachuyebay = new ArrayList();
    }

    public KhoLuuTru(ArrayList<LuuTru> dsmachuyebay) {
        this.dsmachuyebay = dsmachuyebay;
    }

    public ArrayList<LuuTru> getDsmachuyebay() {
        return dsmachuyebay;
    }

    public void setDsmachuyebay(ArrayList<LuuTru> dsmachuyebay) {
        this.dsmachuyebay = dsmachuyebay;
    }

    public boolean kiemtramachuyenbaytontai(LuuTru lt) {
        for (LuuTru luuTru : dsmachuyebay) {
            if(luuTru.getMaChuyenBay().equals(lt.getMaChuyenBay())) {
                return true;
            }
        }
        return false;
    }

    public void dulieutudatabasekholuutru() {
        LichBayDAO lbd= new LichBayDAO();
        dsmachuyebay= lbd.kholuutru();
    }
}
