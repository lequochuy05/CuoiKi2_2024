package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Database.JDCBCUtil;
import Model.KhachHang;
import View.ADMIN;

public class KhachHangDAO {

    private ADMIN admin;

    public int insert(KhachHang kh) {
        int ketqua= 0;
        try {
            Connection con= JDCBCUtil.getConnection();

            String sql= "INSERT INTO dskhachhang (tenKhachHang, ngaySinh, gioiTinh, CCCD_CMND, maChuyenBay, soDienThoai, tinhTrang, loaiGhe, soGhe, url)"+
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, kh.getTenKhachHang());
            pst.setString(2, kh.getNgaySinh());
            pst.setBoolean(3, kh.isGioiTinh());
            pst.setString(4, kh.getCccd());
            pst.setString(5, kh.getMaChuyenBay());
            pst.setString(6, kh.getSoDienThoai());
            pst.setString(7, kh.getTinhTrang());
            pst.setString(8, kh.getLoaiGhe());
            pst.setInt(9, kh.getSoGhe());
            pst.setString(10, kh.getUrl());

            pst.executeUpdate();
            JDCBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public int updatetinhtrang(String ma, String tt) {
        int ketqua= 0;
        try {
            Connection con= JDCBCUtil.getConnection();

            String sql= "UPDATE dskhachhang "+
                    " SET tinhTrang= ?"+
                    " WHERE CCCD_CMND= ?";

            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, tt);
            pst.setString(2, ma);

            pst.executeUpdate();
            JDCBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public ArrayList<KhachHang> selectAll(){
        ArrayList<KhachHang> ketqua= new ArrayList<KhachHang>();
        try {
            Connection con= JDCBCUtil.getConnection();
            String sql= "SELECT* FROM dskhachhang";
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            while (rs.next()) {
                String tenkh= rs.getString("tenKhachHang");
                String ngaysinh= rs.getString("ngaySinh");
                boolean gioiTinh= rs.getBoolean("gioiTinh");
                String cccd= rs.getString("CCCD_CMND");
                String sdt= rs.getString("soDienThoai");
                String macb= rs.getString("maChuyenBay");
                String tinhtrang= rs.getString("tinhTrang");
                String loaighe= rs.getString("loaiGhe");
                int soghe= rs.getInt("soGhe");
                String url= rs.getString("url");
                KhachHang kh= new KhachHang(tenkh, ngaysinh, gioiTinh, cccd, sdt, macb, tinhtrang, loaighe, soghe, url);
                ketqua.add(kh);
            }
            JDCBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public ArrayList<KhachHang> dskhachhangdadi(String machuyenbay){
        ArrayList<KhachHang> ketqua= new ArrayList<KhachHang>();
        try {
            Connection con= JDCBCUtil.getConnection();
            String sql= "SELECT* FROM "+machuyenbay;
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            while (rs.next()) {
                String tenkh= rs.getString("tenKhachHang");
                String ngaysinh= rs.getString("ngaySinh");
                boolean gioiTinh= rs.getBoolean("gioiTinh");
                String cccd= rs.getString("CCCD_CMND");
                String sdt= rs.getString("soDienThoai");
                String macb= rs.getString("maChuyenBay");
                String tinhtrang= rs.getString("tinhTrang");
                String loaighe= rs.getString("loaiGhe");
                int soghe= rs.getInt("soGhe");
                String url= rs.getString("url");

                KhachHang kh = new KhachHang(tenkh, ngaysinh, gioiTinh, cccd, sdt, macb, tinhtrang, loaighe, soghe, url);

                ketqua.add(kh);
            }
            JDCBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }
}