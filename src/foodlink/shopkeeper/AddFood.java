package foodlink.shopkeeper;

import javax.swing.*;
import java.awt.*;

import foodlink.service.ShopkeeperService;

public class AddFood extends JFrame {

    private final long shopId;

    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JCheckBox availableBox;

    public AddFood(long shopId) {

        this.shopId = shopId;

        setTitle("FoodLink - Add Food");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        mainPanel.setBackground(new Color(255, 249, 235));

        JLabel title = new JLabel("ADD FOOD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(24, 59, 46));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 8, 12, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 16);

        nameField = new JTextField(20);
        priceField = new JTextField(20);
        stockField = new JTextField("0", 20);
        availableBox = new JCheckBox("Available", true);
        availableBox.setOpaque(false);
        availableBox.setFont(labelFont);

        addFormRow(formPanel, gbc, 0, "Food Name:", nameField, labelFont);
        addFormRow(formPanel, gbc, 1, "Price (₹):", priceField, labelFont);
        addFormRow(formPanel, gbc, 2, "Stock Quantity:", stockField, labelFont);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        formPanel.add(availableBox, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("ADD FOOD");
        JButton cancelButton = new JButton("CANCEL");

        addButton.setBackground(new Color(246, 77, 32));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        addButton.addActionListener(e -> addFood());
        cancelButton.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(addButton);

        pack();
        setMinimumSize(new Dimension(480, 380));
        setSize(600, 430);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JTextField field,
            Font labelFont) {

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void addFood() {

        String foodName = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String stockText = stockField.getText().trim();

        if (foodName.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stockQuantity = Integer.parseInt(stockText);

            if (!Double.isFinite(price) || price < 0) {
                JOptionPane.showMessageDialog(
                        this, "Enter a valid non-negative price.");
                return;
            }

            if (stockQuantity < 0) {
                JOptionPane.showMessageDialog(
                        this, "Stock quantity cannot be negative.");
                return;
            }

            boolean added = ShopkeeperService.addFood(
                    shopId,
                    foodName,
                    price,
                    availableBox.isSelected(),
                    stockQuantity
            );

            if (added) {
                JOptionPane.showMessageDialog(
                        this, "Food added successfully!");

                nameField.setText("");
                priceField.setText("");
                stockField.setText("0");
                availableBox.setSelected(true);
                nameField.requestFocusInWindow();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Food could not be added. Check the database connection and shop ID.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid price and a whole-number stock quantity.");
        }
    }
}
