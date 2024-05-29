package Controller;

import View.DangNhap;
import View.NguoiDung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerDangNhap implements ActionListener {
    private NguoiDung nguoiDung;
    private DangNhap dangNhap;
    public ControllerDangNhap(NguoiDung nguoiDung){
        this.nguoiDung = nguoiDung;
    }
    public ControllerDangNhap(DangNhap dangNhap){
        this.dangNhap = dangNhap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Đăng nhập")){
            this.dangNhap.thucHienDangNhap();
        }
        if (src.equals("Đổi")){
            this.dangNhap.thucHienDoiMatKhau();
        }
        if (src.equals("Đăng ký")){
            this.dangNhap.thucHienDangKyTaiKhoan();
        }
        if (src.equals("Lấy lại mật khẩu")){
            this.dangNhap.thucHienLayLaiMatKhau();
        }
        if (src.equals("Xác nhận")){
            this.dangNhap.thucHienXoaTaiKhoan();
        }
    }
}
