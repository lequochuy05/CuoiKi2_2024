package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import DAO.XuHuongDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TKDoanhThu extends JFrame {
    private ADMIN admin;
    private XuHuongDAO xuHuongDAO;
    private JTextField textField;
    private JPanel panelChart;

    public TKDoanhThu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1055, 608);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelChart = new JPanel();
        panelChart.setBounds(10, 69, 1010, 444);
        contentPane.add(panelChart);

        JLabel lblNewLabel = new JLabel("Nhập năm");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(54, 20, 136, 39);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setBounds(155, 26, 191, 28);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Xác Nhận");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(395, 19, 149, 39);
        contentPane.add(btnNewButton);

        // Khởi tạo đối tượng XuHuongDAO
        this.xuHuongDAO = new XuHuongDAO();

        // Thêm sự kiện cho nút "Xác nhận"
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nam = textField.getText();
                if (!nam.isEmpty()) {
                    // Kiểm tra năm có dữ liệu hay không
                    if (xuHuongDAO.kiemTraNam(nam)) {
                        panelChart.removeAll(); // Xóa panel trước khi vẽ biểu đồ mới
                        veBieuDoDoanhThu(nam, panelChart);
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
    }

    public void veBieuDoDoanhThu(String year, JPanel panel) {
        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Lấy doanh thu của từng tháng trong năm
        for (int month = 1; month <= 12; month++) {
            double doanhThuThang = xuHuongDAO.xuHuongDoanhThuTheoThang(year, month);
            dataset.addValue(doanhThuThang, "Doanh thu", getTenThang(month));
        }

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ doanh thu năm " + year,
                "Tháng",
                "Doanh thu",
                dataset
        );

        // Hiển thị biểu đồ trong JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
        panel.removeAll(); // Xóa các biểu đồ cũ
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    // Hàm trợ giúp để lấy tên của tháng từ số tháng
    private String getTenThang(int month) {
        String[] monthNames = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        return monthNames[month - 1];
    }




    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TKDoanhThu frame = new TKDoanhThu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}