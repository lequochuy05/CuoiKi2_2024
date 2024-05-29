package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.ADMIN;
import View.NguoiDung;

public class QLCBController implements ActionListener {
    private NguoiDung nguoiDung;
    private ADMIN admin;

    public QLCBController(ADMIN admin) {
        this.admin = admin;
    }

    public QLCBController(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Thêm   ")) {
            this.admin.thuchienthem();
        } else if (src.equals("Tìm")) {
            this.admin.thuchientim();
        } else if (src.equals("Chỉnh Sửa")) {
            this.admin.thuchienchinhsua();
        } else if (src.equals("Xóa       ")) {
            this.admin.thuchienxoa();
        } else if (src.equals("Thông Báo")) {
            this.admin.xacnhankhachhang();
        } else if (src.equals("H.Thành")) {
            this.admin.hoanthanhchuyenbay();}
//        } else if (src.equals("Tìm")) {
//            this.admin.timcackhachhang();
//        }
    }

}