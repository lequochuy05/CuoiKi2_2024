package View;
import javax.swing.*;

import DAO.XuHuongDAO;
import Model.LoaiGhe;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BieuDoXuHuong extends JFrame {
    private JTextField textField;
    private JPanel chartPanel;

    public BieuDoXuHuong() {
        setTitle("Xu Hướng Đặt Ghế ");
        setSize(810, 547);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        chartPanel = new JPanel(new BorderLayout());
        contentPane.add(chartPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        contentPane.add(inputPanel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Nhập năm");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        inputPanel.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setColumns(10);
        inputPanel.add(textField);

        JButton showChartButton = new JButton("Xác Nhận");
        showChartButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        inputPanel.add(showChartButton);

        showChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XuHuongDAO xh = new XuHuongDAO();
                LoaiGhe loaiGhe = xh.xuHuongDatQuaCacNam(textField.getText());
                if (xh.kiemTraNam(textField.getText())) {
                    // Tạo dataset cho biểu đồ tròn
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    dataset.setValue("EconomyClass: ", loaiGhe.getEconomyclass());
                    dataset.setValue("BusinessClass: ", loaiGhe.getBusinessclass());
                    dataset.setValue("FirstClass: ", loaiGhe.getFirstclass());

                    // Tạo biểu đồ tròn
                    JFreeChart pieChart = ChartFactory.createPieChart(
                            "Biểu đồ xu hướng đặt ghế qua năm " + textField.getText(),
                            dataset,
                            true, true, false);

                    // Hiển thị biểu đồ tròn trong ChartPanel
                    ChartPanel chartPanel = new ChartPanel(pieChart);
                    chartPanel.setPreferredSize(new Dimension(400, 300));

                    BieuDoXuHuong.this.chartPanel.removeAll();
                    BieuDoXuHuong.this.chartPanel.add(chartPanel, BorderLayout.CENTER);
                    BieuDoXuHuong.this.chartPanel.revalidate();
                    BieuDoXuHuong.this.chartPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Năm không có dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BieuDoXuHuong().setVisible(true);
            }
        });
    }
}
