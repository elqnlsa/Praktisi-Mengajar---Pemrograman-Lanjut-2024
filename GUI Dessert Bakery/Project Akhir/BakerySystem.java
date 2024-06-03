import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BakerySystem extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JList<String> menuList;
    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private Map<String, Integer> menuItems;
    private ArrayList<String> order;

    public BakerySystem() {
        setTitle("Sistem Antrian Bakery dan Dessert");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menuItems = new HashMap<>();
        order = new ArrayList<>();

        addMenuItems();

        initInputComponents();
    }

    private void addMenuItems() {
        menuItems.put("Cake Tart", 45000);
        menuItems.put("Strawberry Shortcake", 35000);
        menuItems.put("Tiramisu", 65000);
        menuItems.put("Iced Frappucino", 30000);
        menuItems.put("Iced Matcha", 35500);
        menuItems.put("Cheesecake", 40000);
        menuItems.put("Macarons", 70000);
        menuItems.put("Baguette", 30000);
    }

    private void initInputComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 228, 196));
        add(panel);

        JLabel nameLabel = new JLabel("Nama:");
        panel.add(nameLabel);
        nameField = new JTextField(15);
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("Nomor Telepon:");
        panel.add(phoneLabel);
        phoneField = new JTextField(15);
        panel.add(phoneField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(135, 206, 250));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitInfo();
            }
        });
        panel.add(submitButton);
    }

    private void submitInfo() {
        String name = nameField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan Nomor Telepon harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        showMenu();
    }

    private void showMenu() {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 250, 205));
        add(panel);

        JLabel menuLabel = new JLabel("Pilih Menu Dessert Bakery");
        menuLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        panel.add(menuLabel);

        menuList = new JList<>(menuItems.keySet().toArray(new String[0]));
        menuList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        panel.add(menuScrollPane);

        JButton orderButton = new JButton("Tambah ke Daftar Pesanan");
        orderButton.setBackground(new Color(144, 238, 144));
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder();
            }
        });
        panel.add(orderButton);

        JLabel orderLabel = new JLabel("Daftar Pesanan Anda:");
        orderLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
        panel.add(orderLabel);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);
        panel.add(orderScrollPane);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(255, 182, 193));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });
        panel.add(checkoutButton);

        revalidate();
        repaint();
    }

    private void addToOrder() {
        for (String item : menuList.getSelectedValuesList()) {
            order.add(item);
            orderListModel.addElement(item);
        }
    }

    private void checkout() {
        if (order.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Daftar pesanan Anda kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String customerName = nameField.getText();
        String customerPhone = phoneField.getText();
        int totalItems = order.size();
        int totalPrice = 0;

        StringBuilder orderDetails = new StringBuilder("Pesanan Anda:\n");
        for (String item : order) {
            totalPrice += menuItems.get(item);
            orderDetails.append(item).append(" - ").append(menuItems.get(item)).append(" rupiah\n");
        }

        orderDetails.append("\nTotal Item: ").append(totalItems);
        orderDetails.append("\nTotal Harga: ").append(totalPrice).append(" rupiah");
        orderDetails.append("\n\nNama: ").append(customerName);
        orderDetails.append("\nNomor Telepon: ").append(customerPhone);
        orderDetails.append("\n\nTerima kasih telah berbelanja!");

        showFinalOrder(orderDetails.toString());
    }

    private void showFinalOrder(String orderDetails) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 248, 255));
        add(panel);

        JLabel finalLabel = new JLabel("Detail Pesanan Anda");
        finalLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        panel.add(finalLabel);

        JTextArea orderTextArea = new JTextArea(orderDetails);
        orderTextArea.setEditable(false);
        orderTextArea.setBackground(new Color(240, 248, 255));
        panel.add(orderTextArea);

        JButton newOrderButton = new JButton("Pesan Baru");
        newOrderButton.setBackground(new Color(144, 238, 144));
        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        panel.add(newOrderButton);

        revalidate();
        repaint();
    }

    private void reset() {
        order.clear();
        orderListModel.clear();
        getContentPane().removeAll();
        revalidate();
        repaint();
        initInputComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BakerySystem().setVisible(true);
            }
        });
    }
}
