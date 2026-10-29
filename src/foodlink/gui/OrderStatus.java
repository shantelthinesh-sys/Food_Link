package foodlink.gui;

import javax.swing.*;
import java.awt.*;

public class OrderStatus extends JFrame {

    private String orderStatus;

    public OrderStatus() {

        orderStatus = "Confirmed";

        setTitle("FoodLink - Order Status");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel(
                "ORDER STATUS"
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setBounds(
                40, 40, 300, 40
        );

        add(title);

        JLabel orderId = new JLabel(
                "Order ID: Pending"
        );

        orderId.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        orderId.setBounds(
                40, 110, 300, 30
        );

        add(orderId);

        JLabel statusTitle = new JLabel(
                "Current Status"
        );

        statusTitle.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        statusTitle.setBounds(
                40, 170, 250, 30
        );

        add(statusTitle);

        JLabel status = new JLabel(
                orderStatus
        );

        status.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        status.setBounds(
                40, 215, 300, 35
        );

        add(status);

        JLabel information = new JLabel(
                "Your order is being processed."
        );

        information.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        information.setBounds(
                40, 270, 450, 30
        );

        add(information);

        JButton refresh = new JButton(
                "REFRESH"
        );

        refresh.setBounds(
                40, 340, 130, 45
        );

        add(refresh);

        JButton back = new JButton(
                "BACK"
        );

        back.setBounds(
                200, 340, 120, 45
        );

        add(back);

        refresh.addActionListener(e -> {

            status.setText(orderStatus);

            information.setText(
                    "Current order status: "
                            + orderStatus
            );
        });

        back.addActionListener(
                e -> dispose()
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateStatus(String newStatus) {

        orderStatus = newStatus;
    }
}