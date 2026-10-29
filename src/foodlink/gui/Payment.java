package foodlink.gui;

import javax.swing.*;
import java.awt.*;

public class Payment extends JFrame {

    private double totalAmount;

    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color LIGHT_ORANGE = new Color(255, 243, 238);
    private final Color TEXT_DARK = new Color(35, 35, 35);
    private final Color TEXT_GREY = new Color(100, 100, 100);

    private JRadioButton cash;
    private JRadioButton upi;
    private JRadioButton card;

    private JTextField upiField;
    private JLabel detailsLabel;
    private JLabel statusLabel;
    private JButton payButton;

    public Payment(double totalAmount) {

        this.totalAmount = totalAmount;

        setTitle("FoodLink - Payment");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(CREAM);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Header
        JLabel title = new JLabel("FoodLink");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(DARK_GREEN);
        title.setBounds(35, 20, 250, 40);
        mainPanel.add(title);

        JLabel subtitle = new JLabel("Secure Checkout");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(TEXT_GREY);
        subtitle.setBounds(38, 58, 250, 25);
        mainPanel.add(subtitle);

        // Order amount card
        JPanel amountPanel = new JPanel(null);
        amountPanel.setBackground(DARK_GREEN);
        amountPanel.setBounds(35, 100, 565, 105);
        mainPanel.add(amountPanel);

        JLabel amountTitle = new JLabel("TOTAL AMOUNT");
        amountTitle.setFont(new Font("Arial", Font.BOLD, 14));
        amountTitle.setForeground(Color.WHITE);
        amountTitle.setBounds(25, 18, 200, 25);
        amountPanel.add(amountTitle);

        JLabel amountLabel = new JLabel(
                String.format("₹ %.2f", totalAmount)
        );
        amountLabel.setFont(new Font("Arial", Font.BOLD, 30));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(25, 43, 300, 45);
        amountPanel.add(amountLabel);

        // Payment heading
        JLabel paymentHeading = new JLabel("Choose Payment Method");
        paymentHeading.setFont(new Font("Arial", Font.BOLD, 21));
        paymentHeading.setForeground(TEXT_DARK);
        paymentHeading.setBounds(35, 225, 400, 35);
        mainPanel.add(paymentHeading);

        // Payment options
        cash = new JRadioButton("Cash on Delivery");
        upi = new JRadioButton("UPI Payment (Demo)");
        card = new JRadioButton("Card Payment (Demo)");

        styleRadioButton(cash);
        styleRadioButton(upi);
        styleRadioButton(card);

        cash.setBounds(45, 275, 300, 30);
        upi.setBounds(45, 315, 300, 30);
        card.setBounds(45, 355, 300, 30);

        mainPanel.add(cash);
        mainPanel.add(upi);
        mainPanel.add(card);

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cash);
        paymentGroup.add(upi);
        paymentGroup.add(card);

        // UPI input
        JLabel upiLabel = new JLabel("Enter UPI ID for demo:");
        upiLabel.setFont(new Font("Arial", Font.BOLD, 13));
        upiLabel.setForeground(TEXT_DARK);
        upiLabel.setBounds(45, 400, 220, 25);
        mainPanel.add(upiLabel);

        upiField = new JTextField();
        upiField.setBounds(45, 425, 280, 32);
        upiField.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(upiField);

        detailsLabel = new JLabel(
                "Select a payment method to continue."
        );
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsLabel.setForeground(TEXT_GREY);
        detailsLabel.setBounds(45, 460, 530, 25);
        mainPanel.add(detailsLabel);

        // Buttons
        payButton = new JButton("CONFIRM ORDER");
        payButton.setBounds(45, 510, 220, 45);
        payButton.setBackground(GREEN);
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setFocusPainted(false);
        payButton.setBorderPainted(false);
        mainPanel.add(payButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(285, 510, 120, 45);
        backButton.setBackground(ORANGE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        mainPanel.add(backButton);

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setForeground(GREEN);
        statusLabel.setBounds(45, 565, 540, 25);
        mainPanel.add(statusLabel);

        // Payment selection actions
        cash.addActionListener(e -> {
            upiField.setEnabled(false);
            detailsLabel.setText(
                    "Pay the delivery person when your order arrives."
            );
            statusLabel.setText("");
        });

        upi.addActionListener(e -> {
            upiField.setEnabled(true);
            detailsLabel.setText(
                    "Demo only. No real UPI transaction will occur."
            );
            statusLabel.setText("");
        });

        card.addActionListener(e -> {
            upiField.setEnabled(false);
            detailsLabel.setText(
                    "Demo only. No card details are required."
            );
            statusLabel.setText("");
        });

        // Initially disable UPI input
        upiField.setEnabled(false);

        // Confirm order
        payButton.addActionListener(e -> {

            String method;

            if (cash.isSelected()) {

                method = "Cash on Delivery";

                showConfirmation(method, "Pending");

            } else if (upi.isSelected()) {

                String upiId = upiField.getText().trim();

                if (upiId.isEmpty() || !upiId.contains("@")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid-format UPI ID.",
                            "Invalid UPI ID",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                method = "UPI (Demo)";

                showDemoConfirmation(method);

            } else if (card.isSelected()) {

                method = "Card (Demo)";

                showDemoConfirmation(method);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a payment method.",
                        "Payment Method Required",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void styleRadioButton(JRadioButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(TEXT_DARK);
        button.setBackground(CREAM);
        button.setFocusPainted(false);
    }

    private void showDemoConfirmation(String method) {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Amount: ₹" + String.format("%.2f", totalAmount)
                        + "\nMethod: " + method
                        + "\n\nThis is a demonstration only."
                        + "\nNo real payment will be processed."
                        + "\n\nContinue with the demo order?",
                "Confirm Demo Order",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            showConfirmation(method, "Demo");
        }
    }

    private void showConfirmation(String method, String paymentStatus) {

        JOptionPane.showMessageDialog(
                this,
                "FOODLINK ORDER CONFIRMED\n\n"
                        + "Amount: ₹"
                        + String.format("%.2f", totalAmount)
                        + "\nPayment method: " + method
                        + "\nStatus: "
                        + (paymentStatus.equals("Pending")
                                ? "Payment pending"
                                : "Demo confirmation only")
                        + "\n\nThank you for choosing FoodLink!",
                "Order Confirmation",
                JOptionPane.INFORMATION_MESSAGE
        );

        statusLabel.setText("Confirmation displayed successfully.");
        payButton.setEnabled(false);
    }
}