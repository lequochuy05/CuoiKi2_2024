package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ControllerDatVe;
import Controller.QLCBController;
import DAO.KhachHangDAO;
import DAO.LichBayDAO;
import Mail.Emailto;
import Model.DSKhachHang;
import Model.DSLichBay;
import Model.EditLichBay;
import Model.KhachHang;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class NguoiDung extends JFrame {

	public JTextArea textArea_chat;
	private JTextField textField_chatSend;
	public JLabel lblNewLabel_tenTK;
	private DSKhachHang dsKhachHang;
	private KhachHangDAO khachHangDAO;
	private LichBayDAO lichBayDAO;
	private DSLichBay dsLichBay;
	private JPanel contentPane;
	private JTable table_lichbay;
	private JTextField textField_lbtimngaybay;
	private JTable table_datve;
	private JComboBox comboBox_lbtimhacanh;
	private JComboBox comboBox_lbtimkhoihanh;
	private JComboBox comboBox_lbtimhangbay;
	private JTextField textField_dtenkhachhang;
	private JTextField textField_dngaysinh;
	private JTextField textField_dsdt;
	private JTextField textField_dcccd;
	private JTextField textField_email;
	private JRadioButton NewRadioButton_dnam;
	private JRadioButton NewRadioButton_dnu;
	private ButtonGroup btn_groupDgioitinh;
	private JComboBox comboBox_datvemachuyenbay;
	private JTextField textField_tccccd;
	private JTextField textField_tcsdt;
	private JLabel NewLabel_machuyenbay;
	private JLabel NewLabel_ngaybay;
	private JLabel NewLabel_giobay;
	private JLabel NewLabel_khoihanh;
	private JLabel NewLabel_hacanh;
	private JLabel NewLabel_hangbay;
	private JLabel NewLabel_soghe;
	private JLabel NewLabel_tenkhachhang;
	private JLabel NewLabel_gioitinh;
	private JLabel NewLabel_ngaysinh;
	private JLabel NewLabel_trangthai;
	private JLabel NewLabel_loaighe;
	private JComboBox comboBox_EconomyClass;
	private JComboBox comboBox_BusinessClass;
	private JComboBox comboBox_FirstClass;
	private CardLayout cardLayout;
	private JPanel jPanel_card;
	private JLabel NewLabel_anhchuyenkhoan;
	private String imagePath;
	private JLabel NewLabel_giave;
	private CardLayout layout;
	private JPanel panel_3;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				// Kiểm tra kết nối đến server
				if (isServerRunning("localhost", 9090)) {
					// Nếu server đang chạy, hiển thị giao diện
					NguoiDung frame = new NguoiDung();
					frame.setVisible(true);
				} else {
					// Nếu server không chạy, hiển thị thông báo
					JOptionPane.showMessageDialog(null, "Không thể kết nối đến server.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static boolean isServerRunning(String address, int port) {
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
	 *
	 * @throws IOException
	 */
	public NguoiDung() throws IOException {

		this.dsLichBay = new DSLichBay();
		this.lichBayDAO = new LichBayDAO();
		this.dsKhachHang = new DSKhachHang();
		this.khachHangDAO = new KhachHangDAO();
		setTitle("Người Dùng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1249, 669);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(192, 192, 192));

		QLCBController controller = new QLCBController(this);
		ControllerDatVe controllerDatVe = new ControllerDatVe(this);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(2, 233, 201, 394);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Lịch Bay");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(10, 10, 180, 52);
		panel.add(btnNewButton);
		java.net.URL urlIcon_Calender = NguoiDung.class.getResource("calendar");
		// Image img = Toolkit.getDefaultToolkit().createImage(urlIcon_Calender);
		Image img = ImageIO.read(getClass().getResource("calendar.png"));
		btnNewButton.setIcon(new ImageIcon(img));

		JButton btnNewButton_1 = new JButton("Đặt Vé");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(10, 75, 180, 50);
		panel.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 128, 64));
		java.net.URL urlIcon_Ticket = NguoiDung.class.getResource("ticket");
		Image img1 = ImageIO.read(getClass().getResource("ticket.png"));
		btnNewButton_1.setIcon(new ImageIcon(img1));

		JButton btnNewButton_1_1 = new JButton("Đăng Xuất");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));

		java.net.URL urlIcon_LogOff = NguoiDung.class.getResource("log_off");
		Image img2 = ImageIO.read(getClass().getResource("log_off.png"));
		btnNewButton_1_1.setIcon(new ImageIcon(img2));

		btnNewButton_1_1.setBounds(13, 261, 180, 54);
		panel.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chon = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất?", "Messenger",
						JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
					dispose();
					DangNhap dn = new DangNhap();
					dn.setVisible(true);
				}
			}
		});

		JButton btnNewButton_1_1_1 = new JButton("Thoát");
		btnNewButton_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_1.setBackground(new Color(0, 0, 128));
		btnNewButton_1_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1_1.setBounds(12, 330, 180, 50);
		java.net.URL urlIcon_Exit = NguoiDung.class.getResource("exit");
		Image img3 = ImageIO.read(getClass().getResource("exit.png"));
		btnNewButton_1_1_1.setIcon(new ImageIcon(img3));
		panel.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1_2 = new JButton("Tra Cứu");
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_2.setBackground(new Color(0, 128, 237));
		btnNewButton_1_1_2.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1_2.setBounds(10, 140, 180, 51);
		java.net.URL urlIcon_Search = NguoiDung.class.getResource("search1");
		Image img4 = ImageIO.read(getClass().getResource("search1.png"));
		btnNewButton_1_1_2.setIcon(new ImageIcon(img4));
		panel.add(btnNewButton_1_1_2);

		JButton btnNewButton_chat = new JButton("Chat");
		btnNewButton_chat.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_chat
				.setIcon(new ImageIcon("C:\\Users\\ASUS\\Documents\\Java\\CuoiKi2_2024\\src\\View\\admin.png"));
		btnNewButton_chat.setForeground(Color.WHITE);
		btnNewButton_chat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_chat.setBackground(new Color(0, 128, 237));
		btnNewButton_chat.setBounds(11, 201, 180, 50);
		panel.add(btnNewButton_chat);

		Panel panel_lichbay = new Panel();
		panel_lichbay.setBackground(new Color(255, 255, 255));
		panel_lichbay.setBounds(260, 246, 1013, 387);
		panel_lichbay.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 176, 1008, 262);
		panel_lichbay.add(scrollPane);
		scrollPane.setBackground(Color.black);

		table_lichbay = new JTable();
		table_lichbay.setBackground(new Color(255, 255, 255));
		table_lichbay.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã Chuyến Bay", "Ngày Bay",
				"Giờ Bay", "Điểm Khởi Hành", "Điểm Hạ Cánh", "Hãng Bay", "Sức Chứa" }));
		scrollPane.setViewportView(table_lichbay);

		JLabel lblNewLabel_1 = new JLabel("Ngày Bay");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(31, 26, 147, 33);
		lblNewLabel_1.setForeground(Color.white);
		panel_lichbay.add(lblNewLabel_1);
		panel_lichbay.setBackground(new Color(0, 128, 192));

		JLabel lblNewLabel_1_1 = new JLabel("Điểm Khởi Hành");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(31, 86, 147, 33);
		lblNewLabel_1_1.setForeground(Color.white);

		panel_lichbay.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Điểm Hạ Cánh");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(405, 26, 147, 33);
		lblNewLabel_1_2.setForeground(Color.white);

		panel_lichbay.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Hãng Bay");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(405, 86, 147, 33);
		lblNewLabel_1_3.setForeground(Color.white);
		panel_lichbay.add(lblNewLabel_1_3);

		textField_lbtimngaybay = new JTextField();
		textField_lbtimngaybay.setBackground(new Color(255, 255, 255));
		textField_lbtimngaybay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_lbtimngaybay.setColumns(10);
		textField_lbtimngaybay.setBounds(188, 26, 187, 33);
		panel_lichbay.add(textField_lbtimngaybay);

		JButton btnNewButton_2 = new JButton(" Tìm");
		btnNewButton_2.addActionListener(controller);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(789, 26, 140, 55);
		btnNewButton_2.setBackground(new Color(255, 128, 128));
		btnNewButton_2.setForeground(Color.black);
		java.net.URL urlIcon_Search1 = NguoiDung.class.getResource("search2");
		Image img_tim = ImageIO.read(getClass().getResource("search2.png"));
		btnNewButton_2.setIcon(new ImageIcon(img_tim));
		panel_lichbay.add(btnNewButton_2);

		JPanel jPanel_datghe = new JPanel();
		jPanel_datghe.setBackground(new Color(230, 230, 230));
		jPanel_datghe.setBounds(260, 246, 1013, 387);
		jPanel_datghe.setLayout(null);

		JPanel jPanel_datve = new JPanel();
		jPanel_datve.setBounds(260, 246, 1013, 387);
		jPanel_datve.setBackground(new Color(0, 128, 192));
		jPanel_datve.setLayout(null);

		JPanel jPanel_tracuu = new JPanel();
		jPanel_tracuu.setBackground(new Color(192, 192, 192));
		jPanel_tracuu.setBounds(260, 246, 1013, 387);
		jPanel_tracuu.setLayout(null);

		JPanel jPanel_chat = new JPanel();
		jPanel_chat.setBackground(new Color(192, 192, 192));
		jPanel_chat.setBounds(260, 246, 1013, 387);
		jPanel_chat.setLayout(null);

		cardLayout = new CardLayout();
		jPanel_card = new JPanel(cardLayout);
		jPanel_card.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPanel_card.add(panel_lichbay, "panel_lichbay");
		jPanel_card.add(jPanel_tracuu, "jPanel_tracuu");
		jPanel_card.add(jPanel_datghe, "jPanel_datghe");
		jPanel_card.add(jPanel_chat, "jPanel_chat");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 230));
		panel_1.setLayout(null);
		panel_1.setBounds(798, 173, 218, 188);
		jPanel_datghe.add(panel_1);

		JButton btnNewButton_3_1 = new JButton("Thanh Toán");
		btnNewButton_3_1.setBackground(new Color(192, 192, 192));
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3_1.setBounds(28, 11, 157, 46);
		panel_1.add(btnNewButton_3_1);

		JButton btnNewButton_3_3 = new JButton("Chọn Ảnh");
		btnNewButton_3_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseImage();
			}
		});
		btnNewButton_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3_3.setBounds(28, 67, 157, 46);
		panel_1.add(btnNewButton_3_3);

		JButton btnNewButton_3 = new JButton("Đặt");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setBounds(28, 123, 157, 46);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.addActionListener(controllerDatVe);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_6 = new JLabel("EconomyClass");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(26, 60, 135, 39);
		jPanel_datghe.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("BusinessClass");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6_1.setBounds(360, 60, 135, 39);
		jPanel_datghe.add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_2 = new JLabel("FirstClass");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6_2.setBounds(691, 60, 135, 39);
		jPanel_datghe.add(lblNewLabel_6_2);

		comboBox_EconomyClass = new JComboBox();
		comboBox_EconomyClass.setBackground(new Color(192, 192, 192));
		comboBox_EconomyClass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_EconomyClass.setBounds(171, 60, 150, 39);
		jPanel_datghe.add(comboBox_EconomyClass);

		comboBox_BusinessClass = new JComboBox();
		comboBox_BusinessClass.setBackground(new Color(192, 192, 192));
		comboBox_BusinessClass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_BusinessClass.setBounds(504, 60, 150, 39);
		jPanel_datghe.add(comboBox_BusinessClass);

		comboBox_FirstClass = new JComboBox();
		comboBox_FirstClass.setBackground(new Color(192, 192, 192));
		comboBox_FirstClass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_FirstClass.setBounds(798, 60, 150, 39);
		jPanel_datghe.add(comboBox_FirstClass);

		JButton btnNewButton_4 = new JButton("<");
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_4.setBounds(10, 10, 51, 39);
		jPanel_datghe.add(btnNewButton_4);

		JLabel lblNewLabel_4 = new JLabel("Giá Vé");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(20, 111, 78, 39);
		jPanel_datghe.add(lblNewLabel_4);

		NewLabel_giave = new JLabel("");
		NewLabel_giave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_giave.setBounds(108, 111, 202, 39);
		jPanel_datghe.add(NewLabel_giave);

		JLabel lblNewLabel_4_1 = new JLabel("Quét QR");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(405, 112, 178, 39);
		jPanel_datghe.add(lblNewLabel_4_1);

		NewLabel_anhchuyenkhoan = new JLabel("");
		NewLabel_anhchuyenkhoan.setHorizontalAlignment(SwingConstants.CENTER);
		NewLabel_anhchuyenkhoan.setBackground(new Color(255, 255, 255));
		NewLabel_anhchuyenkhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_anhchuyenkhoan.setBounds(610, 173, 180, 180);
		jPanel_datghe.add(NewLabel_anhchuyenkhoan);
		NewLabel_anhchuyenkhoan.setBorder(new LineBorder(Color.BLACK, 1));

		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 161, 590, 215);
		JPanel panel_tt = new JPanel();
		panel1.setBounds(10, 161, 590, 215);

		layout = new CardLayout();

		panel_3 = new JPanel(layout);
		panel_3.setBounds(10, 161, 590, 215);
		jPanel_datghe.add(panel_3);

		panel_3.add(panel1, "panel1");
		panel_3.add(panel_tt, "panel_tt");
		panel_tt.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Thông tin chuyển khoản");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(10, 11, 239, 32);
		panel_tt.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("Tên Tài Khoản: QUAN LY CHUYEN BAY");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7_1.setBounds(10, 97, 364, 32);
		panel_tt.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_2 = new JLabel("STK: 123456789");
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7_2.setBounds(10, 140, 239, 32);
		panel_tt.add(lblNewLabel_7_2);

		JLabel lblNewLabel_7_3 = new JLabel("Ngân Hàng: MBank");
		lblNewLabel_7_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7_3.setBounds(10, 54, 239, 32);
		panel_tt.add(lblNewLabel_7_3);

		JLabel lblQRCode = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("file:///C:/Users/ASUS/Documents/Java/CuoiKi2_2024/src/View/qrcode.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		lblQRCode.setBounds(395, 15, 180, 180);
		panel_tt.add(lblQRCode);

		JLabel lblNewLabel = new JLabel("Ảnh chuyển khoản");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(608, 115, 180, 39);
		jPanel_datghe.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 1010, 50);
		jPanel_tracuu.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("CCCD/CMND");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 11, 128, 28);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_2sdt = new JLabel("Số Điện Thoại");
		lblNewLabel_2sdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2sdt.setBounds(345, 11, 139, 28);
		panel_2.add(lblNewLabel_2sdt);

		textField_tccccd = new JTextField();
		textField_tccccd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_tccccd.setBounds(131, 11, 196, 28);
		panel_2.add(textField_tccccd);
		textField_tccccd.setColumns(10);

		textField_tcsdt = new JTextField();
		textField_tcsdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_tcsdt.setColumns(10);
		textField_tcsdt.setBounds(485, 11, 196, 28);
		panel_2.add(textField_tcsdt);

		JButton btnNewButtontracuu = new JButton("Tra Cứu");
		btnNewButtontracuu.addActionListener(controllerDatVe);
		btnNewButtontracuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtontracuu.setBounds(700, 11, 139, 28);
		btnNewButtontracuu.setBackground(new Color(255, 255, 255));
		btnNewButtontracuu.setForeground(Color.black);
		panel_2.add(btnNewButtontracuu);

		JButton btnNewButton_3_2 = new JButton("Hủy Chuyến");
		btnNewButton_3_2.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_3_2.addActionListener(controllerDatVe);
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3_2.setBounds(850, 11, 145, 28);
		btnNewButton_3_2.setBackground(new Color(255, 255, 255));
		btnNewButton_3_2.setForeground(Color.black);
		jPanel_tracuu.setBackground(new Color(0, 128, 192));

		panel_2.add(btnNewButton_3_2);

		JLabel lblNewLabel_3 = new JLabel("Điểm Hạ Cánh");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 282, 148, 38);
		lblNewLabel_3.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Điểm Khởi Hành");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(10, 233, 148, 38);
		lblNewLabel_3_1.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Giờ Bay");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_2.setBounds(10, 184, 148, 38);
		lblNewLabel_3_2.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Ngày Bay");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_3.setBounds(10, 135, 148, 38);
		lblNewLabel_3_3.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("Mã Chuyến Bay");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_4.setBounds(10, 86, 148, 38);
		lblNewLabel_3_4.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_4);

		NewLabel_machuyenbay = new JLabel("");
		NewLabel_machuyenbay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_machuyenbay.setBounds(168, 90, 148, 31);
		jPanel_tracuu.add(NewLabel_machuyenbay);

		NewLabel_ngaybay = new JLabel("");
		NewLabel_ngaybay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_ngaybay.setBounds(168, 139, 148, 31);
		jPanel_tracuu.add(NewLabel_ngaybay);

		NewLabel_giobay = new JLabel("");
		NewLabel_giobay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_giobay.setBounds(168, 188, 148, 31);
		jPanel_tracuu.add(NewLabel_giobay);

		NewLabel_khoihanh = new JLabel("");
		NewLabel_khoihanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_khoihanh.setBounds(168, 237, 218, 31);
		jPanel_tracuu.add(NewLabel_khoihanh);

		NewLabel_hacanh = new JLabel("");
		NewLabel_hacanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_hacanh.setBounds(168, 289, 218, 31);
		jPanel_tracuu.add(NewLabel_hacanh);

		JLabel lblNewLabel_3_5 = new JLabel("Hãng Bay");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_5.setBounds(10, 331, 148, 31);
		lblNewLabel_3_5.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_5);

		JLabel lblNewLabel_3_6 = new JLabel("Số Ghế");
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_6.setBounds(489, 139, 148, 31);
		lblNewLabel_3_6.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_6);

		JLabel lblNewLabel_3_7 = new JLabel("Tên Khách Hàng");
		lblNewLabel_3_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_7.setBounds(489, 188, 148, 31);
		lblNewLabel_3_7.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_7);

		JLabel lblNewLabel_3_8 = new JLabel("Giới Tính");
		lblNewLabel_3_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_8.setBounds(489, 237, 148, 31);
		lblNewLabel_3_8.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_8);

		JLabel lblNewLabel_3_9 = new JLabel("Ngày Sinh");
		lblNewLabel_3_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_9.setBounds(489, 286, 148, 31);
		lblNewLabel_3_9.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_9);

		JLabel lblNewLabel_3_10 = new JLabel("Trạng Thái");
		lblNewLabel_3_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_10.setBounds(489, 331, 148, 31);
		lblNewLabel_3_10.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_3_10);

		NewLabel_hangbay = new JLabel("");
		NewLabel_hangbay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_hangbay.setBounds(168, 331, 218, 31);
		jPanel_tracuu.add(NewLabel_hangbay);

		NewLabel_soghe = new JLabel("");
		NewLabel_soghe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_soghe.setBounds(647, 142, 148, 31);
		jPanel_tracuu.add(NewLabel_soghe);

		NewLabel_tenkhachhang = new JLabel("");
		NewLabel_tenkhachhang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_tenkhachhang.setBounds(647, 191, 250, 31);
		jPanel_tracuu.add(NewLabel_tenkhachhang);

		NewLabel_gioitinh = new JLabel("");
		NewLabel_gioitinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_gioitinh.setBounds(647, 237, 148, 31);
		jPanel_tracuu.add(NewLabel_gioitinh);

		NewLabel_ngaysinh = new JLabel("");
		NewLabel_ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_ngaysinh.setBounds(647, 289, 148, 31);
		jPanel_tracuu.add(NewLabel_ngaysinh);

		NewLabel_trangthai = new JLabel("");
		NewLabel_trangthai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_trangthai.setBounds(647, 331, 250, 31);
		jPanel_tracuu.add(NewLabel_trangthai);

		JLabel lblNewLabel_5 = new JLabel("Loại Ghế");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(489, 86, 148, 31);
		lblNewLabel_5.setForeground(Color.white);
		jPanel_tracuu.add(lblNewLabel_5);

		NewLabel_loaighe = new JLabel("");
		NewLabel_loaighe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_loaighe.setBounds(647, 90, 148, 31);
		jPanel_tracuu.add(NewLabel_loaighe);

		String[] sb = new String[] { "", "SB Nội Bài - HAN", "SB Tân Sơn Nhất - SGN", "SB Đà Nẵng - DAD",
				"SB Nha Trang - CXR", "SB Cát Bi - HPH", "SB Phù Cát - UIH", "SB Buôn Ma Thuột - BMV",
				"SB Lien Khuong - DLI", "SB Phan Rang - Tháp Chàm - PHAN RANG", "SB Tuy Hòa - TBB" };

		comboBox_lbtimhacanh = new JComboBox(sb);
		comboBox_lbtimhacanh.setBackground(new Color(255, 255, 255));
		comboBox_lbtimhacanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lbtimhacanh.setBounds(562, 28, 187, 33);
		panel_lichbay.add(comboBox_lbtimhacanh);

		comboBox_lbtimkhoihanh = new JComboBox(sb);
		comboBox_lbtimkhoihanh.setBackground(new Color(255, 255, 255));
		comboBox_lbtimkhoihanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lbtimkhoihanh.setBounds(188, 88, 187, 33);
		panel_lichbay.add(comboBox_lbtimkhoihanh);

		String[] hb = new String[] { "", "Vietnam Airlines", "VietJet Air", "Bamboo Airways", "Pacific Airlines",
				"Vasco", "Vietravel Airlines" };

		comboBox_lbtimhangbay = new JComboBox(hb);
		comboBox_lbtimhangbay.setBackground(new Color(255, 255, 255));
		comboBox_lbtimhangbay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lbtimhangbay.setBounds(562, 88, 187, 33);
		panel_lichbay.add(comboBox_lbtimhangbay);
		jPanel_card.add(jPanel_datve, "jPanel_datve");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 176, 1010, 262);
		jPanel_datve.add(scrollPane_1);

		table_datve = new JTable();
		table_datve.setBackground(new Color(255, 255, 255));
		table_datve.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Tên Khách Hàng", "Ngày Sinh", "Giới Tính",
						"CCCD/CMND", "Mã Chuyến Bay", "Số Điện Thoại", "Tình Trạng", "Loại Ghế", "Số Ghế", "URL" }));
		scrollPane_1.setViewportView(table_datve);
		String[] macb = this.lichBayDAO.laymachuyenbay();
		comboBox_datvemachuyenbay = new JComboBox<>();
		comboBox_datvemachuyenbay.setBackground(new Color(255, 255, 255));
		comboBox_datvemachuyenbay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_datvemachuyenbay.addItem("Mã Chuyến Bay");
		for (String string : macb) {
			comboBox_datvemachuyenbay.addItem(string);
		}
		comboBox_datvemachuyenbay.setBounds(536, 83, 196, 41);
		jPanel_datve.add(comboBox_datvemachuyenbay);

		JLabel lblNewLabel_2_3 = new JLabel("Giới Tính");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(448, 11, 142, 41);
		lblNewLabel_2_3.setForeground(Color.white);
		jPanel_datve.add(lblNewLabel_2_3);

		JLabel lblNewLabel_8 = new JLabel("Tên Tài Khoản:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(10, 10, 160, 39);
		jPanel_chat.add(lblNewLabel_8);

		lblNewLabel_tenTK = new JLabel("");
		lblNewLabel_tenTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_tenTK.setBounds(180, 10, 256, 39);
		jPanel_chat.add(lblNewLabel_tenTK);

		

		textArea_chat = new JTextArea();
		textArea_chat.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea_chat.setLineWrap(true);
		textArea_chat.setWrapStyleWord(true);
		textArea_chat.setEditable(false);
		
		JScrollPane scrPane_chat = new JScrollPane(textArea_chat);
		scrPane_chat.setBounds(10, 88, 665, 244);
		jPanel_chat.add(scrPane_chat);
		// Thiết lập thanh cuộn tự động xuất hiện khi cần thiết
		scrPane_chat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textField_chatSend = new JTextField();
		textField_chatSend.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_chatSend.setBounds(10, 372, 665, 39);
		jPanel_chat.add(textField_chatSend);
		textField_chatSend.setColumns(10);

		JButton btnNewButton_chatSend = new JButton("Gửi");
		btnNewButton_chatSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_chatSend.setBounds(685, 371, 125, 40);
		jPanel_chat.add(btnNewButton_chatSend);

		textField_dtenkhachhang = new JTextField("Tên Đặt Vé");
		textField_dtenkhachhang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_dtenkhachhang.setBounds(10, 11, 196, 41);
		jPanel_datve.add(textField_dtenkhachhang);
		textField_dtenkhachhang.setColumns(10);
		textField_dtenkhachhang.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_dtenkhachhang.getText().equals("Tên Đặt Vé")) {
					textField_dtenkhachhang.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_dtenkhachhang.getText().isEmpty()) {
					textField_dtenkhachhang.setText("Tên Đặt Vé");
				}
			}
		});

		textField_dngaysinh = new JTextField("Ngày Sinh");
		textField_dngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_dngaysinh.setColumns(10);
		textField_dngaysinh.setBounds(227, 11, 196, 41);
		jPanel_datve.add(textField_dngaysinh);
		textField_dngaysinh.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_dngaysinh.getText().equals("Ngày Sinh")) {
					textField_dngaysinh.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_dngaysinh.getText().isEmpty()) {
					textField_dngaysinh.setText("Ngày Sinh");
				}
			}
		});

		textField_dsdt = new JTextField("Số Điện Thoại");
		textField_dsdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_dsdt.setColumns(10);
		textField_dsdt.setBounds(227, 83, 196, 41);
		jPanel_datve.add(textField_dsdt);
		textField_dsdt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_dsdt.getText().equals("Số Điện Thoại")) {
					textField_dsdt.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_dsdt.getText().isEmpty()) {
					textField_dsdt.setText("Số Điện Thoại");
				}
			}
		});

		textField_dcccd = new JTextField("CCCD");
		textField_dcccd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_dcccd.setColumns(10);
		textField_dcccd.setBounds(10, 83, 196, 41);
		jPanel_datve.add(textField_dcccd);
		textField_dcccd.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_dcccd.getText().equals("CCCD")) {
					textField_dcccd.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_dcccd.getText().isEmpty()) {
					textField_dcccd.setText("CCCD");
				}
			}
		});

		NewRadioButton_dnam = new JRadioButton("Nam");
		NewRadioButton_dnam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewRadioButton_dnam.setBounds(596, 11, 67, 41);
		jPanel_datve.add(NewRadioButton_dnam);

		NewRadioButton_dnu = new JRadioButton("Nữ");
		NewRadioButton_dnu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewRadioButton_dnu.setBounds(665, 11, 67, 41);
		jPanel_datve.add(NewRadioButton_dnu);
		jPanel_card.setBounds(205, 177, 1030, 450);

		btn_groupDgioitinh = new ButtonGroup();
		btn_groupDgioitinh.add(NewRadioButton_dnam);
		btn_groupDgioitinh.add(NewRadioButton_dnu);

		String[] lg = new String[] { "", "Economy Class", "Business Class", "First Class" };

		JButton btnNewButton_5 = new JButton("Đặt Ghế");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setBounds(796, 11, 180, 51);
		btnNewButton_5.setBackground(new Color(255, 128, 128));
		btnNewButton_5.setForeground(Color.white);
		jPanel_datve.add(btnNewButton_5);

		textField_email = new JTextField("Email");
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_email.setColumns(10);
		textField_email.setBounds(780, 81, 196, 41);
		jPanel_datve.add(textField_email);
		textField_email.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_email.getText().equals("Email")) {
					textField_email.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_email.getText().isEmpty()) {
					textField_email.setText("Email");
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "panel_lichbay");
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_datve");
			}
		});
		btnNewButton_1_1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_tracuu");
			}
		});

		btnNewButton_chat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_chat");
				try {
					socket = new Socket("localhost", 9090);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(socket.getOutputStream(), true);

					// Gửi tên của client tới server khi kết nối
					writer.println(lblNewLabel_tenTK.getText());

					// Tạo một luồng mới để nhận tin nhắn từ server
					Thread receiveThread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								String message;
								while ((message = reader.readLine()) != null) {
									textArea_chat.append(message + "\n");
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					receiveThread.start();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_chatSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = textField_chatSend.getText();
				writer.println(message);
				textField_chatSend.setText("");
			}
		});

		btnNewButton_1_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát khỏi chương trình?", "Messenger",
						JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_3, "panel_tt");
				if (comboBox_BusinessClass.getSelectedIndex() == 0 && comboBox_EconomyClass.getSelectedIndex() == 0
						&& comboBox_FirstClass.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Chọn loại ghế cần đặt.");
				} else if (comboBox_BusinessClass.getSelectedIndex() == 0
						&& comboBox_EconomyClass.getSelectedIndex() == 0) {
					NewLabel_giave.setText("120.000.000 VND");
				} else if (comboBox_BusinessClass.getSelectedIndex() == 0
						&& comboBox_FirstClass.getSelectedIndex() == 0) {
					NewLabel_giave.setText("1.500.000 VND");
				} else if (comboBox_EconomyClass.getSelectedIndex() == 0) {
					NewLabel_giave.setText("800.000 VND");
				} else {
					JOptionPane.showMessageDialog(null, "Chỉ được chọn một loại ghế");
				}
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox_datvemachuyenbay.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Chọn mã máy bay");
				} else {
					cardLayout.show(jPanel_card, "jPanel_datghe");
					String macb = comboBox_datvemachuyenbay.getSelectedItem() + "";
					String[] EconomyClass = lichBayDAO.layloaiEconomyClass(macb);
					for (String lb : EconomyClass) {
						if (lb != null)
							comboBox_EconomyClass.addItem(lb);
					}

					String[] FirstClass = lichBayDAO.layloaiFirstClass(macb);
					for (String lb : FirstClass) {
						if (lb != null)
							comboBox_FirstClass.addItem(lb);
					}

					String[] BusinessClass = lichBayDAO.layloaiBusinessClass(macb);
					for (String lb : BusinessClass) {
						if (lb != null)
							comboBox_BusinessClass.addItem(lb);
					}
					lammoi();
				}
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_datve");
			}
		});
		contentPane.add(jPanel_card);

		JPanel panel_4 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("D:\\Workspace\\Intell\\QLCB\\src\\View\\nen1.jpg");
				Image icon = imageIcon.getImage();
				g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(2, 2, 1233, 173);
		contentPane.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(2, 177, 201, 54);
		contentPane.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4_2 = new JLabel("Chức năng");
		panel_5.add(lblNewLabel_4_2);
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.tailichlenbang();
		this.dsKhachHang.capnhatdatabase();

		setVisible(true);
	}

	private void refreshComboBox(JComboBox<String> comboBox, String[] items) {
		comboBox.removeAllItems(); // Xóa tất cả các mục hiện tại
		for (String item : items) {
			if (item != null) {
				comboBox.addItem(item); // Thêm lại các mục mới
			}
		}
	}

	public void lammoi() {

		try {
			String macb = comboBox_datvemachuyenbay.getSelectedItem() + "";

			// Lấy lại dữ liệu từ cơ sở dữ liệu
			String[] EconomyClass = lichBayDAO.layloaiEconomyClass(macb);
			String[] FirstClass = lichBayDAO.layloaiFirstClass(macb);
			String[] BusinessClass = lichBayDAO.layloaiBusinessClass(macb);

			// Làm mới ComboBox
			refreshComboBox(comboBox_EconomyClass, EconomyClass);
			refreshComboBox(comboBox_FirstClass, FirstClass);
			refreshComboBox(comboBox_BusinessClass, BusinessClass);

		} catch (Exception ex) {
			ex.printStackTrace();
			// Xử lý ngoại lệ nếu có
		}

	}

	public String fomatNgay(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date);
	}

	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);
	}

	public static String formatGio(String h) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");

		try {
			Date date = inputFormat.parse(h);
			return format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public void themkhachhangvaobangchuyenbay(EditLichBay lb) {
		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		model_table.addRow(
				new Object[] { lb.getMaChuyenBay(), fomatNgay(new Date(lb.getNgayBay())), formatGio(lb.getGioBay()),
						lb.getDiemkhoihanh(), lb.getDiemHaCanh(), lb.getHangBay(), lb.getSucChua() + "" });
	}

	public void tailichlenbang() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
			int soluongdong = model_table.getRowCount();
			if (soluongdong == 0) {
				break;
			} else {
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ArrayList<EditLichBay> result = lichBayDAO.selectAll();
		for (EditLichBay editLichBay : result) {
			this.themkhachhangvaobangchuyenbay(editLichBay);
		}
	}

	public void thuchientim() {
		this.tailichlenbang();

		String ngaybay = textField_lbtimngaybay.getText();
		int diemkhoihanh = comboBox_lbtimkhoihanh.getSelectedIndex() - 1;
		int diemhacanh = comboBox_lbtimhacanh.getSelectedIndex() - 1;
		int hangbay = comboBox_lbtimhangbay.getSelectedIndex() - 1;

		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		int soluongdong = model_table.getRowCount();

		Set<String> machuyenbaycanxoa = new TreeSet<String>();

		if (ngaybay.length() > 0) {
			for (int i = 0; i < soluongdong; i++) {
				String nb = model_table.getValueAt(i, 1) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!nb.equals(ngaybay)) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (diemkhoihanh >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String kh = model_table.getValueAt(i, 3) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!kh.equals(comboBox_lbtimkhoihanh.getSelectedItem())) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (diemhacanh >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String hc = model_table.getValueAt(i, 4) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!hc.equals(comboBox_lbtimhacanh.getSelectedItem())) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (hangbay >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String hb = model_table.getValueAt(i, 5) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!hb.equals(comboBox_lbtimhangbay.getSelectedItem())) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		for (String macbcanxoa : machuyenbaycanxoa) {
			for (int i = 0; i < soluongdong; i++) {
				String macbtrongbang = model_table.getValueAt(i, 0) + "";
				if (macbtrongbang.equals(macbcanxoa.toString())) {
					try {
						model_table.removeRow(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}

		textField_lbtimngaybay.setText("");
		comboBox_lbtimhacanh.setSelectedIndex(0);
		comboBox_lbtimhangbay.setSelectedIndex(0);
		comboBox_lbtimkhoihanh.setSelectedIndex(0);
	}

	private static boolean checkDateFormat(String ngBay) {
		try {
			String[] parts = ngBay.split("/");
			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);

			if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2023) {
				return false;
			} else {
				if (month == 2) {
					// Kiểm tra năm nhuận và không nhuận
					if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
						if (day > 29) {
							return false;
						}
					} else {
						if (day > 28) {
							return false;
						}
					}
				} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean kiemtracccd(String ccCd) {
		if (ccCd.length() == 12) {
			try {
				Long.parseLong(ccCd);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean kiemtrasdt(String sdt) {
		if (sdt.length() == 10) {
			try {
				Long.parseLong(sdt);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public void themkhachhangvaobangkhachhang(KhachHang kh) {
		DefaultTableModel model_table = (DefaultTableModel) table_datve.getModel();
		model_table.addRow(new Object[] { kh.getTenKhachHang(), kh.getNgaySinh(), kh.isGioiTinh() ? "Nam" : "Nữ",
				kh.getCccd(), kh.getMaChuyenBay(), kh.getSoDienThoai(), kh.getTinhTrang(), kh.getLoaiGhe(),
				kh.getSoGhe(), kh.getUrl() });
	}

	public void thuchienthemkhachhang(KhachHang kh) {
		if (!this.dsKhachHang.kiemtraCCCD(kh)) {
			if (!this.dsKhachHang.kiemtraSDT(kh)) {
				if (!textField_email.getText().isEmpty()) {
					if (textField_email.getText().matches("^.+@gmail.com")) {
						Emailto.sendEmail(textField_email.getText().trim());
						String randomOTP = Emailto.laymaTeenCode(); // Lấy mã OTP đã gửi từ email

						String xacthuc = JOptionPane.showInputDialog(this,
								"Nhập Mã OTP qua email: " + textField_email.getText(), "Messenger",
								JOptionPane.OK_CANCEL_OPTION);
						if (xacthuc != null) {

							if (xacthuc.equals(randomOTP)) {

								this.dsKhachHang.insert(kh);
								this.themkhachhangvaobangkhachhang(kh);
								this.khachHangDAO.insert(kh);
								EditLichBay lb = new EditLichBay(comboBox_datvemachuyenbay.getSelectedItem() + "", null,
										null, null, null, null, 0);
								if (comboBox_EconomyClass.getSelectedItem().equals(kh.getSoGhe() + "")) {
									this.lichBayDAO.xoaghe(lb,
											Integer.valueOf(comboBox_EconomyClass.getSelectedItem() + ""), null, null);
									themtienvaodatabase(comboBox_datvemachuyenbay.getSelectedItem() + "");
								} else if (comboBox_BusinessClass.getSelectedItem().equals(kh.getSoGhe() + "")) {
									this.lichBayDAO.xoaghe(lb, null,
											Integer.valueOf(comboBox_BusinessClass.getSelectedItem() + ""), null);
									themtienvaodatabase(comboBox_datvemachuyenbay.getSelectedItem() + "");
								} else if (comboBox_FirstClass.getSelectedItem().equals(kh.getSoGhe())) {
									this.lichBayDAO.xoaghe(lb, null, null,
											Integer.valueOf(comboBox_FirstClass.getSelectedItem() + ""));
									themtienvaodatabase(comboBox_datvemachuyenbay.getSelectedItem() + "");
								}

								JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
								cardLayout.show(jPanel_card, "jPanel_datve");
							} else {
								JOptionPane.showMessageDialog(this, "Mã OTP không đúng", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						this.textField_dtenkhachhang.setText("Tên Đặt Vé");
						this.textField_dngaysinh.setText("Ngày Sinh");
						this.btn_groupDgioitinh.clearSelection();
						this.textField_dcccd.setText("CCCD");
						this.comboBox_datvemachuyenbay.setSelectedIndex(0);
						this.textField_dsdt.setText("Số Điện Thoại");
						this.comboBox_BusinessClass.setSelectedIndex(0);
						this.comboBox_EconomyClass.setSelectedIndex(0);
						this.comboBox_FirstClass.setSelectedIndex(0);
						this.textField_email.setText("Email");
						this.NewLabel_giave.setText("");
						layout.show(panel_3, "panel1");
						this.NewLabel_anhchuyenkhoan.setIcon(null);
					} else {
						JOptionPane.showMessageDialog(this, "Email không đúng định dạng! (@gmail.com)", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Email trống! vui lòng nhập email", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "CCCD/CMND đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void themtienvaodatabase(String mcb) {
		removeAllItemListeners(comboBox_EconomyClass);
		removeAllItemListeners(comboBox_BusinessClass);
		removeAllItemListeners(comboBox_FirstClass);

		String ngayBay = this.lichBayDAO.layNgayBay(mcb);

		String[] ngayBay1 = ngayBay.split("/");

		int ngay = Integer.parseInt(ngayBay1[0]);
		String thang = ngayBay1[1];
		int nam = Integer.parseInt(ngayBay1[2]);

		comboBox_EconomyClass.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeAllItemListeners(comboBox_EconomyClass);
				}
			}
		});

		comboBox_BusinessClass.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeAllItemListeners(comboBox_BusinessClass);
				}
			}
		});

		comboBox_FirstClass.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					removeAllItemListeners(comboBox_FirstClass);
				}
			}
		});
	}

	private void removeAllItemListeners(JComboBox<?> comboBox) {
		for (ItemListener listener : comboBox.getItemListeners()) {
			comboBox.removeItemListener(listener);
		}
	}

	public void thuchienthem() {
		if (textField_dtenkhachhang.getText().isEmpty() || textField_dngaysinh.getText().isEmpty()
				|| (!NewRadioButton_dnam.isSelected() && !NewRadioButton_dnu.isSelected())
				|| textField_dcccd.getText().isEmpty() || textField_dsdt.getText().isEmpty()
				|| comboBox_datvemachuyenbay.getSelectedIndex() == 0 || (comboBox_BusinessClass.getSelectedIndex() == 0
						&& comboBox_EconomyClass.getSelectedIndex() == 0 && comboBox_FirstClass.getSelectedIndex() == 0)
				|| NewLabel_giave.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String hoten = textField_dtenkhachhang.getText();
		String ngaysinh = textField_dngaysinh.getText();
		boolean gioitinh = NewRadioButton_dnam.isSelected();
		String cccd = textField_dcccd.getText();
		String sdt = textField_dsdt.getText();
		String macb = comboBox_datvemachuyenbay.getSelectedItem() + "";
		String url = imagePath;
		String tinhtrang = "Chờ xác nhận";

		String loi = "Chú ý các lỗi sau:\n";
		if (!checkDateFormat(ngaysinh)) {
			loi += "Nhập đúng định dạng: dd/MM/yyyy.\n";
		}
		if (!kiemtracccd(cccd)) {
			loi += "CCCD chứa 12 số.\n";
		}
		if (!kiemtrasdt(sdt)) {
			loi += "Số điện thoại chứa 10 số.\n";
		}
		if (NewLabel_anhchuyenkhoan.getIcon() == null) {
			loi += "Thêm ảnh chuyển khoản\n";
		}
		if (loi != "Chú ý các lỗi sau:\n") {
			JOptionPane.showMessageDialog(this, loi, "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int count = 0;
		if (comboBox_EconomyClass.getSelectedIndex() != 0) {
			count++;
		}
		if (comboBox_BusinessClass.getSelectedIndex() != 0) {
			count++;
		}
		if (comboBox_FirstClass.getSelectedIndex() != 0) {
			count++;
		}
		if (count != 1) {
			JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 loại ghế", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String S_soghe = comboBox_EconomyClass.getSelectedItem() + "" + comboBox_BusinessClass.getSelectedItem() + ""
				+ comboBox_FirstClass.getSelectedItem() + "";
		if (comboBox_EconomyClass.getSelectedIndex() != 0) {
			S_soghe = comboBox_EconomyClass.getSelectedItem() + "";
		}
		if (comboBox_BusinessClass.getSelectedIndex() != 0) {
			S_soghe = comboBox_BusinessClass.getSelectedItem() + "";
		}
		if (comboBox_FirstClass.getSelectedIndex() != 0) {
			S_soghe = comboBox_FirstClass.getSelectedItem() + "";
		}
		int soghe = Integer.valueOf(S_soghe);
		String loaighe = "";
		if (soghe > 0 && soghe < 21) {
			loaighe = "FirstClass";
		} else if (soghe >= 21 && soghe < 51) {
			loaighe = "BusinessClass";
		} else {
			loaighe = "EconomyClass";
		}
		KhachHang kh = new KhachHang(hoten, ngaysinh, gioitinh, cccd, sdt, macb, tinhtrang, loaighe, soghe, url);
		this.thuchienthemkhachhang(kh);
	}

	public void taikhachhanglenbang() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table_datve.getModel();
			int soluongdong = model_table.getRowCount();
			if (soluongdong == 0) {
				break;
			} else {
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for (KhachHang kh : this.khachHangDAO.selectAll()) {
			this.themkhachhangvaobangkhachhang(kh);
		}
	}

	public void hienthithongtinkhachhang(KhachHang kh) {
		NewLabel_tenkhachhang.setText(kh.getTenKhachHang());
		NewLabel_ngaysinh.setText(kh.getNgaySinh());
		NewLabel_gioitinh.setText(kh.isGioiTinh() ? "Nam" : "Nữ");
		NewLabel_machuyenbay.setText(kh.getMaChuyenBay());
		NewLabel_trangthai.setText(kh.getTinhTrang());
		NewLabel_loaighe.setText(kh.getLoaiGhe());
		NewLabel_soghe.setText(kh.getSoGhe() + "");
	}

	public void hienthithongtinlichbay(EditLichBay lb) {
		NewLabel_ngaybay.setText(lb.getNgayBay());
		NewLabel_giobay.setText(lb.getGioBay());
		NewLabel_khoihanh.setText(lb.getDiemkhoihanh());
		NewLabel_hacanh.setText(lb.getDiemHaCanh());
		NewLabel_hangbay.setText(lb.getHangBay());
	}

	public void thuchientracuukhachhang() {
		this.dsKhachHang.capnhatdatabase();
		this.dsLichBay.laydulieutudatabase();
		String cccd = textField_tccccd.getText();
		String sdt = textField_tcsdt.getText();
		boolean timthay = false;
		for (KhachHang kh : this.dsKhachHang.getDsKhachHang()) {
			if (kh.getCccd().equals(cccd) || kh.getSoDienThoai().equals(sdt)) {
				String macb = kh.getMaChuyenBay();
				for (EditLichBay lb : this.dsLichBay.getDsLichBay()) {
					if (lb.getMaChuyenBay().equals(macb)) {
						this.hienthithongtinkhachhang(kh);
						this.hienthithongtinlichbay(lb);
						timthay = true;
						break;
					}
				}
			}
		}
		if (!timthay) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng trong danh sách.");
			NewLabel_tenkhachhang.setText("");
			NewLabel_ngaysinh.setText("");
			NewLabel_gioitinh.setText("");
			NewLabel_machuyenbay.setText("");
			NewLabel_trangthai.setText("");
			NewLabel_ngaybay.setText("");
			NewLabel_giobay.setText("");
			NewLabel_khoihanh.setText("");
			NewLabel_hacanh.setText("");
			NewLabel_hangbay.setText("");
			NewLabel_soghe.setText("");
			NewLabel_loaighe.setText("");
		}
	}

	public void thuchienhuychuyen() {
		if (NewLabel_trangthai.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa có thông tin khách hàng", "", JOptionPane.ERROR_MESSAGE);
		} else if (NewLabel_trangthai.getText().equals("Đã xác nhận")) {
			JOptionPane.showMessageDialog(this, "Quý khách không thể hủy chuyến bay này", "",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int luachon = JOptionPane.showConfirmDialog(this, "Xác nhận hủy chuyến", "", JOptionPane.YES_NO_OPTION);
			if (luachon == JOptionPane.YES_OPTION) {
				String huy = "Hủy chuyến";
				String cccd = textField_tccccd.getText();
				this.khachHangDAO.updatetinhtrang(cccd, huy);
				this.dsKhachHang.capnhatdatabase();
				this.thuchientracuukhachhang();
			}
		}
	}

	private void chooseImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn ảnh");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = fileChooser.showOpenDialog(NguoiDung.this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// Kiểm tra định dạng tệp ảnh
			if (isImageFile(selectedFile)) {
				displayImage(selectedFile.getAbsolutePath());
			} else {
				JOptionPane.showMessageDialog(NguoiDung.this, "Chọn một tệp ảnh hợp lệ.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean isImageFile(File file) {
		String fileName = file.getName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}

	private void displayImage(String filePath) {
		try {
			ImageIcon originalImageIcon = new ImageIcon(filePath);

			// Lấy kích thước ảnh
			Image originalImage = originalImageIcon.getImage();
			int width = originalImage.getWidth(null);
			int height = originalImage.getHeight(null);

			// Kích thước mới để hiển thị (ví dụ: 150x150)
			int newWidth = 150;
			int newHeight = 150;

			// Thay đổi kích thước ảnh
			Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			// Tạo ImageIcon từ ảnh đã thay đổi kích thước
			ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

			// Hiển thị ảnh trong NewLabel_anh
			NewLabel_anhchuyenkhoan.setIcon(resizedImageIcon);
			imagePath = filePath;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(NguoiDung.this, "Không thể hiển thị ảnh từ đường dẫn: " + filePath, "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
