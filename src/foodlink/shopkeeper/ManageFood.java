package foodlink.shopkeeper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import foodlink.model.FoodItem;
import foodlink.service.ShopkeeperService;

public class ManageFood extends JFrame {

    private JTable foodTable;
    private DefaultTableModel tableModel;
    private ArrayList<FoodItem> foodItems;
    private long shopId;

    public ManageFood(long shopId) {

        this.shopId = shopId;

        setTitle("FoodLink - Manage Food");
        setSize(900, 570);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("MANAGE FOOD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(30, 20, 300, 40);
        add(title);

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Food ID",
                        "Food Name",
                        "Price (₹)",
                        "Shop",
                        "Available",
                        "Stock Quantity"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        foodTable = new JTable(tableModel);
        foodTable.setRowHeight(25);
        foodTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(foodTable);
        scrollPane.setBounds(30, 80, 820, 320);
        add(scrollPane);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(100, 430, 120, 45);
        add(editButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBounds(240, 430, 120, 45);
        add(deleteButton);

        JButton refreshButton = new JButton("REFRESH");
        refreshButton.setBounds(380, 430, 120, 45);
        add(refreshButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(520, 430, 120, 45);
        add(backButton);

        editButton.addActionListener(e -> editSelectedFood());
        deleteButton.addActionListener(e -> deleteSelectedFood());
        refreshButton.addActionListener(e -> loadFoodItems());
        backButton.addActionListener(e -> dispose());

        loadFoodItems();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadFoodItems() {

        foodItems = ShopkeeperService.getFoodItems(shopId);
        tableModel.setRowCount(0);

        for (FoodItem food : foodItems) {
            tableModel.addRow(new Object[]{
                    food.getFoodId(),
                    food.getName(),
                    food.getPrice(),
                    food.getShopName(),
                    food.isAvailable() ? "Yes" : "No",
                    food.getStockQuantity()
            });
        }
    }

    private void editSelectedFood() {

        int selectedRow = foodTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Please select a food item.");
            return;
        }

        int modelRow = foodTable.convertRowIndexToModel(selectedRow);
        FoodItem selectedFood = foodItems.get(modelRow);

        new EditFood(selectedFood);
        dispose();
    }

    private void deleteSelectedFood() {

        int selectedRow = foodTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Please select a food item.");
            return;
        }

        int modelRow = foodTable.convertRowIndexToModel(selectedRow);
        FoodItem selectedFood = foodItems.get(modelRow);

        int answer = JOptionPane.showConfirmDialog(
                this,
                "Delete " + selectedFood.getName() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            boolean deleted = ShopkeeperService.deleteFood(
                    selectedFood.getFoodId());

            if (deleted) {
                JOptionPane.showMessageDialog(
                        this, "Food deleted successfully.");
                loadFoodItems();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Food could not be deleted.");
            }
        }
    }
}
