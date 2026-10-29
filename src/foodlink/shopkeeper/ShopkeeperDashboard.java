
package foodlink.shopkeeper;

import foodlink.service.DatabaseConnection;
import foodlink.service.ShopkeeperService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.math.BigDecimal;

public class ShopkeeperDashboard extends JFrame {

    private final long shopId;

    private final Color darkGreen = new Color(24, 59, 46);
    private final Color orange = new Color(246, 77, 32);
    private final Color cream = new Color(255, 249, 235);
    private final Color white = Color.WHITE;
    private final Color textColor = new Color(48, 57, 51);

    private JPanel contentPanel;

    public ShopkeeperDashboard(long shopId) {
        this.shopId = shopId;

        setTitle("FoodLink - Shopkeeper Dashboard");
        setSize(1200, 750);
        setMinimumSize(new Dimension(950, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(cream);

        add(createTopBar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(cream);
        contentPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
        add(contentPanel, BorderLayout.CENTER);

        showDashboard();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(white);
        bar.setBorder(new EmptyBorder(15, 22, 15, 22));

        JLabel logo = new JLabel("FoodLink  |  SHOPKEEPER PORTAL");
        logo.setFont(new Font("Arial", Font.BOLD, 23));
        logo.setForeground(darkGreen);

        JButton refresh = new JButton("Refresh");
        styleButton(refresh);
        refresh.addActionListener(e -> showDashboard());

        bar.add(logo, BorderLayout.WEST);
        bar.add(refresh, BorderLayout.EAST);
        return bar;
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(darkGreen);
        sidebar.setPreferredSize(new Dimension(205, 0));
        sidebar.setBorder(new EmptyBorder(22, 12, 20, 12));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        addMenu(sidebar, "Dashboard", this::showDashboard);
        addMenu(sidebar, "Add Food", () -> new AddFood(shopId));
        addMenu(sidebar, "Manage Food", () -> new ManageFood(shopId));
        addMenu(sidebar, "Customer Orders", this::showOrders);
        addMenu(sidebar, "Offers", this::showOffers);
        addMenu(sidebar, "Announcements", this::showAnnouncements);

        sidebar.add(Box.createVerticalGlue());
        addMenu(sidebar, "Logout", this::logout);

        return sidebar;
    }

    private void addMenu(JPanel sidebar, String title, Runnable action) {
        JButton button = new JButton(title);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setForeground(white);
        button.setBackground(darkGreen);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(new EmptyBorder(12, 14, 12, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> action.run());
        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(5));
    }

    private void styleButton(JButton button) {
        button.setBackground(orange);
        button.setForeground(white);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JLabel heading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setForeground(darkGreen);
        return label;
    }

    private void displayPage(String title, JComponent component) {
        contentPanel.removeAll();
        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setBackground(cream);
        page.add(heading(title), BorderLayout.NORTH);
        page.add(component, BorderLayout.CENTER);
        contentPanel.add(page, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDashboard() {
        JPanel page = new JPanel();
        page.setBackground(cream);
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));

        JLabel title = heading("Shopkeeper Dashboard");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        page.add(title);

        JLabel subtitle = new JLabel(
            "Overview of your shop's current database records."
        );
        subtitle.setForeground(Color.GRAY);
        subtitle.setBorder(new EmptyBorder(8, 0, 20, 0));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        page.add(subtitle);

        JPanel cards = new JPanel(new GridLayout(0, 2, 14, 14));
        cards.setBackground(cream);
        cards.setAlignmentX(Component.LEFT_ALIGNMENT);

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) {
                throw new SQLException("Database connection is unavailable.");
            }

            int totalFood = count(con,
                "SELECT COUNT(*) FROM food_items WHERE shop_id = ?", shopId);

            int availableFood = count(con,
                "SELECT COUNT(*) FROM food_items WHERE shop_id = ? AND available = TRUE",
                shopId);

            int totalOrders = count(con,
                "SELECT COUNT(*) FROM customer_orders WHERE shop_id = ?", shopId);

            int pendingOrders = count(con,
                "SELECT COUNT(*) FROM customer_orders WHERE shop_id = ? AND UPPER(status) = 'PENDING'",
                shopId);

            BigDecimal revenue = money(con,
                "SELECT COALESCE(SUM(total), 0) FROM customer_orders " +
                "WHERE shop_id = ? AND UPPER(status) IN ('COMPLETED', 'DELIVERED')",
                shopId);

            cards.add(createStatCard("Total Food Items", String.valueOf(totalFood)));
            cards.add(createStatCard("Available Food", String.valueOf(availableFood)));
            cards.add(createStatCard("Customer Orders", String.valueOf(totalOrders)));
            cards.add(createStatCard("Pending Orders", String.valueOf(pendingOrders)));
            cards.add(createStatCard("Completed/Delivered Revenue",
                "₹" + revenue.toPlainString()));

        } catch (SQLException ex) {
            cards.add(createStatCard("Database Status", "Could not load"));
            showError(ex);
        }

        JScrollPane scroll = new JScrollPane(cards);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(cream);
        page.add(scroll);

        contentPanel.removeAll();
        contentPanel.add(page, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private int count(Connection con, String sql, long id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    private BigDecimal money(Connection con, String sql, long id)
            throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getBigDecimal(1) : BigDecimal.ZERO;
            }
        }
    }

    private JPanel createStatCard(String name, String value) {
        JPanel card = new JPanel();
        card.setBackground(white);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 225, 215)),
            new EmptyBorder(22, 22, 22, 22)
        ));

        JLabel label = new JLabel(name);
        label.setForeground(textColor);
        label.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel number = new JLabel(value);
        number.setForeground(orange);
        number.setFont(new Font("Arial", Font.BOLD, 25));
        number.setBorder(new EmptyBorder(12, 0, 0, 0));

        card.add(label);
        card.add(number);
        return card;
    }

    private JTable createTable(String... columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(27);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setBackground(darkGreen);
        table.getTableHeader().setForeground(white);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        return table;
    }

    private DefaultTableModel modelOf(JTable table) {
        return (DefaultTableModel) table.getModel();
    }

    private void showOrders() {
        JTable table = createTable(
            "Order ID", "Customer", "Phone", "Items", "Total", "Payment", "Status"
        );

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) throw new SQLException("Database unavailable.");

            String sql =
                "SELECT order_id, customer_name, phone, items, total, " +
                "payment_method, status FROM customer_orders " +
                "WHERE shop_id = ? ORDER BY created_at DESC";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, shopId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        modelOf(table).addRow(new Object[] {
                            rs.getLong("order_id"),
                            rs.getString("customer_name"),
                            rs.getString("phone"),
                            rs.getString("items"),
                            rs.getBigDecimal("total"),
                            rs.getString("payment_method"),
                            rs.getString("status")
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            showError(ex);
        }

        JButton update = new JButton("Update Selected Order Status");
        styleButton(update);
        update.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select an order first.");
                return;
            }

            long orderId = Long.parseLong(table.getValueAt(row, 0).toString());
            String[] statuses = {
                "PENDING", "CONFIRMED", "PREPARING",
                "READY", "COMPLETED", "DELIVERED", "CANCELLED"
            };

            String selected = (String) JOptionPane.showInputDialog(
                this, "Choose the new status:", "Update Order",
                JOptionPane.PLAIN_MESSAGE, null, statuses, statuses[0]
            );

            if (selected == null) return;

            String sql =
                "UPDATE customer_orders SET status = ? " +
                "WHERE order_id = ? AND shop_id = ?";

            try (Connection con = DatabaseConnection.getConnection()) {
                if (con == null) throw new SQLException("Database unavailable.");

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, selected);
                    ps.setLong(2, orderId);
                    ps.setLong(3, shopId);

                    if (ps.executeUpdate() > 0) {
                        showOrders();
                    } else {
                        JOptionPane.showMessageDialog(this, "Order was not updated.");
                    }
                }
            } catch (SQLException ex) {
                showError(ex);
            }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBackground(cream);
        bottom.add(update);

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(cream);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        displayPage("Customer Orders", panel);
    }

    private void showOffers() {
        JTable table = createTable(
            "Offer ID", "Title", "Description", "Discount %", "Active"
        );
        loadOffers(table);

        JButton add = new JButton("Create Offer");
        JButton toggle = new JButton("Activate/Deactivate");
        styleButton(add);
        styleButton(toggle);

        add.addActionListener(e -> {
            JTextField title = new JTextField();
            JTextField description = new JTextField();
            JTextField discount = new JTextField();

            Object[] fields = {
                "Offer title:", title,
                "Description:", description,
                "Discount percentage (1–100):", discount
            };

            if (JOptionPane.showConfirmDialog(
                this, fields, "Create Offer",
                JOptionPane.OK_CANCEL_OPTION
            ) != JOptionPane.OK_OPTION) return;

            try {
                String offerTitle = title.getText().trim();
                String offerDescription = description.getText().trim();
                BigDecimal percent = new BigDecimal(discount.getText().trim());

                if (offerTitle.isEmpty() || percent.compareTo(BigDecimal.ZERO) <= 0
                        || percent.compareTo(new BigDecimal("100")) > 0) {
                    throw new IllegalArgumentException(
                        "Enter a title and a discount greater than 0 and at most 100."
                    );
                }

                String sql =
                    "INSERT INTO offers (shop_id, title, description, discount_percent) " +
                    "VALUES (?, ?, ?, ?)";

                try (Connection con = DatabaseConnection.getConnection()) {
                    if (con == null) throw new SQLException("Database unavailable.");

                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setLong(1, shopId);
                        ps.setString(2, offerTitle);
                        ps.setString(3, offerDescription);
                        ps.setBigDecimal(4, percent);
                        ps.executeUpdate();
                    }
                }

                loadOffers(table);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Could not create offer", JOptionPane.ERROR_MESSAGE);
            }
        });

        toggle.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select an offer first.");
                return;
            }

            long id = Long.parseLong(table.getValueAt(row, 0).toString());
            boolean currentlyActive = Boolean.parseBoolean(
                table.getValueAt(row, 4).toString()
            );

            String sql =
                "UPDATE offers SET active = ? WHERE offer_id = ? AND shop_id = ?";

            try (Connection con = DatabaseConnection.getConnection()) {
                if (con == null) throw new SQLException("Database unavailable.");

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setBoolean(1, !currentlyActive);
                    ps.setLong(2, id);
                    ps.setLong(3, shopId);
                    ps.executeUpdate();
                }

                loadOffers(table);
            } catch (SQLException ex) {
                showError(ex);
            }
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(cream);
        buttons.add(add);
        buttons.add(toggle);

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(cream);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        displayPage("Offers & Discounts", panel);
    }

    private void loadOffers(JTable table) {
        modelOf(table).setRowCount(0);

        String sql =
            "SELECT offer_id, title, description, discount_percent, active " +
            "FROM offers WHERE shop_id = ? ORDER BY created_at DESC";

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) throw new SQLException("Database unavailable.");

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, shopId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        modelOf(table).addRow(new Object[] {
                            rs.getLong("offer_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getBigDecimal("discount_percent"),
                            rs.getBoolean("active")
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            showError(ex);
        }
    }

    private void showAnnouncements() {
        JTable table = createTable(
            "Announcement ID", "Title", "Message", "Active", "Created At"
        );
        loadAnnouncements(table);

        JButton add = new JButton("Publish Announcement");
        JButton toggle = new JButton("Activate/Deactivate");
        styleButton(add);
        styleButton(toggle);

        add.addActionListener(e -> {
            JTextField title = new JTextField();
            JTextArea message = new JTextArea(5, 25);
            message.setLineWrap(true);
            message.setWrapStyleWord(true);

            Object[] fields = {
                "Announcement title:", title,
                "Message:", new JScrollPane(message)
            };

            if (JOptionPane.showConfirmDialog(
                this, fields, "Publish Announcement",
                JOptionPane.OK_CANCEL_OPTION
            ) != JOptionPane.OK_OPTION) return;

            String announcementTitle = title.getText().trim();
            String announcementMessage = message.getText().trim();

            if (announcementTitle.isEmpty() || announcementMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Title and message are required.");
                return;
            }

            String sql =
                "INSERT INTO announcements (shop_id, title, message) VALUES (?, ?, ?)";

            try (Connection con = DatabaseConnection.getConnection()) {
                if (con == null) throw new SQLException("Database unavailable.");

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setLong(1, shopId);
                    ps.setString(2, announcementTitle);
                    ps.setString(3, announcementMessage);
                    ps.executeUpdate();
                }

                loadAnnouncements(table);
            } catch (SQLException ex) {
                showError(ex);
            }
        });

        toggle.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select an announcement first.");
                return;
            }

            long id = Long.parseLong(table.getValueAt(row, 0).toString());
            boolean active = Boolean.parseBoolean(
                table.getValueAt(row, 3).toString()
            );

            String sql =
                "UPDATE announcements SET active = ? " +
                "WHERE announcement_id = ? AND shop_id = ?";

            try (Connection con = DatabaseConnection.getConnection()) {
                if (con == null) throw new SQLException("Database unavailable.");

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setBoolean(1, !active);
                    ps.setLong(2, id);
                    ps.setLong(3, shopId);
                    ps.executeUpdate();
                }

                loadAnnouncements(table);
            } catch (SQLException ex) {
                showError(ex);
            }
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(cream);
        buttons.add(add);
        buttons.add(toggle);

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(cream);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        displayPage("Shop Announcements", panel);
    }

    private void loadAnnouncements(JTable table) {
        modelOf(table).setRowCount(0);

        String sql =
            "SELECT announcement_id, title, message, active, created_at " +
            "FROM announcements WHERE shop_id = ? ORDER BY created_at DESC";

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) throw new SQLException("Database unavailable.");

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, shopId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        modelOf(table).addRow(new Object[] {
                            rs.getLong("announcement_id"),
                            rs.getString("title"),
                            rs.getString("message"),
                            rs.getBoolean("active"),
                            rs.getTimestamp("created_at")
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            showError(ex);
        }
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void showError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            this,
            "Unable to complete the database operation.\n" + ex.getMessage(),
            "FoodLink Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
