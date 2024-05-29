package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.NguoiDung;

public class ControllerDatVe implements ActionListener {
    private NguoiDung nguoiDung;

    public ControllerDatVe(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src= e.getActionCommand();
        if (src.equals("Đặt")) {
            this.nguoiDung.thuchienthem();
        } else if (src.equals("Tra Cứu")) {
            this.nguoiDung.thuchientracuukhachhang();
        } else if (src.equals("Hủy Chuyến")) {
            this.nguoiDung.thuchienhuychuyen();
        } else if (src.equals(" Tìm")) {
            this.nguoiDung.thuchientim();
        }
    }
}