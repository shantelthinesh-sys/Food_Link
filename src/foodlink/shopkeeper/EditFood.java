package foodlink.shopkeeper;

import javax.swing.*;
import java.awt.*;

import foodlink.model.FoodItem;
import foodlink.service.ShopkeeperService;

public class EditFood extends JFrame {

    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JCheckBox availableBox;

    private FoodItem foodItem;

    public EditFood(FoodItem foodItem) {

        this.foodItem = foodItem;

        setTitle("FoodLink - Edit Food");
        setSize(500, 460);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("EDIT FOOD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(40, 25, 300, 40);
        add(title);

        JLabel nameLabel = new JLabel("Food Name:");
        nameLabel.setBounds(40, 90, 120, 30);
        add(nameLabel);

        nameField = new JTextField(foodItem.getName());
        nameField.setBounds(170, 90, 240, 30);
        add(nameField);

        JLabel priceLabel = new JLabel("Price (₹):");
        priceLabel.setBounds(40, 140, 120, 30);
        add(priceLabel);

        priceField = new JTextField(
                String.valueOf(foodItem.getPrice()));
        priceField.setBounds(170, 140, 240, 30);
        add(priceField);

        JLabel stockLabel = new JLabel("Stock Quantity:");
        stockLabel.setBounds(40, 190, 120, 30);
        add(stockLabel);

        stockField = new JTextField(
                String.valueOf(foodItem.getStockQuantity()));
        stockField.setBounds(170, 190, 240, 30);
        add(stockField);

        availableBox = new JCheckBox("Available");
        availableBox.setBounds(170, 235, 150, 30);
        availableBox.setSelected(foodItem.isAvailable());
        add(availableBox);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(90, 310, 140, 45);
        add(updateButton);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setBounds(250, 310, 140, 45);
        add(cancelButton);

        updateButton.addActionListener(e -> {

            String foodName = nameField.getText().trim();
            String priceText = priceField.getText().trim();
            String stockText = stockField.getText().trim();

            if (foodName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter food name.");
                return;
            }

            if (priceText.isEmpty() || stockText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter price and stock quantity.");
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

                boolean available = availableBox.isSelected();

                boolean updated = ShopkeeperService.updateFood(
                        foodItem.getFoodId(),
                        foodName,
                        price,
                        available,
                        stockQuantity
                );

                if (updated) {
                    JOptionPane.showMessageDialog(
                            this, "Food updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            this, "Food update failed.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid price and whole-number stock quantity.");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
