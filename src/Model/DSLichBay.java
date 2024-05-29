package Model;

import java.util.ArrayList;
import java.util.Iterator;

import DAO.LichBayDAO;
import View.ADMIN;

public class DSLichBay {
    private ArrayList<EditLichBay> dsLichBay;
    private ADMIN admin;

    public DSLichBay() {
        this.dsLichBay = new ArrayList();
    }

    public DSLichBay(ArrayList<EditLichBay> dsLichBay) {
        this.dsLichBay = dsLichBay;
    }

    public ArrayList<EditLichBay> getDsLichBay() {
        return dsLichBay;
    }

    public void setDsLichBay(ArrayList<EditLichBay> dsLichBay) {
        this.dsLichBay = dsLichBay;
    }

    public void insert(EditLichBay lb) {
        this.dsLichBay.add(lb);
    }

    public void delete(EditLichBay lb) {
        Iterator<EditLichBay> iterator= this.dsLichBay.iterator();
        while (iterator.hasNext()) {
            EditLichBay edlb= iterator.next();
            if (edlb.getMaChuyenBay().equals(lb.getMaChuyenBay())) {
                iterator.remove();
                break;
            }
        }
    }

    public void update(EditLichBay lb) {
        this.delete(lb);
        this.dsLichBay.add(lb);
    }

    public void dulieutudatabaselichbay() {
        LichBayDAO lbd= new LichBayDAO();
        dsLichBay= lbd.dsmacblichbay();
    }

    public boolean kiemtramachuyenbaytontai(EditLichBay lb) {
        for (EditLichBay editLichBay : dsLichBay) {
            if(editLichBay.getMaChuyenBay().equals(lb.getMaChuyenBay())) {
                return true;
            }
        }
        return false;
    }

    public boolean kiemtragiobay(EditLichBay lb) {
        int solan=0;
        for (EditLichBay editLichBay : dsLichBay) {
            if(editLichBay.getNgayBay().equals(lb.getNgayBay()) && editLichBay.getGioBay().equals(lb.getGioBay()) && (editLichBay.getDiemkhoihanh().equals(lb.getDiemkhoihanh()) || editLichBay.getDiemHaCanh().equals(lb.getDiemHaCanh()))) {
                solan++;
            }
        }
        return solan < 3;
    }

    public void laydulieutudatabase() {
        LichBayDAO lbd= new LichBayDAO();
        dsLichBay= lbd.selectAll();
    }
}