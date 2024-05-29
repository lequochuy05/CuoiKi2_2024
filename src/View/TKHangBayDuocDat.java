package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import DAO.XuHuongDAO;

public class TKHangBayDuocDat extends JFrame {
    private ADMIN admin;
    private XuHuongDAO xuHuongDAO;
    private JTextField txtNhapNam;
    private JButton btnXacNhan;

    public TKHangBayDuocDat(ADMIN admin) {
        this.admin = admin;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
    }
    public TKHangBayDuocDat() {
        init();
    }

    public void init() {
        setTitle("Thống kê số lượng chuyến bay theo hãng hàng không");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNhapNam = new JLabel("Nhập năm:");
        lblNhapNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNhapNam.setBounds(50, 30, 100, 30);
        contentPane.add(lblNhapNam);

        txtNhapNam = new JTextField();
        txtNhapNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtNhapNam.setBounds(150, 30, 183, 30);
        contentPane.add(txtNhapNam);
        txtNhapNam.setColumns(10);

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnXacNhan.setBounds(373, 30, 123, 30);
        contentPane.add(btnXacNhan);

        // Tạo biểu đồ và panel chứa biểu đồ
        JPanel panelChart = new JPanel();
        panelChart.setBounds(50, 80, 750, 450);
        contentPane.add(panelChart);

        xuHuongDAO = new XuHuongDAO();

        btnXacNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nam = txtNhapNam.getText();
                if (!nam.isEmpty()) {
                    if (xuHuongDAO.kiemTraNam(nam)) {
                        panelChart.removeAll(); // Xóa panel trước khi vẽ biểu đồ mới
                        veBieuDoThongKe(nam, panelChart);
                        panelChart.revalidate();
                        panelChart.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Năm không có dữ liệu chuyến bay.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập năm.");
                }
            }
        });

        // Thêm sự kiện đóng cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                if (admin != null) {
                    admin.setVisible(true);
                }
            }
        });
    }

    // Phương thức vẽ biểu đồ thống kê
    private void veBieuDoThongKe(String nam, JPanel panel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String[] hangBayArr = xuHuongDAO.layHangBay(nam);
        for (String hangBay : hangBayArr) {
            int soLuongChuyenBay = xuHuongDAO.demSoLuongHangBay(hangBay, nam);
            // System.out.println(soLuongChuyenBay);
            dataset.addValue(soLuongChuyenBay, hangBay, "");
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê số lượng chuyến bay theo hãng hàng không năm " + nam, "Hãng hàng không",
                "Số lượng chuyến bay", dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);

        // Thay đổi kích thước của các cột
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.getDomainAxis().setCategoryMargin(0.1); // Điều chỉnh khoảng cách giữa các cột

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TKHangBayDuocDat frame = new TKHangBayDuocDat();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
}