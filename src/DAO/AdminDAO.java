package DAO;

import Database.JDCBCUtil;
import Model.TKAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public static AdminDAO getInstance(){
        return new AdminDAO();
    }

    public int dangKyTaiKhoan(TKAdmin tkAdmin){
        try{
            Connection connection = JDCBCUtil.getConnection();
            String sql = "INSERT INTO dstkadmin (userName, password) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, tkAdmin.getTaiKhoanAD());
            pst.setString(2, tkAdmin.getMatKhauAD());
            pst.executeUpdate();
            JDCBCUtil.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int doiMatKhau(TKAdmin tkAdmin){
        try{
            Connection connection = JDCBCUtil.getConnection();
            String sql = "UPDATE dstkadmin " +
                    "SET password = ? " +
                    "WHERE userName = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, tkAdmin.getMatKhauAD());
            pst.setString(2, tkAdmin.getTaiKhoanAD());
            pst.executeUpdate();
            JDCBCUtil.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaTaiKhoan(TKAdmin tkAdmin){
        try{
            Connection connection = JDCBCUtil.getConnection();
            String sql = "DELETE FROM dstkadmin WHERE userName = ? AND password = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, tkAdmin.getTaiKhoanAD());
            pst.setString(2, tkAdmin.getMatKhauAD());
            pst.executeUpdate();
            JDCBCUtil.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int quenMatKhau(TKAdmin tkAdmin){
        try{
            Connection connection = JDCBCUtil.getConnection();
            String sql = "UPDATE dstkadmin " +
                    "SET password = ? " +
                    "WHERE userName = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, tkAdmin.getMatKhauAD());
            pst.setString(2, tkAdmin.getTaiKhoanAD());
            pst.executeUpdate();
            JDCBCUtil.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<TKAdmin> dsTkAdmin (){
        ArrayList<TKAdmin> dsTraVe = new ArrayList<TKAdmin>();
        try{
            Connection connection = JDCBCUtil.getConnection();
            String sql = "SELECT * FROM dstkadmin";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                String taiKhoan = resultSet.getString("userName");
                String matKhau =resultSet.getString("password");
                TKAdmin tkAdmin = new TKAdmin(taiKhoan, matKhau);
                dsTraVe.add(tkAdmin);
            }
            JDCBCUtil.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsTraVe;
    }
    public boolean kiemTraTaiKhoanMatKhauAdmin(String taiKhoan, String matKhau) {
        Connection connection = null;
        try {
            connection = JDCBCUtil.getConnection();
            String sql= "SELECT * FROM dstkadmin WHERE userName = ? AND password = ?";
            PreparedStatement pts = connection.prepareStatement(sql);
            pts.setString(1, taiKhoan);
            pts.setString(2, matKhau);

            ResultSet resultSet2 = pts.executeQuery();

            if (resultSet2.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }finally {
            JDCBCUtil.closeConnection(connection);
        }
    }
    public boolean kiemTraTaiKhoanTonTai(String taiKhoan) {
        Connection connection = null;
        try {
            connection = JDCBCUtil.getConnection();
            String sql= "SELECT * FROM dstkadmin WHERE userName = ?";
            PreparedStatement pts = connection.prepareStatement(sql);
            pts.setString(1, taiKhoan);

            ResultSet resultSet2 = pts.executeQuery();

            if (resultSet2.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }finally {
            JDCBCUtil.closeConnection(connection);
        }
    }
}
