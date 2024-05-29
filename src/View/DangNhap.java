package View;

import Controller.ControllerDangNhap;
import DAO.AdminDAO;
import DAO.NguoiDungDAO;
import Model.TKAdmin;
import Model.TKNgDung;
import View.NguoiDung;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField_displayName;
	private JLabel lblDisplayName_dangky;
	private JPanel contentPane;
	private JPanel panelDangNhap;
	private JTextField txtUsername_login;
	private JLabel lblUsername_login;
	private JLabel lblPassword_login;
	private JLabel lblQuenmatkhau_login;
	private JButton btnDangnhap;
	private JLabel lblDangky_login;
	private JLabel lblChangePassword_login;
	private JPanel panelTieude;
	private JLabel llbTieude;
	private JPasswordField txtPassword_login;
	private JPanel panelDangKy;
	private JPanel panelTieude_dangky;
	private JLabel lblTieude_dangky;
	private JTextField txtUsername_dangky;
	private JLabel lblUsername_dangky;
	private JLabel lblPassword_dangky;
	private JPasswordField txtPassword_dangky;
	private JTextField txtUsernam_quenmatkhau;
	private JButton btnBack_dangky;
	private JLabel lblTieude_quenmatkhau;
	private JPanel panelDoimatkhau;
	private JPanel panelTieude_Quenmatkhau_1;
	private JLabel lblTieude_doimk;
	private JLabel lblUsername_doimk;
	private JTextField txtUsername_doimk;
	private JButton btnBack_quenmk;
	private JLabel lblPassword_doimk;
	private JPasswordField txtPassword_doimk;
	private JLabel lblNewPassword_doimk;
	private JPasswordField txtNewPassword_doimk;
	private JButton btnDoimatkhau;
	private JButton btnBack_doimk;
	private JButton btnXemlai_dangky;
	private JButton btnXemlai_quenmatkhau;
	private JLabel lblXemlai_doimk;
	private JButton btnRdCaptcha;
	private CardLayout cardLayout;
	private JTextField txtCaptcha1_quenmk;
	private JTextField txtCaptcha2_quenmatkhau;
	private JCheckBox cbAdmin_login;
	private JCheckBox cbAdmin_quenmatkhau;
	private JCheckBox cbAdmin_doimatkhau;
	private JCheckBox cbAdmin_dangky;
	private String thongbao = "Không có thông báo nào cho bạn";
	private JButton btnDangKy;
	private JTextField ttxUsername_xoatk;
	private JPasswordField txtPassword_xoatk;
	private JCheckBox cbAdmin_xoatk;
	private JButton btnXacNhan_xoatk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (isServerRunning("localhost", 9090)) {
						DangNhap frame = new DangNhap();
						frame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Không thể kết nối đến server...");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static boolean isServerRunning(String address, int port) {
		try (Socket ignored = new Socket(address, port)) {
			// Nếu kết nối thành công, server đang chạy
			return true;
		} catch (Exception e) {
			// Nếu không kết nối được, server không chạy
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		ControllerDangNhap controllerDN = new ControllerDangNhap(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 432);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		setContentPane(contentPane);
		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);

		panelDangNhap = new JPanel();
		panelDangNhap.setBorder(new LineBorder(new Color(192, 192, 192)));
		contentPane.add(panelDangNhap, "panelDangNhap");
		panelDangNhap.setLayout(null);

		txtUsername_login = new JTextField();
		txtUsername_login.setBounds(32, 120, 258, 35);
		txtUsername_login.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUsername_login.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername_login.setColumns(10);
		panelDangNhap.add(txtUsername_login);

		lblUsername_login = new JLabel("Tài khoản");
		lblUsername_login.setBounds(32, 93, 115, 25);
		lblUsername_login.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelDangNhap.add(lblUsername_login);

		lblPassword_login = new JLabel("Mật khẩu");
		lblPassword_login.setBounds(32, 165, 115, 25);
		lblPassword_login.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelDangNhap.add(lblPassword_login);

		lblQuenmatkhau_login = new JLabel("Lấy lại mật khẩu?");
		lblQuenmatkhau_login.setBounds(185, 309, 115, 20);
		lblQuenmatkhau_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelDangNhap.add(lblQuenmatkhau_login);
		lblQuenmatkhau_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contentPane, "panelQuenmatkhau");
				txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
				txtUsername_login.setText("");
				txtPassword_login.setText("");
				cbAdmin_login.setSelected(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblQuenmatkhau_login.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblQuenmatkhau_login.setForeground(Color.BLACK);
			}

		});

		btnDangnhap = new JButton("Đăng nhập");
		btnDangnhap.setBounds(32, 260, 258, 45);
		btnDangnhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangnhap.setBackground(new Color(255, 255, 255));
		panelDangNhap.add(btnDangnhap);
		btnDangnhap.addActionListener(controllerDN);

		lblDangky_login = new JLabel("Đăng ký?");
		lblDangky_login.setBounds(32, 309, 136, 25);
		lblDangky_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelDangNhap.add(lblDangky_login);
		lblDangky_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contentPane, "panelDangKy");
				txtUsername_login.setText("");
				txtPassword_login.setText("");
				cbAdmin_login.setSelected(false);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblDangky_login.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblDangky_login.setForeground(Color.BLUE);
			}
		});

		lblChangePassword_login = new JLabel("Đổi mật khẩu?");
		lblChangePassword_login.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword_login.setBounds(195, 230, 100, 25);
		lblChangePassword_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelDangNhap.add(lblChangePassword_login);
		lblChangePassword_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contentPane, "panelDoimatkhau");
				txtUsername_login.setText("");
				txtPassword_login.setText("");
				cbAdmin_login.setSelected(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblChangePassword_login.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblChangePassword_login.setForeground(Color.BLACK);
			}
		});

		cbAdmin_login = new JCheckBox("Admin");
		cbAdmin_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbAdmin_login.setBounds(32, 230, 70, 21);
		panelDangNhap.add(cbAdmin_login);

		panelTieude = new JPanel();
		panelTieude.setBackground(new Color(192, 192, 192));
		panelTieude.setBounds(0, 0, 322, 78);
		panelDangNhap.add(panelTieude);
		panelTieude.setLayout(new BorderLayout(0, 0));

		llbTieude = new JLabel("Đăng nhập");
		llbTieude.setFont(new Font("Tahoma", Font.PLAIN, 30));
		llbTieude.setHorizontalAlignment(SwingConstants.CENTER);
		panelTieude.add(llbTieude, BorderLayout.CENTER);

		txtPassword_login = new JPasswordField();
		txtPassword_login.setBounds(32, 190, 258, 35);
		panelDangNhap.add(txtPassword_login);

		JLabel lblXoaTaiKhoan = new JLabel("Xóa tài khoản");
		lblXoaTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblXoaTaiKhoan.setBounds(32, 330, 91, 25);
		panelDangNhap.add(lblXoaTaiKhoan);
		lblXoaTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contentPane, "panelXoaTaiKhoan");
				txtUsername_login.setText("");
				txtPassword_login.setText("");
				cbAdmin_login.setSelected(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblXoaTaiKhoan.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblXoaTaiKhoan.setForeground(Color.BLACK);
			}
		});

		panelDangKy = new JPanel();
		contentPane.add(panelDangKy, "panelDangKy");
		panelDangKy.setLayout(null);

		panelTieude_dangky = new JPanel();
		panelTieude_dangky.setBackground(Color.LIGHT_GRAY);
		panelTieude_dangky.setBounds(0, 0, 322, 44);
		panelDangKy.add(panelTieude_dangky);
		panelTieude_dangky.setLayout(new BorderLayout(0, 0));

		lblTieude_dangky = new JLabel("Đăng ký");
		lblTieude_dangky.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTieude_dangky.setHorizontalAlignment(SwingConstants.CENTER);
		panelTieude_dangky.add(lblTieude_dangky, BorderLayout.CENTER);

		txtUsername_dangky = new JTextField();
		txtUsername_dangky.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUsername_dangky.setColumns(10);
		txtUsername_dangky.setBounds(32, 157, 258, 35);
		panelDangKy.add(txtUsername_dangky);

		lblUsername_dangky = new JLabel("Tài khoản");
		lblUsername_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername_dangky.setBounds(32, 122, 115, 25);
		panelDangKy.add(lblUsername_dangky);

		lblPassword_dangky = new JLabel("Mật khẩu");
		lblPassword_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword_dangky.setBounds(32, 202, 115, 25);
		panelDangKy.add(lblPassword_dangky);

		txtPassword_dangky = new JPasswordField();
		txtPassword_dangky.setBounds(32, 237, 258, 35);
		panelDangKy.add(txtPassword_dangky);

		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangKy.setBackground(Color.WHITE);
		btnDangKy.setBounds(32, 305, 258, 35);
		panelDangKy.add(btnDangKy);
		btnDangKy.addActionListener(controllerDN);

		cbAdmin_dangky = new JCheckBox("Admin");
		cbAdmin_dangky.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbAdmin_dangky.setBounds(32, 278, 93, 21);
		panelDangKy.add(cbAdmin_dangky);

		btnBack_dangky = new JButton("Quay lại");
		btnBack_dangky.setBackground(new Color(255, 255, 255));
		btnBack_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack_dangky.setBounds(87, 350, 203, 35);
		panelDangKy.add(btnBack_dangky);
		btnBack_dangky.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "panelDangNhap");
				cbAdmin_dangky.setSelected(false);
				txtUsername_dangky.setText("");
				txtPassword_dangky.setText("");
			}
		});

		btnXemlai_dangky = new JButton("?");
		btnXemlai_dangky.setBackground(new Color(255, 255, 255));
		btnXemlai_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemlai_dangky.setBounds(32, 350, 45, 35);
		panelDangKy.add(btnXemlai_dangky);

		lblDisplayName_dangky = new JLabel("Tên Hiển Thị");
		lblDisplayName_dangky.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDisplayName_dangky.setBounds(32, 52, 155, 25);
		panelDangKy.add(lblDisplayName_dangky);

		textField_displayName = new JTextField();
		textField_displayName.setHorizontalAlignment(SwingConstants.LEFT);
		textField_displayName.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_displayName.setColumns(10);
		textField_displayName.setBounds(32, 77, 258, 35);
		panelDangKy.add(textField_displayName);
		btnXemlai_dangky.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JPanel panelQuenmatkhau = new JPanel();
		panelQuenmatkhau.setLayout(null);
		contentPane.add(panelQuenmatkhau, "panelQuenmatkhau");

		JPanel panelTieude_Quenmatkhau = new JPanel();
		panelTieude_Quenmatkhau.setBackground(Color.LIGHT_GRAY);
		panelTieude_Quenmatkhau.setBounds(0, 0, 322, 78);
		panelQuenmatkhau.add(panelTieude_Quenmatkhau);
		panelTieude_Quenmatkhau.setLayout(new BorderLayout(0, 0));

		lblTieude_quenmatkhau = new JLabel("Lấy lại mật khẩu");
		lblTieude_quenmatkhau.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTieude_quenmatkhau.setHorizontalAlignment(SwingConstants.CENTER);
		panelTieude_Quenmatkhau.add(lblTieude_quenmatkhau, BorderLayout.CENTER);

		txtUsernam_quenmatkhau = new JTextField();
		txtUsernam_quenmatkhau.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsernam_quenmatkhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUsernam_quenmatkhau.setColumns(10);
		txtUsernam_quenmatkhau.setBounds(32, 120, 258, 35);
		panelQuenmatkhau.add(txtUsernam_quenmatkhau);

		JLabel lblUsername_quenmatkhau = new JLabel("Tài khoản");
		lblUsername_quenmatkhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername_quenmatkhau.setBounds(32, 93, 115, 25);
		panelQuenmatkhau.add(lblUsername_quenmatkhau);

		JLabel lblCaptcha_quenmk = new JLabel("Captcha");
		lblCaptcha_quenmk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCaptcha_quenmk.setBounds(32, 165, 115, 25);
		panelQuenmatkhau.add(lblCaptcha_quenmk);

		JButton btnLaylaimk = new JButton("Lấy lại mật khẩu");
		btnLaylaimk.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLaylaimk.setBackground(Color.WHITE);
		btnLaylaimk.setBounds(32, 270, 258, 45);
		panelQuenmatkhau.add(btnLaylaimk);
		btnLaylaimk.addActionListener(controllerDN);

		cbAdmin_quenmatkhau = new JCheckBox("Admin");
		cbAdmin_quenmatkhau.setBackground(new Color(255, 255, 255));
		cbAdmin_quenmatkhau.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbAdmin_quenmatkhau.setBounds(32, 240, 93, 21);
		panelQuenmatkhau.add(cbAdmin_quenmatkhau);

		btnBack_quenmk = new JButton("Quay lại");
		btnBack_quenmk.setBackground(new Color(255, 255, 255));
		btnBack_quenmk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack_quenmk.setBounds(87, 330, 203, 45);
		panelQuenmatkhau.add(btnBack_quenmk);
		btnBack_quenmk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "panelDangNhap");
				cbAdmin_quenmatkhau.setSelected(false);
				txtUsernam_quenmatkhau.setText("");
				txtCaptcha1_quenmk.setText("");
				txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			}
		});

		btnXemlai_quenmatkhau = new JButton("?");
		btnXemlai_quenmatkhau.setBackground(new Color(255, 255, 255));
		btnXemlai_quenmatkhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemlai_quenmatkhau.setBounds(32, 330, 45, 45);
		panelQuenmatkhau.add(btnXemlai_quenmatkhau);
		btnXemlai_quenmatkhau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnRdCaptcha = new JButton("@");
		btnRdCaptcha.setBackground(new Color(255, 255, 255));
		btnRdCaptcha.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRdCaptcha.setBounds(240, 194, 50, 35);
		panelQuenmatkhau.add(btnRdCaptcha);
		btnRdCaptcha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			}
		});

		txtCaptcha1_quenmk = new JTextField();
		txtCaptcha1_quenmk.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCaptcha1_quenmk.setBounds(32, 194, 93, 35);
		panelQuenmatkhau.add(txtCaptcha1_quenmk);
		txtCaptcha1_quenmk.setColumns(10);

		txtCaptcha2_quenmatkhau = new JTextField();
		txtCaptcha2_quenmatkhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCaptcha2_quenmatkhau.setEditable(false);
		txtCaptcha2_quenmatkhau.setColumns(10);
		txtCaptcha2_quenmatkhau.setBounds(137, 194, 93, 35);
		panelQuenmatkhau.add(txtCaptcha2_quenmatkhau);

		panelDoimatkhau = new JPanel();
		contentPane.add(panelDoimatkhau, "panelDoimatkhau");
		panelDoimatkhau.setLayout(null);

		panelTieude_Quenmatkhau_1 = new JPanel();
		panelTieude_Quenmatkhau_1.setBackground(Color.LIGHT_GRAY);
		panelTieude_Quenmatkhau_1.setBounds(0, 0, 322, 78);
		panelDoimatkhau.add(panelTieude_Quenmatkhau_1);
		panelTieude_Quenmatkhau_1.setLayout(new BorderLayout(0, 0));

		lblTieude_doimk = new JLabel("Đổi mật khẩu");
		lblTieude_doimk.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTieude_doimk.setHorizontalAlignment(SwingConstants.CENTER);
		panelTieude_Quenmatkhau_1.add(lblTieude_doimk, BorderLayout.CENTER);

		lblUsername_doimk = new JLabel("Tài khoản");
		lblUsername_doimk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername_doimk.setBounds(32, 93, 115, 25);
		panelDoimatkhau.add(lblUsername_doimk);

		txtUsername_doimk = new JTextField();
		txtUsername_doimk.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername_doimk.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUsername_doimk.setColumns(10);
		txtUsername_doimk.setBounds(32, 120, 258, 35);
		panelDoimatkhau.add(txtUsername_doimk);

		lblPassword_doimk = new JLabel("Mật khẩu cũ");
		lblPassword_doimk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword_doimk.setBounds(32, 165, 115, 25);
		panelDoimatkhau.add(lblPassword_doimk);

		txtPassword_doimk = new JPasswordField();
		txtPassword_doimk.setBounds(32, 190, 258, 35);
		panelDoimatkhau.add(txtPassword_doimk);

		lblNewPassword_doimk = new JLabel("Mật khẩu mới");
		lblNewPassword_doimk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewPassword_doimk.setBounds(32, 235, 115, 25);
		panelDoimatkhau.add(lblNewPassword_doimk);

		txtNewPassword_doimk = new JPasswordField();
		txtNewPassword_doimk.setBounds(32, 264, 258, 35);
		panelDoimatkhau.add(txtNewPassword_doimk);

		btnDoimatkhau = new JButton("Đổi");
		btnDoimatkhau.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDoimatkhau.setBackground(new Color(255, 255, 255));
		btnDoimatkhau.setBounds(32, 330, 146, 45);
		panelDoimatkhau.add(btnDoimatkhau);
		btnDoimatkhau.addActionListener(controllerDN);

		cbAdmin_doimatkhau = new JCheckBox("Admin");
		cbAdmin_doimatkhau.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbAdmin_doimatkhau.setBounds(29, 305, 90, 21);
		panelDoimatkhau.add(cbAdmin_doimatkhau);

		btnBack_doimk = new JButton("Quay lại");
		btnBack_doimk.setBackground(new Color(255, 255, 255));
		btnBack_doimk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack_doimk.setBounds(188, 330, 102, 45);
		panelDoimatkhau.add(btnBack_doimk);
		btnBack_doimk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "panelDangNhap");
				cbAdmin_doimatkhau.setSelected(false);
				txtUsername_doimk.setText("");
				txtPassword_doimk.setText("");
				txtNewPassword_doimk.setText("");
			}
		});

		lblXemlai_doimk = new JLabel("Xem lại thông báo");
		lblXemlai_doimk.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblXemlai_doimk.setBounds(178, 305, 112, 13);
		lblXemlai_doimk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblXemlai_doimk.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblXemlai_doimk.setForeground(Color.BLACK);
			}
		});
		panelDoimatkhau.add(lblXemlai_doimk);

		JPanel panelXoaTaiKhoan = new JPanel();
		contentPane.add(panelXoaTaiKhoan, "panelXoaTaiKhoan");
		panelXoaTaiKhoan.setLayout(null);

		JPanel panelTieude_XoaTK = new JPanel();
		panelTieude_XoaTK.setBackground(Color.LIGHT_GRAY);
		panelTieude_XoaTK.setBounds(0, 0, 322, 78);
		panelXoaTaiKhoan.add(panelTieude_XoaTK);
		panelTieude_XoaTK.setLayout(new BorderLayout(0, 0));

		JLabel lblXoataikhoan_xoa = new JLabel("Xóa tài khoản");
		lblXoataikhoan_xoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblXoataikhoan_xoa.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panelTieude_XoaTK.add(lblXoataikhoan_xoa, BorderLayout.CENTER);

		ttxUsername_xoatk = new JTextField();
		ttxUsername_xoatk.setHorizontalAlignment(SwingConstants.LEFT);
		ttxUsername_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		ttxUsername_xoatk.setColumns(10);
		ttxUsername_xoatk.setBounds(32, 120, 258, 35);
		panelXoaTaiKhoan.add(ttxUsername_xoatk);

		JLabel lblUsername_xoatk = new JLabel("Tài khoản");
		lblUsername_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername_xoatk.setBounds(32, 93, 115, 25);
		panelXoaTaiKhoan.add(lblUsername_xoatk);

		JLabel lblPassword_xoatk = new JLabel("Mật khẩu");
		lblPassword_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword_xoatk.setBounds(32, 165, 115, 25);
		panelXoaTaiKhoan.add(lblPassword_xoatk);

		txtPassword_xoatk = new JPasswordField();
		txtPassword_xoatk.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPassword_xoatk.setColumns(10);
		txtPassword_xoatk.setBounds(32, 195, 258, 35);
		panelXoaTaiKhoan.add(txtPassword_xoatk);

		cbAdmin_xoatk = new JCheckBox("Admin");
		cbAdmin_xoatk.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbAdmin_xoatk.setBounds(32, 236, 93, 21);
		panelXoaTaiKhoan.add(cbAdmin_xoatk);

		btnXacNhan_xoatk = new JButton("Xác nhận");
		btnXacNhan_xoatk.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXacNhan_xoatk.setBackground(Color.WHITE);
		btnXacNhan_xoatk.setBounds(32, 263, 258, 45);
		panelXoaTaiKhoan.add(btnXacNhan_xoatk);
		btnXacNhan_xoatk.addActionListener(controllerDN);

		JButton btnXemlai_xoatk = new JButton("?");
		btnXemlai_xoatk.setBackground(new Color(255, 255, 255));
		btnXemlai_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemlai_xoatk.setBounds(32, 323, 45, 45);
		panelXoaTaiKhoan.add(btnXemlai_xoatk);
		btnXemlai_xoatk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton btnBack_xoatk = new JButton("Quay lại");
		btnBack_xoatk.setBackground(new Color(255, 255, 255));
		btnBack_xoatk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack_xoatk.setBounds(87, 323, 203, 45);
		panelXoaTaiKhoan.add(btnBack_xoatk);
		btnBack_xoatk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "panelDangNhap");
				cbAdmin_xoatk.setSelected(false);
				ttxUsername_xoatk.setText("");
				txtPassword_xoatk.setText("");
			}
		});
		setVisible(true);
	}

	public void thucHienDangNhap() {
		String taiKhoan = txtUsername_login.getText();
		char[] matKhau = txtPassword_login.getPassword();

		if (taiKhoan.isEmpty() || matKhau.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên người dùng và mật khẩu", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (cbAdmin_login.isSelected()) {
			thucHienDangNhapAdmin(taiKhoan, matKhau);
		} else {
			thucHienDangNhapNguoiDung(taiKhoan, matKhau);
		}

		txtPassword_login.setText("");
	}

	private void thucHienDangNhapAdmin(String taiKhoan, char[] matKhau) {
		boolean kiemtraAdmin = AdminDAO.getInstance().kiemTraTaiKhoanMatKhauAdmin(taiKhoan, new String(matKhau));
		if (kiemtraAdmin) {
			JPasswordField matKhauTruyCap = new JPasswordField();
			Object[] message = { "Nhập mật khẩu truy cập: ", matKhauTruyCap };
			int option = JOptionPane.showConfirmDialog(this, message, "Messenger", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				char[] input = matKhauTruyCap.getPassword();
				if (new String(input).equals("010605")) {
					try {

						ADMIN admin = new ADMIN();
						admin.setVisible(true);
						cbAdmin_login.setSelected(false);
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showConfirmDialog(this, "Mật khẩu truy cập không chính xác", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Nhập sai tài khoản/mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void thucHienDangNhapNguoiDung(String taiKhoan, char[] matKhau) {
		boolean kiemtraNgDung = NguoiDungDAO.getInstance().kiemTraTaiKhoanMatKhauNguoiDung(taiKhoan,
				new String(matKhau));
		if (kiemtraNgDung) {
			NguoiDung nguoiDung = null;
			try {
				nguoiDung = new NguoiDung();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			nguoiDung.setVisible(true);
			String tenht = NguoiDungDAO.getInstance().laytendangnhap(taiKhoan, new String(matKhau));
			nguoiDung.lblNewLabel_tenTK.setText(tenht);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Nhập sai tài khoản/mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void thucHienDoiMatKhau() {
		String taiKhoan = txtUsername_doimk.getText();
		char[] matKhau = txtPassword_doimk.getPassword();
		char[] matKhauMoi = txtNewPassword_doimk.getPassword();

		if (taiKhoan.isEmpty() || matKhau.length == 0 || matKhauMoi.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên người dùng và mật khẩu", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (new String(matKhau).equalsIgnoreCase(new String(matKhauMoi))) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu mới trùng nhau", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (cbAdmin_doimatkhau.isSelected()) {
			thucHienDoiMatKhauAdmin(taiKhoan, new String(matKhau), new String(matKhauMoi));
		} else {
			thucHienDoiMatKhauNguoiDung(taiKhoan, new String(matKhau), new String(matKhauMoi));
		}
		txtNewPassword_doimk.setText("");
		txtPassword_doimk.setText("");
		txtUsername_doimk.setText("");
	}

	private void thucHienDoiMatKhauAdmin(String taiKhoan, String matKhau, String matKhauMoi) {
		boolean kiemTraAdmin = AdminDAO.getInstance().kiemTraTaiKhoanMatKhauAdmin(taiKhoan, matKhau);
		if (kiemTraAdmin) {
			JPasswordField matKhauTruyCap = new JPasswordField();
			Object[] message = { "Nhập mật khẩu truy cập: ", matKhauTruyCap };
			int option = JOptionPane.showConfirmDialog(this, message, "Messenger", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				char[] inputps = matKhauTruyCap.getPassword();
				if (new String(inputps).equals("010605")) {
					TKAdmin tkAdmin = new TKAdmin(taiKhoan, matKhauMoi);
					AdminDAO.getInstance().doiMatKhau(tkAdmin);
					cbAdmin_doimatkhau.setSelected(false);
					thongbao = "Đổi mật khẩu thành công!\n" + "Mật khẩu mới của bạn là: " + matKhauMoi;
					JOptionPane.showMessageDialog(this,
							"Đổi mật khẩu thành công!\n" + "Mật khẩu mới của bạn là: " + matKhauMoi);
				} else {
					JOptionPane.showConfirmDialog(this, "Mật khẩu truy cập không chính xác", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tài khoản/mật khẩu không chính xác!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void thucHienDoiMatKhauNguoiDung(String taiKhoan, String matKhau, String matKhauMoi) {
		boolean kiemTraNgDung = NguoiDungDAO.getInstance().kiemTraTaiKhoanMatKhauNguoiDung(taiKhoan, matKhau);
		if (kiemTraNgDung) {
			TKNgDung tkNgDung = new TKNgDung(taiKhoan, matKhauMoi, null);
			NguoiDungDAO.getInstance().doiMatKhau(tkNgDung);
			thongbao = "Đổi mật khẩu thành công!\n" + "Mật khẩu mới của bạn là: " + matKhauMoi;
			JOptionPane.showMessageDialog(this,
					"Đổi mật khẩu thành công!\n" + "Mật khẩu mới của bạn là: " + matKhauMoi);
		} else {
			JOptionPane.showMessageDialog(this, "Tài khoản/mật khẩu không chính xác!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void thucHienDangKyTaiKhoan() {
		String taiKhoan = txtUsername_dangky.getText();
		char[] matKhau = txtPassword_dangky.getPassword();
		String encodepass = Base64.getEncoder().encodeToString(new String(matKhau).getBytes());
		String tenht = textField_displayName.getText();

		if (taiKhoan.isEmpty() || matKhau.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản/mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (taiKhoan.equalsIgnoreCase(new String(matKhau))) {
			JOptionPane.showMessageDialog(this, "Tài khoản và mật khẩu trùng nhau", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			if (cbAdmin_dangky.isSelected()) {
				thucHienDangKyTaiKhoanAdmin(taiKhoan, new String(matKhau));
			} else
				thucHienDangKyTaiKhoanNguoiDung(taiKhoan, new String(matKhau), tenht);
		}
		txtUsername_dangky.setText("");
		txtPassword_dangky.setText("");
	}

	private void thucHienDangKyTaiKhoanAdmin(String taiKhoan, String matKhau) {
		boolean kiemTraTonTai = AdminDAO.getInstance().kiemTraTaiKhoanTonTai(taiKhoan);
		if (kiemTraTonTai) {
			JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
			txtUsername_dangky.setText("");
			txtPassword_dangky.setText("");
			return;
		}
		JPasswordField matKhauTruyCap = new JPasswordField();
		Object[] message = { "Nhập mật khẩu truy cập: ", matKhauTruyCap };
		int option = JOptionPane.showConfirmDialog(this, message, "Messenger", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (option == JOptionPane.OK_OPTION) {
			char[] inputps = matKhauTruyCap.getPassword();
			if (new String(inputps).equals("010605")) {
				TKAdmin tkAdmin = new TKAdmin(taiKhoan, matKhau);
				AdminDAO.getInstance().dangKyTaiKhoan(tkAdmin);
				cbAdmin_dangky.setSelected(false);
				thongbao = "Đăng ký tài khoản: " + taiKhoan + ", mật khẩu: " + matKhau + " thành công!";
				JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this, "Mật khẩu truy cập không chính xác", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void thucHienDangKyTaiKhoanNguoiDung(String taiKhoan, String matKhau, String tenHt) {
		boolean kiemTraNgDung = NguoiDungDAO.getInstance().kiemTraTaiKhoanTonTai(taiKhoan);
		if (kiemTraNgDung) {
			JOptionPane.showMessageDialog(this, "Tài khoản/mật khẩu không chính xác!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		TKNgDung tkNgDung = new TKNgDung(taiKhoan, matKhau, tenHt);
		NguoiDungDAO.getInstance().dangKyTaiKhoan(tkNgDung);
		thongbao = "Đăng ký tài khoản: " + taiKhoan + ", mật khẩu: " + matKhau + " thành công!";
		JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	public void thucHienLayLaiMatKhau() {
		String taiKhoan = txtUsernam_quenmatkhau.getText();
		String captcha1 = txtCaptcha1_quenmk.getText();
		String captcha2 = txtCaptcha2_quenmatkhau.getText();

		if (taiKhoan.isEmpty()) {
			txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (cbAdmin_quenmatkhau.isSelected()) {
			thucHienLaylaiMatKhauAdmin(taiKhoan, captcha1, captcha2);
		} else {
			thucHienLaylaiMatKhauNguoiDung(taiKhoan, captcha1, captcha2);
		}
		txtUsernam_quenmatkhau.setText("");
		txtCaptcha1_quenmk.setText("");
	}

	private void thucHienLaylaiMatKhauAdmin(String taiKhoan, String captcha1, String captcha2) {
		boolean kiemTraTonTai = AdminDAO.getInstance().kiemTraTaiKhoanTonTai(taiKhoan);
		if (kiemTraTonTai) {
			JPasswordField matKhauTruyCap = new JPasswordField();
			Object[] message = { "Nhập mật khẩu truy cập: ", matKhauTruyCap };
			int option = JOptionPane.showConfirmDialog(this, message, "Messenger", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				char[] inputps = matKhauTruyCap.getPassword();
				if (new String(inputps).equals("010605")) {
					if (captcha1.equalsIgnoreCase(captcha2)) {
						String matKhauMoi = String.valueOf(random());
						TKAdmin tkAdmin = new TKAdmin(taiKhoan, matKhauMoi);
						AdminDAO.getInstance().quenMatKhau(tkAdmin);
						cbAdmin_quenmatkhau.setSelected(false);
						thongbao = "Lấy lại mật khẩu thành công!\nMật khẩu mới của bạn là: " + matKhauMoi;
						JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
					}
				} else {
					txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
					JOptionPane.showConfirmDialog(this, "Mật khẩu truy cập không chính xác", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void thucHienLaylaiMatKhauNguoiDung(String taiKhoan, String captcha1, String captcha2) {
		boolean kiemTraTonTai = NguoiDungDAO.getInstance().kiemTraTaiKhoanTonTai(taiKhoan);
		if (kiemTraTonTai) {
			if (captcha1.equalsIgnoreCase(captcha2)) {
				String matKhauMoi = String.valueOf(random());
				TKNgDung tkNgDung = new TKNgDung(taiKhoan, matKhauMoi, null);
				NguoiDungDAO.getInstance().quenMatKhau(tkNgDung);
				thongbao = "Lấy lại mật khẩu thành công!\nMật khẩu mới của bạn là: " + matKhauMoi;
				JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			} else {
				JOptionPane.showMessageDialog(this, "Mã captcha không đúng", "ERROR", JOptionPane.ERROR_MESSAGE);
				txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			}
		} else {
			txtCaptcha2_quenmatkhau.setText(String.valueOf(random()));
			JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private int random() {
		Random random = new Random();
		return random.nextInt(9000) + 1000;
	}

	public void thucHienXoaTaiKhoan() {
		String taiKhoan = ttxUsername_xoatk.getText();
		char[] matKhau = txtPassword_xoatk.getPassword();

		if (taiKhoan.isEmpty() || matKhau.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (cbAdmin_xoatk.isSelected()) {
			thucHienXoaTaiKhoanAdmin(taiKhoan, new String(matKhau));
		} else {
			thucHienXoaTaiKhoanNguoiDung(taiKhoan, new String(matKhau));
		}
		txtPassword_xoatk.setText("");
		ttxUsername_xoatk.setText("");
	}

	public void thucHienXoaTaiKhoanAdmin(String taiKhoan, String matKhau) {
		boolean kiemTraTonTai = AdminDAO.getInstance().kiemTraTaiKhoanMatKhauAdmin(taiKhoan, matKhau);
		if (kiemTraTonTai) {
			JPasswordField matKhauTruyCap = new JPasswordField();
			Object[] message = { "Nhập mật khẩu truy cập: ", matKhauTruyCap };
			int option = JOptionPane.showConfirmDialog(this, message, "ERROR", JOptionPane.OK_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				char[] input = matKhauTruyCap.getPassword();
				if (new String(input).equals("010605")) {
					TKAdmin tkAdmin = new TKAdmin(taiKhoan, matKhau);
					AdminDAO.getInstance().xoaTaiKhoan(tkAdmin);
					cbAdmin_xoatk.setSelected(false);
					thongbao = "Xóa tài khoản " + taiKhoan + " thành công!";
					JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(this, "Mật khẩu truy cập không chính xác", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Nhập sai tài khoản/mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void thucHienXoaTaiKhoanNguoiDung(String taiKhoan, String matKhau) {
		boolean kiemTraTonTai = NguoiDungDAO.getInstance().kiemTraTaiKhoanMatKhauNguoiDung(taiKhoan, matKhau);
		if (kiemTraTonTai) {
			TKNgDung nguoiDung = new TKNgDung(taiKhoan, matKhau, null);
			NguoiDungDAO.getInstance().xoaTaiKhoan(nguoiDung);
			thongbao = "Xóa tài khoản " + taiKhoan + " thành công!";
			JOptionPane.showMessageDialog(this, thongbao, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Nhập sai tài khoản/mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}