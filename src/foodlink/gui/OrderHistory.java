package foodlink.gui;

import javax.swing.*;
import java.awt.*;

public class OrderHistory extends JFrame {

    private JPanel orderPanel;

    public OrderHistory() {

        setTitle("FoodLink - Order History");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel(
                "ORDER HISTORY"
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setBounds(
                40, 30, 300, 40
        );

        add(title);

        orderPanel = new JPanel();

        orderPanel.setLayout(
                new BoxLayout(
                        orderPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(orderPanel);

        scrollPane.setBounds(
                40, 90, 600, 380
        );

        add(scrollPane);

        JButton back = new JButton(
                "BACK"
        );

        back.setBounds(
                40, 500, 120, 40
        );

        add(back);

        back.addActionListener(
                e -> dispose()
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addOrder(
            String orderId,
            String shopName,
            double amount,
            String status) {

        JPanel order = new JPanel();

        order.setLayout(null);

        order.setPreferredSize(
                new Dimension(560, 100)
        );

        JLabel id = new JLabel(
                "Order ID: " + orderId
        );

        id.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        id.setBounds(
                10, 10, 250, 25
        );

        order.add(id);

        JLabel shop = new JLabel(
                "Shop: " + shopName
        );

        shop.setBounds(
                10, 40, 300, 25
        );

        order.add(shop);

        JLabel amountLabel = new JLabel(
                "Amount: ₹" + amount
        );

        amountLabel.setBounds(
                330, 10, 180, 25
        );

        order.add(amountLabel);

        JLabel statusLabel = new JLabel(
                "Status: " + status
        );

        statusLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        statusLabel.setBounds(
                330, 40, 180, 25
        );

        order.add(statusLabel);

        orderPanel.add(order);

        orderPanel.revalidate();
        orderPanel.repaint();
    }
}