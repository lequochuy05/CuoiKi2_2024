package Model;

import java.util.ArrayList;
import java.util.Iterator;

import DAO.KhachHangDAO;

public class DSKhachHang {
    private ArrayList<KhachHang> dsKhachHang;

    public DSKhachHang() {
        this.dsKhachHang = new ArrayList();
    }

    public DSKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public void insert(KhachHang khachHang) {
        this.dsKhachHang.add(khachHang);
    }

    public void delete(KhachHang khachHang) {
        Iterator<KhachHang> iterator= this.dsKhachHang.iterator();
        while (iterator.hasNext()) {
            KhachHang kh= iterator.next();
            if (kh.getCccd().equals(khachHang.getCccd())) {
                iterator.remove();
                break;
            }
        }
    }

    public void update(KhachHang khachHang) {
        this.delete(khachHang);
        this.dsKhachHang.add(khachHang);
    }

    public boolean kiemtraCCCD(KhachHang khachHang) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getCccd().equals(khachHang.getCccd())) {
                return true;
            }
        }
        return false;
    }

    public boolean kiemtraSDT(KhachHang khachHang) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getSoDienThoai().equals(khachHang.getSoDienThoai())) {
                return true;
            }
        }
        return false;
    }

    public void capnhatdatabase() {
        KhachHangDAO khd= new KhachHangDAO();
        dsKhachHang= khd.selectAll();
    }
}