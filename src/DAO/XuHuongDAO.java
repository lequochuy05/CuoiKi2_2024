package DAO;

import Database.JDCBCUtil;
import Model.LoaiGhe;
import View.ADMIN;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XuHuongDAO {
    private LichBayDAO lichBayDAO;
    private ADMIN admin;
    public XuHuongDAO() {
        this.lichBayDAO = new LichBayDAO();
    }
    public LoaiGhe xuHuongDatQuaCacNam(String year) {
        String[] macb = laydanhsachcacchuyenbaycungnam(year);
        int eco = 0;
        int busin = 0;
        int first = 0;
        for(String ma : macb) {
            try {
                Connection con = JDCBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT loaiGhe FROM " + ma);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    if(rs.getString("loaiGhe").equals("EconomyClass")) {
                        eco ++;
                    } else if (rs.getString("loaiGhe").equals("BusinessClass")) {
                        busin ++;
                    } else if (rs.getString("loaiGhe").equals("FirstClass")) {
                        first ++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
        }
        return new LoaiGhe(eco, busin, first);
    }

    public String[] laydanhsachcacchuyenbaycungnam(String year) {
        List<String> ketqua = new ArrayList<>();
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT maChuyenBay FROM luutru WHERE SUBSTRING_INDEX(ngayBay, '/', -1) = '" + year + " '");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ketqua.add(rs.getString("maChuyenBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua.toArray(new String[0]);
    }
    public String[] laynam() {
        List<String> ketqua = new ArrayList<>();
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT SUBSTRING_INDEX(ngayBay, '/', -1) AS nam FROM luutru");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ketqua.add(rs.getString("nam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua.toArray(new String[0]);
    }
    public String[] layHangBay() {
        List<String> hangBayList = new ArrayList<>();

        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT DISTINCT hangBay FROM lichBay INNER JOIN dsKhachHang ON lichBay.maChuyenBay = dsKhachHang.maChuyenBay");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String hangBay = rs.getString("hangBay");
                hangBayList.add(hangBay);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hangBayList.toArray(new String[0]);
    }
    public int demSoLuongHangBay(String hangBay) {
        int soLuong = 0;
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS total FROM dsKhachHang"
                    + " INNER JOIN lichBay ON dsKhachHang.maChuyenBay = lichBay.maChuyenBay WHERE lichBay.hangBay = ?");
            pst.setString(1, hangBay);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("total");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    //TK hãng bay

    //Ktra năm
    public boolean kiemTraNam(String nam) {
        boolean tonTaiDuLieu = false;
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT COUNT(*) AS total FROM luutru WHERE SUBSTRING_INDEX(ngayBay, '/', -1) = ?");
            pst.setString(1, nam);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int soLuong = rs.getInt("total");
                if (soLuong > 0) {
                    tonTaiDuLieu = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tonTaiDuLieu;
    }


    // --//
    // Lay hang bay
    public String[] layHangBay(String nam) {
        List<String> hangBayList = new ArrayList<>();

        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT DISTINCT hangBay FROM luuTru WHERE SUBSTRING_INDEX(ngayBay, '/', -1) = ?");
            pst.setString(1, nam);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String hangBay = rs.getString("hangBay");
                hangBayList.add(hangBay);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hangBayList.toArray(new String[0]);
    }

    // ==//
    // Đếm
    public int demSoLuongHangBay(String hangBay, String nam) {
        int soLuong = 0;
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(hangBay) AS total FROM luuTru WHERE hangBay = ? "
                    + "AND SUBSTRING_INDEX(ngayBay, '/', -1) = ?");
            pst.setString(1, hangBay);
            pst.setString(2, nam);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("total");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    //TK doanh Thu
    public double xuHuongDoanhThuTheoThang(String year, int month) {
        double doanhThuThang = 0;
        String[] macb = laydanhsachcacchuyenbaycungnamThang(year, month);
        for (String ma : macb) {
            try {
                Connection con = JDCBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT SUM(CASE WHEN loaiGhe = 'EconomyClass' THEN 80 "
                        + "WHEN loaiGhe = 'BusinessClass' THEN 500 "
                        + "WHEN loaiGhe = 'FirstClass' THEN 150 "
                        + "ELSE 0 END) AS DoanhThu FROM " + ma + " WHERE tinhTrang = 'Đã hoàn thành'");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    double gia = rs.getDouble("DoanhThu");
                    doanhThuThang += gia;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return doanhThuThang;
    }

    // Hàm lấy danh sách các chuyến bay trong một tháng của năm
    public String[] laydanhsachcacchuyenbaycungnamThang(String year, int month) {
        List<String> ketqua = new ArrayList<>();
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(
                    "SELECT maChuyenBay FROM luutru WHERE SUBSTRING_INDEX(ngayBay, '/', -2) = ? AND SUBSTRING_INDEX(ngayBay, '/', -1) = ?");
            pst.setInt(1, month);
            pst.setString(2, year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ketqua.add(rs.getString("maChuyenBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua.toArray(new String[0]);
    }
}
