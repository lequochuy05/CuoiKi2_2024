
package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;
import Controller.QLCBController;
import DAO.KhachHangDAO;
import DAO.LichBayDAO;
import DAO.XuHuongDAO;
import Model.DSKhachHang;
import Model.DSLichBay;
import Model.EditLichBay;
import Model.KhachHang;
import Model.KhoLuuTru;
import Model.LuuTru;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.border.LineBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class ADMIN extends JFrame {
	private JTextField textField_sendChat;
	private KhoLuuTru khoLuuTru;
	private DSKhachHang dsKhachHang;
	private KhachHangDAO khachHangDAO;
	private LichBayDAO lichBayDAO;
	private XuHuongDAO xuHuongDAO;
	private DSLichBay dsLichBay;
	private JPanel contentPane;
	private JTable table_lichbay;
	private JTextField textField_lichmachuyenbay;
	private JTextField textField_lichngaybay;
	private JTextField textField_lichgiobay;
	private JTextField textField_lichsucchua;
	private JComboBox comboBox_lichkhoihanh;
	private JComboBox comboBox_lichhacanh;
	private JComboBox comboBox_lichhangbay;
	private int selectedRow;
	private JTable table_khachang;
	private JLabel NewLabel_duyetcccd;
	private JComboBox comboBox_tinhtrang;
	private JComboBox comboBox_duyetmacb;
	private JLabel NewLabel_anhchuyenkhoan;
	private JTable table_kholuutru;
	private JComboBox comboBox;
	private JMenuBar jMenuBar;
	private JMenu jMenu_ThongKe;
	private JMenuItem jMenuItem_LoaiGhe, jMenuItem_DoanhThu, jMenuItem_HangBay;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private JTextArea textArea_message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {

				ADMIN frame = new ADMIN();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ADMIN() throws IOException {
		this.dsLichBay = new DSLichBay();
		this.lichBayDAO = new LichBayDAO();
		this.dsKhachHang = new DSKhachHang();
		this.khachHangDAO = new KhachHangDAO();
		this.khoLuuTru = new KhoLuuTru();
		this.xuHuongDAO = new XuHuongDAO();
		setTitle("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1212, 716);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 128, 192));

		QLCBController controller = new QLCBController(this);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(2, 233, 201, 408);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_lichbay = new JButton("Lịch Bay");
		btnNewButton_lichbay.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_lichbay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_lichbay.setBounds(10, 10, 180, 60);
		panel.add(btnNewButton_lichbay);
		btnNewButton_lichbay.setForeground(new Color(255, 255, 255));
		btnNewButton_lichbay.setBackground(Color.ORANGE);
		java.net.URL urlIcon_Calender_lichbay = ADMIN.class.getResource("calendar");
		Image img_lichbay = ImageIO.read(getClass().getResource("calendar.png"));
		btnNewButton_lichbay.setIcon(new ImageIcon(img_lichbay));

		JButton btnNewButton_1 = new JButton("Đăng Xuất");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(10, 205, 180, 60);
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setForeground(Color.white);
		java.net.URL urlIcon_dangxuat = ADMIN.class.getResource("log_off");
		Image img_dangxuat = ImageIO.read(getClass().getResource("log_off.png"));
		btnNewButton_1.setIcon(new ImageIcon(img_dangxuat));
		panel.add(btnNewButton_1);

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chon = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất?", "Messenger",
						JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
					dispose();
					DangNhap dangNhap = new DangNhap();
					dangNhap.setVisible(true);
				}
			}
		});

		JButton btnNewButton_duyet = new JButton("Duyệt");
		btnNewButton_duyet.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_duyet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_duyet.setBounds(10, 75, 180, 60);
		btnNewButton_duyet.setBackground(new Color(255, 128, 64));
		btnNewButton_duyet.setForeground(new Color(255, 255, 255));
		java.net.URL urlIcon_duyet = ADMIN.class.getResource("tick2");
		Image img_duyet = ImageIO.read(getClass().getResource("tick2.png"));
		btnNewButton_duyet.setIcon(new ImageIcon(img_duyet));
		panel.add(btnNewButton_duyet);

		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 270, 180, 60);
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.setForeground(new Color(255, 255, 255));
		java.net.URL urlIcon_thoat = ADMIN.class.getResource("exit");
		Image img_thoat = ImageIO.read(getClass().getResource("exit.png"));
		btnNewButton.setIcon(new ImageIcon(img_thoat));
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát khỏi chương trình?", "Messenger",
						JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		JButton btnNewButton_chat = new JButton("Chat");
		btnNewButton_chat
				.setIcon(new ImageIcon("C:\\Users\\ASUS\\Documents\\Java\\CuoiKi2_2024\\src\\View\\admin.png"));
		btnNewButton_chat.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_chat.setForeground(Color.WHITE);
		btnNewButton_chat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_chat.setBackground(new Color(128, 64, 64));
		btnNewButton_chat.setBounds(10, 340, 180, 60);
		panel.add(btnNewButton_chat);

		JButton btnNewButton_5 = new JButton("K.Lưu Trữ");
		btnNewButton_5.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_5.setBackground(new Color(128, 64, 64));
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setBounds(10, 140, 180, 60);
		panel.add(btnNewButton_5);
		java.net.URL urlIcon_kholuutru = ADMIN.class.getResource("kholuutru");
		Image img_kholuutru = ImageIO.read(getClass().getResource("kholuutru.png"));
		btnNewButton_5.setIcon(new ImageIcon(img_kholuutru));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_lichbay = new JPanel();
		panel_lichbay.setBackground(new Color(128, 255, 128));
		panel_lichbay.setBounds(282, 246, 991, 386);
		panel_lichbay.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 971, 250);
		panel_lichbay.add(scrollPane);
		panel_lichbay.setBackground(Color.white);
		table_lichbay = new JTable();

		table_lichbay.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã Chuyến Bay", "Ngày Bay",
				"Giờ Bay", "Điểm Khởi Hành", "Điểm Hạ Cánh", "Hãng Bay", "Sức Chứa" }));
		scrollPane.setViewportView(table_lichbay);

		JPanel jPanel_duyet = new JPanel();
		jPanel_duyet.setBounds(282, 246, 991, 386);
		jPanel_duyet.setLayout(null);

		JPanel jPanel_kholuutru = new JPanel();
		jPanel_kholuutru.setBounds(282, 246, 991, 386);
		jPanel_kholuutru.setLayout(null);

		JPanel panel_chat = new JPanel();
		panel_chat.setBackground(new Color(255, 255, 255));
		panel_chat.setBounds(282, 246, 991, 386);
		panel_chat.setLayout(null);

		CardLayout cardLayout = new CardLayout();
		JPanel jPanel_card = new JPanel(cardLayout);
		jPanel_card.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPanel_card.add(panel_lichbay, "panel_lichbay");
		jPanel_card.add(jPanel_kholuutru, "jPanel_kholuutru");
		jPanel_card.add(panel_chat, "panel_chat");

		JScrollPane scrollPane_kholuutru = new JScrollPane();
		scrollPane_kholuutru.setBounds(10, 118, 971, 258);
		jPanel_kholuutru.add(scrollPane_kholuutru);

		table_kholuutru = new JTable();
		scrollPane_kholuutru.setViewportView(table_kholuutru);
		table_kholuutru.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "CCCD/CMND",
						"M\u00E3 chuy\u1EBFn bay", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "T\u00ECnh tr\u1EA1ng",
						"Lo\u1EA1i gh\u1EBF", "S\u1ED1 gh\u1EBF", "URL" }));
		table_kholuutru.getColumnModel().getColumn(0).setPreferredWidth(90);
		table_kholuutru.getColumnModel().getColumn(4).setPreferredWidth(80);
		table_kholuutru.getColumnModel().getColumn(7).setPreferredWidth(90);
		table_kholuutru.getColumnModel().getColumn(9).setPreferredWidth(90);

		JLabel lblMcb_kholuutru = new JLabel("Mã Chuyến Bay");
		lblMcb_kholuutru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMcb_kholuutru.setBounds(15, 35, 143, 34);
		jPanel_kholuutru.add(lblMcb_kholuutru);

		JButton btnTim_kholuutru = new JButton("Tìm");
		btnTim_kholuutru.addActionListener(controller);
		btnTim_kholuutru.setBackground(new Color(255, 128, 128));
		btnTim_kholuutru.setForeground(new Color(255, 255, 255));
		btnTim_kholuutru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim_kholuutru.setBounds(316, 35, 100, 34);
		jPanel_kholuutru.add(btnTim_kholuutru);
		java.net.URL urlIcon_timkiem = ADMIN.class.getResource("timkiem");
		Image img_timkiem = ImageIO.read(getClass().getResource("timkiem.png"));
		btnTim_kholuutru.setIcon(new ImageIcon(img_timkiem));

		String[] dsmacb = this.lichBayDAO.luutrumachuyenbay();
		comboBox = new JComboBox();
		comboBox.addItem("");
		for (String string : dsmacb) {
			comboBox.addItem(string);
		}
		comboBox.setBounds(168, 39, 138, 29);
		jPanel_kholuutru.add(comboBox);

		textField_lichmachuyenbay = new JTextField("Mã chuyến bay");
		textField_lichmachuyenbay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_lichmachuyenbay.setBounds(27, 10, 181, 34);
		panel_lichbay.add(textField_lichmachuyenbay);
		textField_lichmachuyenbay.setColumns(10);
		textField_lichmachuyenbay.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_lichmachuyenbay.getText().equals("Mã chuyến bay")) {
					textField_lichmachuyenbay.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_lichmachuyenbay.getText().isEmpty()) {
					textField_lichmachuyenbay.setText("Mã chuyến bay");
				}
			}
		});

		textField_lichngaybay = new JTextField("Ngày Bay");
		textField_lichngaybay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_lichngaybay.setColumns(10);
		textField_lichngaybay.setBounds(263, 10, 181, 34);
		panel_lichbay.add(textField_lichngaybay);
		textField_lichngaybay.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_lichngaybay.getText().equals("Ngày Bay")) {
					textField_lichngaybay.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_lichngaybay.getText().isEmpty()) {
					textField_lichngaybay.setText("Ngày Bay");
				}
			}
		});

		textField_lichgiobay = new JTextField("Giờ Bay");
		textField_lichgiobay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_lichgiobay.setColumns(10);
		textField_lichgiobay.setBounds(512, 10, 181, 34);
		panel_lichbay.add(textField_lichgiobay);
		textField_lichgiobay.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_lichgiobay.getText().equals("Giờ Bay")) {
					textField_lichgiobay.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_lichgiobay.getText().isEmpty()) {
					textField_lichgiobay.setText("Giờ Bay");
				}
			}
		});

		textField_lichsucchua = new JTextField("Sức chứa");
		textField_lichsucchua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_lichsucchua.setColumns(10);
		textField_lichsucchua.setBounds(628, 67, 181, 34);
		panel_lichbay.add(textField_lichsucchua);
		textField_lichsucchua.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_lichsucchua.getText().equals("Sức chứa")) {
					textField_lichsucchua.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_lichsucchua.getText().isEmpty()) {
					textField_lichsucchua.setText("Sức chứa");
				}
			}
		});

		String[] sbkh = new String[] { "Điểm khởi hành", "SB Nội Bài - HAN", "SB Tân Sơn Nhất - SGN",
				"SB Đà Nẵng - DAD", "SB Nha Trang - CXR", "SB Cát Bi - HPH", "SB Phù Cát - UIH",
				"SB Buôn Ma Thuột - BMV", "SB Lien Khuong - DLI", "SB Phan Rang - Tháp Chàm - PHAN RANG",
				"SB Tuy Hòa - TBB" };
		comboBox_lichkhoihanh = new JComboBox(sbkh);
		comboBox_lichkhoihanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lichkhoihanh.setBounds(757, 11, 181, 34);
		panel_lichbay.add(comboBox_lichkhoihanh);

		String[] sbhc = new String[] { "Điểm hạ cánh", "SB Nội Bài - HAN", "SB Tân Sơn Nhất - SGN", "SB Đà Nẵng - DAD",
				"SB Nha Trang - CXR", "SB Cát Bi - HPH", "SB Phù Cát - UIH", "SB Buôn Ma Thuột - BMV",
				"SB Lien Khuong - DLI", "SB Phan Rang - Tháp Chàm - PHAN RANG", "SB Tuy Hòa - TBB" };
		comboBox_lichhacanh = new JComboBox(sbhc);
		comboBox_lichhacanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lichhacanh.setBounds(140, 68, 181, 34);
		panel_lichbay.add(comboBox_lichhacanh);

		String[] hb = new String[] { "Hãng Bay", "Vietnam Airlines", "VietJet Air", "Bamboo Airways",
				"Pacific Airlines", "Vasco", "Vietravel Airlines" };
		comboBox_lichhangbay = new JComboBox(hb);
		comboBox_lichhangbay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_lichhangbay.setBounds(391, 68, 181, 34);
		panel_lichbay.add(comboBox_lichhangbay);
		java.net.URL urlIcon_chinhsua = ADMIN.class.getResource("edit");
		Image img_chinhsua = ImageIO.read(getClass().getResource("edit.png"));
		java.net.URL urlIcon_xoa = ADMIN.class.getResource("delete");
		Image img_xoa = ImageIO.read(getClass().getResource("delete.png"));
		java.net.URL urlIcon_them = ADMIN.class.getResource("add2");
		Image img_them = ImageIO.read(getClass().getResource("add2.png"));

		JButton btnNewButton_them = new JButton("Thêm   ");
		btnNewButton_them.setBounds(27, 141, 180, 35);
		panel_lichbay.add(btnNewButton_them);
		btnNewButton_them.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_them.addActionListener(controller);
		btnNewButton_them.setBackground(new Color(255, 128, 128));
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setForeground(Color.white);
		btnNewButton_them.setIcon(new ImageIcon(img_them));

		JButton btnNewButton_chinhsua = new JButton("Chỉnh Sửa");
		btnNewButton_chinhsua.setBounds(512, 141, 180, 35);
		panel_lichbay.add(btnNewButton_chinhsua);
		btnNewButton_chinhsua.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_chinhsua.addActionListener(controller);
		btnNewButton_chinhsua.setBackground(new Color(240, 240, 240));
		btnNewButton_chinhsua.setBackground(new Color(255, 128, 128));
		btnNewButton_chinhsua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_chinhsua.setForeground(new Color(255, 255, 255));
		btnNewButton_chinhsua.setIcon(new ImageIcon(img_chinhsua));

		JButton btnNewButton_xoa = new JButton("Xóa       ");
		btnNewButton_xoa.setBounds(263, 141, 180, 35);
		panel_lichbay.add(btnNewButton_xoa);
		btnNewButton_xoa.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_xoa.addActionListener(controller);
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBackground(new Color(255, 128, 128));
		btnNewButton_xoa.setForeground(new Color(255, 255, 255));
		btnNewButton_xoa.setIcon(new ImageIcon(img_xoa));

		JButton btnNewButton_6 = new JButton("Tìm");
		btnNewButton_6.setBounds(757, 141, 180, 35);
		panel_lichbay.add(btnNewButton_6);
		btnNewButton_6.addActionListener(controller);
		btnNewButton_6.setBackground(new Color(255, 153, 153));
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jPanel_card.add(jPanel_duyet, "jPanel_duyet");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 215, 971, 237);
		jPanel_duyet.add(scrollPane_1);

		table_khachang = new JTable();

		table_khachang.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Tên Khách Hàng", "Ngày Sinh", "Giới Tính",
						"CCCD/CMND", "Mã Chuyến Bay", "Số Điện Thoại", "Tình Trạng", "Loại Ghế", "Số Ghế", "URL" }));
		scrollPane_1.setViewportView(table_khachang);
		table_khachang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table_khachang.getSelectedRow();
				if (selectRow != -1) {
					NewLabel_duyetcccd.setText(table_khachang.getValueAt(selectRow, 3) + "");
					String url = table_khachang.getValueAt(selectRow, 9) + "";
					displayImage(url);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("CCCD/CMND");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(22, 11, 126, 34);
		jPanel_duyet.add(lblNewLabel_1);

		NewLabel_duyetcccd = new JLabel("");
		NewLabel_duyetcccd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_duyetcccd.setBounds(178, 11, 190, 34);
		jPanel_duyet.add(NewLabel_duyetcccd);
		NewLabel_duyetcccd.setBorder(new LineBorder(Color.BLACK, 1));

		JLabel lblNewLabel_1_2 = new JLabel("Tình Trạng");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(400, 56, 126, 34);
		jPanel_duyet.add(lblNewLabel_1_2);

		String[] tt = new String[] { "", "Chờ xác nhận", "Đã xác nhận", "Xác nhận hủy chuyến", "Đã Hủy Chuyến Bay" };
		comboBox_tinhtrang = new JComboBox(tt);
		comboBox_tinhtrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_tinhtrang.setBounds(560, 58, 190, 34);
		jPanel_duyet.add(comboBox_tinhtrang);

		JLabel lblNewLabel_2 = new JLabel("Mã Chuyến Bay");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(400, 11, 150, 34);
		jPanel_duyet.add(lblNewLabel_2);

		JButton btnNewButton_2 = new JButton("Tìm");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(255, 128, 128));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timcackhachhangcungchuyenbay();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(789, 11, 160, 45);
		jPanel_duyet.add(btnNewButton_2);
		java.net.URL urlIcon_timkiem2 = ADMIN.class.getResource("timkiem");
		Image img_timkiem2 = ImageIO.read(getClass().getResource("timkiem.png"));
		btnNewButton_2.setIcon(new ImageIcon(img_timkiem2));

		JButton btnNewButton_3 = new JButton("Thông Báo");
		btnNewButton_3.setBackground(new Color(255, 128, 128));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_3.addActionListener(controller);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(789, 121, 160, 45);
		jPanel_duyet.add(btnNewButton_3);
		java.net.URL urlIcon_tbao = ADMIN.class.getResource("thongbao");
		Image img_thongbao = ImageIO.read(getClass().getResource("thongbao.png"));
		btnNewButton_3.setIcon(new ImageIcon(img_thongbao));

		comboBox_duyetmacb = new JComboBox();
		String[] macb = this.lichBayDAO.laymachuyenbay();
		comboBox_duyetmacb = new JComboBox<>();
		comboBox_duyetmacb.addItem("");
		for (String string : macb) {
			comboBox_duyetmacb.addItem(string);
		}
		comboBox_duyetmacb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_duyetmacb.setBounds(560, 13, 190, 34);
		jPanel_duyet.add(comboBox_duyetmacb);

		JLabel lblNewLabel_3 = new JLabel("Ảnh");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(22, 56, 126, 34);
		jPanel_duyet.add(lblNewLabel_3);

		NewLabel_anhchuyenkhoan = new JLabel("");
		NewLabel_anhchuyenkhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewLabel_anhchuyenkhoan.setBounds(178, 55, 150, 150);
		jPanel_duyet.add(NewLabel_anhchuyenkhoan);
		NewLabel_anhchuyenkhoan.setBorder(new LineBorder(Color.BLACK, 1));

		JButton btnNewButton_4 = new JButton("H.Thành");
		btnNewButton_4.setBackground(new Color(255, 128, 128));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_4.addActionListener(controller);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(789, 66, 160, 45);
		java.net.URL urlIcon_hthanh = ADMIN.class.getResource("hoanthanh");
		Image img_hthanh = ImageIO.read(getClass().getResource("hoanthanh.png"));
		btnNewButton_4.setIcon(new ImageIcon(img_hthanh));

		textArea_message = new JTextArea();
		textArea_message.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea_message.setLineWrap(true);
		textArea_message.setWrapStyleWord(true);
		textArea_message.setEditable(false);

		JScrollPane scrPane_chat = new JScrollPane(textArea_message);
		scrPane_chat.setBounds(10, 91, 825, 290);
		panel_chat.add(scrPane_chat);
		// Thiết lập thanh cuộn tự động xuất hiện khi cần thiết
		scrPane_chat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textField_sendChat = new JTextField();
		textField_sendChat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_sendChat.setBounds(10, 407, 825, 45);
		panel_chat.add(textField_sendChat);
		textField_sendChat.setColumns(10);

		JButton btnNewButton_send = new JButton("Gửi");
		btnNewButton_send.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_send.setBounds(845, 407, 134, 45);
		panel_chat.add(btnNewButton_send);

		jPanel_duyet.add(btnNewButton_4);
		jPanel_card.setBounds(205, 177, 991, 464);
		btnNewButton_lichbay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "panel_lichbay");
			}
		});
		btnNewButton_duyet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_duyet");
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "jPanel_kholuutru");
			}
		});

		btnNewButton_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = textField_sendChat.getText();
				writer.println(message);
				textField_sendChat.setText("");
			}
		});

		btnNewButton_chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel_card, "panel_chat");
				try {
					socket = new Socket("localhost", 9090);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(socket.getOutputStream(), true);

					// Gửi tên của client tới server khi kết nối
					writer.println("ADMIN");

					// Tạo một luồng mới để nhận tin nhắn từ server
					Thread receiveThread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								String message;
								while ((message = reader.readLine()) != null) {
									textArea_message.append(message + "\n");
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					receiveThread.start();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Server chưa hoạt động", "Error", JOptionPane.ERROR_MESSAGE);
					cardLayout.show(jPanel_card, "panel_lichbay");
				}
			}
		});
		contentPane.add(jPanel_card);

		JPanel panel_art = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("D:\\Workspace\\Intell\\QLCB\\src\\View\\nen2.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel_art.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_art.setBounds(2, 2, 1194, 173);
		contentPane.add(panel_art);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(2, 177, 201, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("Chức năng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4, BorderLayout.CENTER);

		jMenuBar = new JMenuBar();

		jMenu_ThongKe = new JMenu("Thống kê");
		jMenu_ThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));

		jMenuItem_LoaiGhe = new JMenuItem("Thống kê loại loại ghế được đặt qua các năm");
		jMenuItem_LoaiGhe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jMenuItem_DoanhThu = new JMenuItem("Thống kê doanh thu qua các năm");
		jMenuItem_DoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jMenuItem_HangBay = new JMenuItem("Xu hướng đặt vé của khách hàng với hãng bay qua các năm");
		jMenuItem_HangBay.setFont(new Font("Tahoma", Font.PLAIN, 18));

		jMenu_ThongKe.add(jMenuItem_DoanhThu);
		jMenu_ThongKe.add(jMenuItem_LoaiGhe);
		jMenu_ThongKe.add(jMenuItem_HangBay);

		jMenuBar.add(jMenu_ThongKe);

		setJMenuBar(jMenuBar);
		jMenuItem_LoaiGhe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BieuDoXuHuong bd = new BieuDoXuHuong();
				bd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				bd.setVisible(true);
			}
		});
		jMenuItem_DoanhThu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TKDoanhThu bd = new TKDoanhThu();
				bd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				bd.setVisible(true);
			}
		});
		jMenuItem_HangBay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TKHangBayDuocDat tk = new TKHangBayDuocDat();
				tk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tk.setVisible(true);
			}
		});

		table_lichbay.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table_lichbay.getSelectedRow();
				if (selectedRow != -1) {
					textField_lichmachuyenbay.setText(table_lichbay.getValueAt(selectedRow, 0) + "");
					textField_lichngaybay.setText(table_lichbay.getValueAt(selectedRow, 1) + "");
					textField_lichgiobay.setText(table_lichbay.getValueAt(selectedRow, 2) + "");
					comboBox_lichkhoihanh.setSelectedItem(table_lichbay.getValueAt(selectedRow, 3));
					comboBox_lichhacanh.setSelectedItem(table_lichbay.getValueAt(selectedRow, 4));
					comboBox_lichhangbay.setSelectedItem(table_lichbay.getValueAt(selectedRow, 5));
					textField_lichsucchua.setText(table_lichbay.getValueAt(selectedRow, 6) + "");
				}
			}
		});

		ArrayList<EditLichBay> result = lichBayDAO.selectAll();
		for (EditLichBay editLichBay : result) {
			this.themchuyenbayvaobang(editLichBay);
		}
		this.dsLichBay.laydulieutudatabase();
		this.dsKhachHang.capnhatdatabase();
		this.taikhachhanglenbang();

		setVisible(true);
	}

	public void thuchienthem() {
		if (this.textField_lichmachuyenbay.getText().isEmpty() || this.textField_lichngaybay.getText().isEmpty()
				|| this.textField_lichgiobay.getText().isEmpty() || this.comboBox_lichkhoihanh.getSelectedItem() == ""
				|| this.comboBox_lichhacanh.getSelectedItem() == "" || this.comboBox_lichhangbay.getSelectedItem() == ""
				|| this.textField_lichsucchua.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String machuyenbay = this.textField_lichmachuyenbay.getText();
		String ngaybay = this.textField_lichngaybay.getText();
		String gioBay = this.textField_lichgiobay.getText();
		String diemkhoihanh = this.comboBox_lichkhoihanh.getSelectedItem() + "";
		String diemhacanh = this.comboBox_lichhacanh.getSelectedItem() + "";
		String hangbay = this.comboBox_lichhangbay.getSelectedItem() + "";
		int succhua = Integer.valueOf(this.textField_lichsucchua.getText());
		String loi = "Chú ý các lỗi sau:\n";
		if (!kiemtramachuyenbay(machuyenbay)) {
			loi += "Quy định mã chuyến bay chỉ có chữ và số.\n";
		}
		if (!checkDateFormat(ngaybay)) {
			loi += "Nhập đúng định dạng: dd/MM/yyyy.\n";
		}
		if (!checkTimeFormat(gioBay)) {
			loi += "Nhập đúng định dạng: HH:mm.\n";
		}
		if (!kiemtrasucchua(this.textField_lichsucchua.getText())) {
			loi += "Sức chứa nhập số.\n";
		}
		if (succhua > 250 || succhua < 200) {
			loi += "Sức chứa nằm trong khoảng [200,250]\n";
		}
		if (loi != "Chú ý các lỗi sau:\n") {
			JOptionPane.showMessageDialog(this, loi, "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		EditLichBay lb = new EditLichBay(machuyenbay, ngaybay, gioBay, diemkhoihanh, diemhacanh, hangbay, succhua);
		this.themhoaccapnhapchuyenbay(lb);

		this.textField_lichmachuyenbay.setText("Mã chuyến bay");
		this.textField_lichngaybay.setText("Ngày Bay");
		this.textField_lichgiobay.setText("Giờ Bay");
		this.comboBox_lichkhoihanh.setSelectedIndex(0);
		this.comboBox_lichhacanh.setSelectedIndex(0);
		this.comboBox_lichhangbay.setSelectedIndex(0);
		this.textField_lichsucchua.setText("Sức chứa");
	}

	public void themhoaccapnhapchuyenbay(EditLichBay lb) {
		this.khoLuuTru.dulieutudatabasekholuutru();
		this.dsLichBay.dulieutudatabaselichbay();
		LuuTru lt = new LuuTru(lb.getMaChuyenBay());
		if (!this.khoLuuTru.kiemtramachuyenbaytontai(lt) && !this.dsLichBay.kiemtramachuyenbaytontai(lb)) {
			if (this.dsLichBay.kiemtragiobay(lb)) {
				this.lichBayDAO.insert(lb);
				this.dsLichBay.insert(lb);
				this.themchuyenbayvaobang(lb);
				this.lichBayDAO.themmachuyenbay(lb);
				int x = Integer.valueOf(textField_lichsucchua.getText());
				this.lichBayDAO.themloaigheFirstClass(lb, 0);
				this.lichBayDAO.themloaigheBusinessClass(lb, 0);
				this.lichBayDAO.themloaigheEconomyClass(lb, 0);
				for (int i = 1; i <= 20; i++) {
					this.lichBayDAO.themloaigheFirstClass(lb, i);
				}
				for (int i = 21; i <= 50; i++) {
					this.lichBayDAO.themloaigheBusinessClass(lb, i);
				}
				for (int i = 51; i <= x; i++) {
					this.lichBayDAO.themloaigheEconomyClass(lb, i);
				}
				JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
				this.textField_lichmachuyenbay.setText("Mã chuyến bay");
				this.textField_lichngaybay.setText("Ngày Bay");
				this.textField_lichgiobay.setText("Giờ Bay");
				this.comboBox_lichkhoihanh.setSelectedIndex(0);
				this.comboBox_lichhacanh.setSelectedIndex(0);
				this.comboBox_lichhangbay.setSelectedIndex(0);
				this.textField_lichsucchua.setText("Sức chứa");
			} else {
				JOptionPane.showMessageDialog(this, "Tại thời điểm này sân bay đã dạt tối đa chuyến hạ và cất cánh");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Mã chuyến bay đã tồn tại!");
		}
	}

	public void themchuyenbayvaobang(EditLichBay lb) {
		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		model_table.addRow(new Object[] { lb.getMaChuyenBay(), lb.getNgayBay(), lb.getGioBay(), lb.getDiemkhoihanh(),
				lb.getDiemHaCanh(), lb.getHangBay(), lb.getSucChua() + "" });
	}

	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(date);
	}

	private boolean kiemtramachuyenbay(String macb) {
		if (macb.matches(".*[^a-zA-Z0-9].*")) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean checkDateFormat(String ngBay) {
		try {
			String[] parts = ngBay.split("/");
			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);

			if (day < 1 || day > 31 || month < 1 || month > 12 || year < 2023) {
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

	private boolean checkTimeFormat(String time) {
		try {
			// tạo 1 mảng gồm các kí tự thời gian đc đc cắt bằng dấu ":"
			// time = "12:56"
			// parts[] = {12, 56}
			String[] parts = time.split(":");
			int h = Integer.parseInt(parts[0]);
			int m = Integer.parseInt(parts[1]);
			if (h < 0 || h > 23 || m < 0 || m > 59) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean kiemtrasucchua(String soghe) {
		try {
			int parsedSoghe = Integer.parseInt(soghe);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void thuchienchinhsua() {
		selectedRow = table_lichbay.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để chỉnh sửa");
			return;
		}
		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		String machuyenbay = this.textField_lichmachuyenbay.getText();
		String ngaybay = this.textField_lichngaybay.getText();
		String gioBay = this.textField_lichgiobay.getText();
		String diemkhoihanh = this.comboBox_lichkhoihanh.getSelectedItem() + "";
		String diemhacanh = this.comboBox_lichhacanh.getSelectedItem() + "";
		String hangbay = this.comboBox_lichhangbay.getSelectedItem() + "";
		int succhua = Integer.valueOf(this.textField_lichsucchua.getText());

		String loi = "Chú ý các lỗi sau:\n";
		if (!kiemtramachuyenbay(machuyenbay)) {
			loi += "Quy định mã chuyến bay chỉ có chữ và số.\n";
		}
		if (!checkDateFormat(ngaybay)) {
			loi += "Nhập đúng định dạng: dd/MM/yyyy.\n";
		}
		if (!checkTimeFormat(gioBay)) {
			loi += "Nhập đúng định dạng: HH:mm.\n";
		}
		if (!kiemtrasucchua(this.textField_lichsucchua.getText())) {
			loi += "Sức chứa nhập số.\n";
		}
		if (succhua > 250 || succhua < 200) {
			loi += "Sức chứa nằm trong khoảng [200,250]\n";
		}
		if (loi != "Chú ý các lỗi sau:\n") {
			JOptionPane.showMessageDialog(this, loi, "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		EditLichBay ed = getChuyenBayDangChon();
		String macb = ed.getMaChuyenBay();
		int countrow = ed.getSucChua();
		if (this.lichBayDAO.soluongdong(macb) == (countrow + 3)) {
			EditLichBay lb = new EditLichBay(machuyenbay, ngaybay, gioBay, diemkhoihanh, diemhacanh, hangbay, succhua);
			this.lichBayDAO.update(lb);
			this.dsLichBay.update(lb);
			this.tailichbaylenbang();

			this.textField_lichmachuyenbay.setText("Mã chuyến bay");
			this.textField_lichngaybay.setText("Ngày Bay");
			this.textField_lichgiobay.setText("Giờ Bay");
			this.comboBox_lichkhoihanh.setSelectedIndex(0);
			this.comboBox_lichhacanh.setSelectedIndex(0);
			this.comboBox_lichhangbay.setSelectedIndex(0);
			this.textField_lichsucchua.setText("Sức chứa");
		} else {
			JOptionPane.showMessageDialog(this, "Không thể chỉnh sửa chuyến bay này vì đã có khách hàng đặt vé");
		}
	}

	public void thuchienxoa() {
		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		selectedRow = table_lichbay.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để chỉnh sửa");
			return;
		} else {
			int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa chuyến bay này không");
			if (luachon == JOptionPane.YES_OPTION) {
				EditLichBay ed = getChuyenBayDangChon();
				String macb = ed.getMaChuyenBay();
				int countrow = ed.getSucChua();
				if (this.lichBayDAO.soluongdong(macb) == (countrow + 3)) {
					this.dsLichBay.delete(ed);
					this.lichBayDAO.delete(ed);
					model_table.removeRow(selectedRow);
					this.lichBayDAO.xoabangmachuyenbay(macb);
				} else {
					JOptionPane.showMessageDialog(this, "Không thể xóa chuyến bay này vì đã có khách hàng đặt vé");
				}
			}
		}
		this.textField_lichmachuyenbay.setText("Mã chuyến bay");
		this.textField_lichngaybay.setText("Ngày Bay");
		this.textField_lichgiobay.setText("Giờ Bay");
		this.comboBox_lichkhoihanh.setSelectedIndex(0);
		this.comboBox_lichhacanh.setSelectedIndex(0);
		this.comboBox_lichhangbay.setSelectedIndex(0);
		this.textField_lichsucchua.setText("Sức chứa");
	}

	public EditLichBay getChuyenBayDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		selectedRow = table_lichbay.getSelectedRow();
		String machuyenbay = model_table.getValueAt(selectedRow, 0) + "";
		String ngaybay = model_table.getValueAt(selectedRow, 1) + "";
		String giobay = model_table.getValueAt(selectedRow, 2) + "";
		String khoihanh = model_table.getValueAt(selectedRow, 3) + "";
		String hacanh = model_table.getValueAt(selectedRow, 4) + "";
		String hangbay = model_table.getValueAt(selectedRow, 5) + "";
		int soghe = Integer.valueOf(model_table.getValueAt(selectedRow, 6) + "");
		EditLichBay lb = new EditLichBay(machuyenbay, ngaybay, giobay, khoihanh, hacanh, hangbay, soghe);
		return lb;
	}

	public void tailichbaylenbang() {
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
		for (EditLichBay lb : this.lichBayDAO.selectAll()) {
			this.themchuyenbayvaobang(lb);
		}
	}

	public void themkhachhangvaobangkhachhang(KhachHang kh) {
		DefaultTableModel model_table = (DefaultTableModel) table_khachang.getModel();
		model_table.addRow(new Object[] { kh.getTenKhachHang(), kh.getNgaySinh(), kh.isGioiTinh() ? "Nam" : "Nữ",
				kh.getCccd(), kh.getMaChuyenBay(), kh.getSoDienThoai(), kh.getTinhTrang(), kh.getLoaiGhe(),
				kh.getSoGhe(), kh.getUrl() });
	}

	public void taikhachhanglenbang() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table_khachang.getModel();
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

	public void xacnhankhachhang() {
		DefaultTableModel model_table = (DefaultTableModel) table_khachang.getModel();
		int i_row = table_khachang.getSelectedRow();
		if (i_row < 0) {
			JOptionPane.showMessageDialog(this, "Chọn khách hàng cần thông báo");
			return;
		} else {
			int luachon = JOptionPane.showConfirmDialog(this, "Xác nhận thay đổi thông bao cho khác hàng");
			if (luachon == JOptionPane.YES_OPTION) {
				String xn = NewLabel_duyetcccd.getText();
				String tt = comboBox_tinhtrang.getSelectedItem() + "";
				this.khachHangDAO.updatetinhtrang(xn, tt);
				this.dsKhachHang.capnhatdatabase();
				this.taikhachhanglenbang();
			}
		}
	}

	public void timcackhachhangcungchuyenbay() {
		this.taikhachhanglenbang();
		String macb = comboBox_duyetmacb.getSelectedItem() + "";

		DefaultTableModel model_table = (DefaultTableModel) table_khachang.getModel();
		int soluongdong = model_table.getRowCount();

		Set<String> machuyenbaycanxoa = new TreeSet<String>();
		if (macb.length() > 0) {
			for (int i = 0; i < soluongdong; i++) {
				String cb = model_table.getValueAt(i, 4) + "";
				String cccd = model_table.getValueAt(i, 3) + "";
				if (!cb.equals(macb)) {
					machuyenbaycanxoa.add(cccd);
				}
			}
		}
		for (String s : machuyenbaycanxoa) {
			for (int i = 0; i < soluongdong; i++) {
				String cccd = model_table.getValueAt(i, 3) + "";
				if (cccd.equals(s.toString())) {
					try {
						model_table.removeRow(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ADMIN.this, "Không thể hiển thị ảnh từ đường dẫn: " + filePath, "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void hoanthanhchuyenbay() {
		String macb = comboBox_duyetmacb.getSelectedItem() + "";
		this.dsLichBay.laydulieutudatabase();
		if (macb.equals("")) {
			JOptionPane.showMessageDialog(this, "Chọn mã chuyến bay");
		} else {
			int luachon = JOptionPane.showConfirmDialog(this, "Xác nhận chuyến bay đã hoàn thành?", "",
					JOptionPane.YES_NO_OPTION);
			if (luachon == JOptionPane.YES_OPTION) {
				this.lichBayDAO.xoachuyenbayhoanthanh(macb); // Xóa chuyến bay ở trong lịch bay sau khi hoàn thành
																// chuyến bay
				for (EditLichBay ed : this.dsLichBay.getDsLichBay()) {
					if (ed.getMaChuyenBay().equals(macb)) {
						String nb = ed.getNgayBay();
						this.lichBayDAO.themvaokholuutru(macb, nb, ed.getHangBay()); // Thêm vào bảng lưu trữ sau khi
																						// hoàn thành
					}
				}
				this.lichBayDAO.xoabangmachuyenbay(macb); // Xóa bảng chuyến bay để thay bảng mới
				this.lichBayDAO.taobangchuyenbaymoi(macb); // Tạo bảng chuyến bay mới để lưu trữ thông tin khách hàng đã
															// đi chuyến bay này
				this.laythongtinkhachhang();

			}
		}
	}

	public void laythongtinkhachhang() {
		this.timcackhachhangcungchuyenbay();

		DefaultTableModel model_table = (DefaultTableModel) table_khachang.getModel();
		int soluongdong = model_table.getRowCount();
		for (int i = 0; i < soluongdong; i++) {
			String tenkh = model_table.getValueAt(i, 0) + "";
			String ngaysinh = model_table.getValueAt(i, 1) + "";
			String s_gioitinh = model_table.getValueAt(i, 2) + "";
			boolean gioitinh = s_gioitinh.equals("Nam");
			String cccd = model_table.getValueAt(i, 3) + "";
			String macb = model_table.getValueAt(i, 4) + "";
			String sdt = model_table.getValueAt(i, 5) + "";
			String tinhtrang = model_table.getValueAt(i, 6) + "";
			String loaighe = model_table.getValueAt(i, 7) + "";
			int soghe = Integer.valueOf(model_table.getValueAt(i, 8) + "");
			String url = model_table.getValueAt(i, 9) + "";
			KhachHang kh = new KhachHang(tenkh, ngaysinh, gioitinh, cccd, sdt, macb, tinhtrang, loaighe, soghe, url);
			this.lichBayDAO.themthongtinkhachhang(kh); // Lưu trữ thông tin khách hàng vào kho lưu trữ
			this.lichBayDAO.xoakhachhangtrongchuyenbay(cccd);
		}
	}

	public void timcackhachhang() {
		String macb = comboBox.getSelectedItem() + "";
		ArrayList<KhachHang> dskhachhang = this.khachHangDAO.dskhachhangdadi(macb);
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table_kholuutru.getModel();
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
		for (KhachHang kh : dskhachhang) {
			themkhachhangvaobang(kh);
		}
	}

	public void themkhachhangvaobang(KhachHang kh) {
		DefaultTableModel model_table = (DefaultTableModel) table_kholuutru.getModel();
		model_table.addRow(new Object[] { kh.getTenKhachHang(), kh.getNgaySinh(), kh.isGioiTinh() ? "Nam" : "Nữ",
				kh.getCccd(), kh.getMaChuyenBay(), kh.getSoDienThoai(), kh.getTinhTrang(), kh.getLoaiGhe(),
				kh.getSoGhe() + "", kh.getUrl() });
	}

	public void thuchientim() {
		this.tailichbaylenbang();

		String ngaybay = textField_lichngaybay.getText();
		String giobay = textField_lichgiobay.getText();
		int diemkhoihanh = comboBox_lichkhoihanh.getSelectedIndex() - 1;
		int diemhacanh = comboBox_lichhacanh.getSelectedIndex() - 1;
		int hangbay = comboBox_lichhangbay.getSelectedIndex() - 1;

		DefaultTableModel model_table = (DefaultTableModel) table_lichbay.getModel();
		int soluongdong = model_table.getRowCount();

		Set<String> machuyenbaycanxoa = new TreeSet<String>();

		if (ngaybay.length() > 0 && !ngaybay.equals("Ngày Bay")) {
			for (int i = 0; i < soluongdong; i++) {
				String nb = model_table.getValueAt(i, 1) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!nb.equals(ngaybay)) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (giobay.length() > 0 && !giobay.equals("Giờ Bay")) {
			for (int i = 0; i < soluongdong; i++) {
				String gb = model_table.getValueAt(i, 2) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!gb.equals(giobay)) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (diemkhoihanh >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String kh = model_table.getValueAt(i, 3) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!kh.equals(comboBox_lichkhoihanh.getSelectedItem())) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (diemhacanh >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String hc = model_table.getValueAt(i, 4) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!hc.equals(comboBox_lichhacanh.getSelectedItem())) {
					machuyenbaycanxoa.add(macb);
				}
			}
		}

		if (hangbay >= 0) {
			for (int i = 0; i < soluongdong; i++) {
				String hb = model_table.getValueAt(i, 5) + "";
				String macb = model_table.getValueAt(i, 0) + "";
				if (!hb.equals(comboBox_lichhangbay.getSelectedItem())) {
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

		textField_lichngaybay.setText("Ngày Bay");
		textField_lichgiobay.setText("Giờ Bay");
		comboBox_lichhacanh.setSelectedIndex(0);
		comboBox_lichhangbay.setSelectedIndex(0);
		comboBox_lichkhoihanh.setSelectedIndex(0);

	}
}
