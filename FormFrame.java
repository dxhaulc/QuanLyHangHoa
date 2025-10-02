package QuanLyHangHoa;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FormFrame extends JFrame {
    private String filePath = "data.ser";

    private JTextField tfTenHangHoa;
    private JTextField tfMaHangHoa;
    private JTextField tfDonViTinh;
    private JTextField tfSoLuong;

    private JButton btThem;
    private JButton btLuu;
    private JButton btXoa;
    private JButton btSua;
    private JButton btSapXep;
    private JButton btClearForm;

    private DefaultListModel<HangHoa> listModel;
    private JList<HangHoa> dataList;

    public FormFrame() {
        setTitle("Quản lý danh sách sản phẩm");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        setVisible(true);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("QUẢN LÝ HÀNG HÓA"));
        add(titlePanel);

        JPanel pnTenHangHoa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbTenHangHoa = new JLabel("Tên hàng hóa:");
        lbTenHangHoa.setPreferredSize(new Dimension(90, 20));
        tfTenHangHoa = new JTextField(50);
        pnTenHangHoa.add(lbTenHangHoa);
        pnTenHangHoa.add(tfTenHangHoa);
        add(pnTenHangHoa);

        JPanel pnMaHangHoa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbMaHangHoa = new JLabel("Mã hàng hóa:");
        lbMaHangHoa.setPreferredSize(lbTenHangHoa.getPreferredSize());
        tfMaHangHoa = new JTextField(50);
        pnMaHangHoa.add(lbMaHangHoa);
        pnMaHangHoa.add(tfMaHangHoa);
        add(pnMaHangHoa);

        JPanel pnDonViTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbDonViTinh = new JLabel("Đơn vị tính: ");
        lbDonViTinh.setPreferredSize(lbTenHangHoa.getPreferredSize());
        tfDonViTinh = new JTextField(50);
        pnDonViTinh.add(lbDonViTinh);
        pnDonViTinh.add(tfDonViTinh);
        add(pnDonViTinh);

        JPanel pnSoLuong = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbSoLuong = new JLabel("Số lượng:");
        lbSoLuong.setPreferredSize(lbTenHangHoa.getPreferredSize());
        tfSoLuong = new JTextField(50);
        pnSoLuong.add(lbSoLuong);
        pnSoLuong.add(tfSoLuong);
        add(pnSoLuong);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btThem = new JButton("Thêm hàng hóa");
        buttonPanel.add(btThem);
        btSua = new JButton("Sửa");
        buttonPanel.add(btSua);
        btXoa = new JButton("Xóa");
        buttonPanel.add(btXoa);
        btLuu = new JButton("Lưu");
        buttonPanel.add(btLuu);
        btSapXep = new JButton("Sắp xếp");
        buttonPanel.add(btSapXep);
        btClearForm = new JButton("Clear Form");
        buttonPanel.add(btClearForm);


        add(buttonPanel);

        btThem.addActionListener(e -> {

            if (tfTenHangHoa.getText().isEmpty() || tfMaHangHoa.getText().isEmpty() || tfDonViTinh.getText().isEmpty()
                    || tfSoLuong.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bạn không nhập đủ dữ liệu! Hãy nhập đủ!");
                return;
            }
            else if(tfTenHangHoa.getText().length() > 50){
                JOptionPane.showMessageDialog(null, "Tên hàng hóa không được vượt quá 50 kí tự!");
                return;
            }
            else if(tfMaHangHoa.getText().length() > 30){
                JOptionPane.showMessageDialog(null, "Mã hàng hóa không được vượt quá 30 kí tự!");
                return;
            }     
            else if(tfDonViTinh.getText().length() > 10){
                JOptionPane.showMessageDialog(null, "Đơn vị tính không được vượt quá 10 kí tự!");
                return;
            }
            try {
                int soLuongHangHoa;
                soLuongHangHoa = Integer.parseInt(tfSoLuong.getText());
                if(soLuongHangHoa < 0){
                    JOptionPane.showMessageDialog(null, "Số lượng phải >= 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là số nguyên! Nhập lại!");
                return;
            }
            HangHoa findHH = null;
            for (int i = 0; i < listModel.getSize(); i++) {
                findHH = listModel.getElementAt(i);
                if (findHH.getTenHangHoa().equals(tfTenHangHoa.getText()) || findHH.getMaHangHoa().equals(tfMaHangHoa.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên hàng hóa hoặc mã hàng hóa đã bị trùng! Nhập lại!");
                    return;
                }
            }

            listModel.addElement(new HangHoa(tfTenHangHoa.getText(), tfMaHangHoa.getText(), tfDonViTinh.getText(),
                    tfSoLuong.getText()));
            clearForm();

        });

        btSua.addActionListener(e -> {

            HangHoa findHH = null;
            for (int i = 0; i < listModel.getSize(); i++) {
                findHH = listModel.getElementAt(i);
                if (findHH.getMaHangHoa().equals(tfMaHangHoa.getText())) {

                    if (tfTenHangHoa.getText().isEmpty() || tfDonViTinh.getText().isEmpty()
                            || tfSoLuong.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn không nhập đủ dữ liệu! Hãy nhập đủ!");
                        return;
                    }
                    else if(tfTenHangHoa.getText().length() > 50){
                        JOptionPane.showMessageDialog(null, "Tên hàng hóa không được vượt quá 50 kí tự!");
                        return;
                    }
                    else if(tfMaHangHoa.getText().length() > 30){
                        JOptionPane.showMessageDialog(null, "Mã hàng hóa không được vượt quá 30 kí tự!");
                        return;
                    }     
                    else if(tfDonViTinh.getText().length() > 10){
                        JOptionPane.showMessageDialog(null, "Đơn vị tính không được vượt quá 10 kí tự!");
                        return;
                    }
                    try {
                        int soLuongHangHoa;
                        soLuongHangHoa = Integer.parseInt(tfSoLuong.getText());
                        if(soLuongHangHoa < 0){
                            JOptionPane.showMessageDialog(null, "Số lượng phải >= 0!");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là số nguyên! Nhập lại!");
                        return;
                    }

                    for (int j = 0; j < listModel.getSize(); j++) {
                        HangHoa findHHb = listModel.getElementAt(j);
                        if(findHHb.getMaHangHoa().equals(tfMaHangHoa.getText())) continue;
                        else if (findHHb.getTenHangHoa().equals(tfTenHangHoa.getText())) {
                            JOptionPane.showMessageDialog(null, "Tên hàng hóa đã bị trùng! Nhập lại!");
                            return;
                        }
                    }
    

                    findHH.setTenHangHoa(tfTenHangHoa.getText());
                    findHH.setDonViTinh(tfDonViTinh.getText());
                    findHH.setSoLuong(tfSoLuong.getText());

                    listModel.insertElementAt(findHH, i);
                    listModel.remove(i + 1);

                    return;
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Không tìm thấy hàng hóa nào có mã " + tfMaHangHoa.getText() + " để sửa ");
        });

        btXoa.addActionListener(e -> {
            HangHoa value = dataList.getSelectedValue();
            System.out.println(value);
            int choice = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn xóa hàng hóa đang chọn này không", "Xóa hay không",
                    JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                try {
                    listModel.removeElement(value);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    }
                dataList.repaint();
                clearForm();
                return;
            }
        });

        
        btLuu.addActionListener(e -> {
            SaveData();
        });

        btSapXep.addActionListener(e -> {

            HangHoa temp;
            HangHoa m = null;
            HangHoa n = null;
            for (int i = 0; i < listModel.getSize() - 1; i++) {
                for (int j = 1; j < listModel.getSize(); j++) {
                    m = listModel.getElementAt(j - 1);
                    n = listModel.getElementAt(j);
                    int x = Integer.parseInt(m.getSoLuong());
                    int y = Integer.parseInt(n.getSoLuong());
                    if ((x - y) < 0) {
                        temp = m;
                        listModel.setElementAt(n, j - 1);
                        listModel.setElementAt(temp, j);
                    }
                }
            }
        });

        btClearForm.addActionListener(e -> {
            clearForm();
        });

        listModel = new DefaultListModel<>();
        dataList = new JList<>(listModel);
        dataList.setAutoscrolls(true);
        dataList.setVisibleRowCount(10);
        LoadData();

        dataList.addListSelectionListener(e -> {
            HangHoa value = dataList.getSelectedValue();
            tfTenHangHoa.setText(value.getTenHangHoa());
            tfMaHangHoa.setText(value.getMaHangHoa());
            tfDonViTinh.setText(value.getDonViTinh());
            tfSoLuong.setText(value.getSoLuong());

        });

        JScrollPane scrollPane = new JScrollPane(dataList);
        Border border = BorderFactory.createLineBorder(Color.pink);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Danh sách hàng hóa");
        scrollPane.setBorder(titledBorder);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
        pack();
        // setVisible(true);
    }

    private void clearForm() {
        tfTenHangHoa.setText("");
        tfMaHangHoa.setText("");
        tfDonViTinh.setText("");
        tfSoLuong.setText("");
    }

    private void LoadData() {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return;
            }
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<HangHoa> listHH = (ArrayList<HangHoa>) in.readObject();
            for (HangHoa hh : listHH) {
                listModel.addElement(hh);
            }
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void SaveData() {
        try {
            // Serialize đối tượng sang dạng nhị phân
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            ArrayList<HangHoa> list = new ArrayList<>();
            for (int i = 0; i < dataList.getModel().getSize(); i++) {
                list.add(dataList.getModel().getElementAt(i));
            }
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}