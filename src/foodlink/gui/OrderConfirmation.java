package foodlink.gui;

import javax.swing.*;
import java.awt.*;

public class OrderConfirmation extends JFrame {

    private double totalAmount;
    private String paymentMethod;

    public OrderConfirmation(
            double totalAmount,
            String paymentMethod) {

        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;

        setTitle("FoodLink - Order Confirmation");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel(
                "ORDER CONFIRMED"
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setBounds(
                40, 50, 400, 40
        );

        add(title);

        JLabel message = new JLabel(
                "Your order has been placed successfully."
        );

        message.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        message.setBounds(
                40, 120, 500, 30
        );

        add(message);

        JLabel amount = new JLabel(
                "Amount Paid: ₹" + totalAmount
        );

        amount.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        amount.setBounds(
                40, 180, 400, 35
        );

        add(amount);

        JLabel payment = new JLabel(
                "Payment Method: " + paymentMethod
        );

        payment.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        payment.setBounds(
                40, 230, 400, 30
        );

        add(payment);

        JLabel status = new JLabel(
                "Order Status: Confirmed"
        );

        status.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        status.setBounds(
                40, 280, 400, 30
        );

        add(status);

        JButton homeButton = new JButton(
                "BACK TO HOME"
        );

        homeButton.setBounds(
                40, 350, 160, 45
        );

        add(homeButton);

        JButton closeButton = new JButton(
                "CLOSE"
        );

        closeButton.setBounds(
                230, 350, 120, 45
        );

        add(closeButton);

        homeButton.addActionListener(e -> {

            dispose();

            new CustomerHome(
                    "Customer"
            );
        });

        closeButton.addActionListener(
                e -> dispose()
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }
}