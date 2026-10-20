package foodlink.gui;

import javax.swing.*;
import java.awt.*;

public class Payment extends JFrame {

    private double totalAmount;

    public Payment(double totalAmount) {

        this.totalAmount = totalAmount;

        setTitle("FoodLink - Payment");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("PAYMENT");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setBounds(
                40, 30, 300, 40
        );

        add(title);

        JLabel amountLabel = new JLabel(
                "Total Amount: ₹" + totalAmount
        );

        amountLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        amountLabel.setBounds(
                40, 100, 400, 40
        );

        add(amountLabel);

        JLabel paymentLabel = new JLabel(
                "Select Payment Method"
        );

        paymentLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        paymentLabel.setBounds(
                40, 165, 300, 30
        );

        add(paymentLabel);

        JRadioButton cash = new JRadioButton(
                "Cash"
        );

        cash.setBounds(
                40, 215, 100, 30
        );

        add(cash);

        JRadioButton upi = new JRadioButton(
                "UPI"
        );

        upi.setBounds(
                160, 215, 100, 30
        );

        add(upi);

        JRadioButton card = new JRadioButton(
                "Card"
        );

        card.setBounds(
                280, 215, 100, 30
        );

        add(card);

        ButtonGroup paymentGroup =
                new ButtonGroup();

        paymentGroup.add(cash);
        paymentGroup.add(upi);
        paymentGroup.add(card);

        JButton payButton = new JButton(
                "PAY NOW"
        );

        payButton.setBounds(
                40, 290, 150, 45
        );

        add(payButton);

        JButton backButton = new JButton(
                "BACK"
        );

        backButton.setBounds(
                220, 290, 120, 45
        );

        add(backButton);

        JLabel status = new JLabel("");

        status.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        status.setBounds(
                40, 370, 450, 35
        );

        add(status);

        payButton.addActionListener(e -> {

            String method = "";

            if (cash.isSelected()) {
                method = "Cash";
            } else if (upi.isSelected()) {
                method = "UPI";
            } else if (card.isSelected()) {
                method = "Card";
            }

            if (method.equals("")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a payment method."
                );

            } else {

                status.setText(
                        "Payment successful using " + method
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Payment successful."
                );
            }
        });

        backButton.addActionListener(
                e -> dispose()
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }
}