
package foodlink.gui;

import foodlink.model.Customer;
import foodlink.service.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class LoginPage extends JFrame {

    private final Color GREEN = new Color(46, 125, 50);
    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color BORDER = new Color(225, 229, 220);

    private CardLayout formLayout;
    private JPanel formPanel;

    private JButton signInTab;
    private JButton signUpTab;

    private JComboBox<String> loginRole;
    private JTextField loginUsername;
    private JPasswordField loginPassword;

    private JComboBox<String> signupRole;
    private JTextField signupName;
    private JTextField signupUsername;
    private JTextField signupEmail;
    private JPasswordField signupPassword;
    private JPasswordField confirmPassword;
    private JComboBox<ShopOption> shopCombo;

    private JPanel emailRow;
    private JPanel shopRow;

    public LoginPage() {
        setTitle("FoodLink | Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 720);
        setMinimumSize(new Dimension(430, 600));
        setLocationRelativeTo(null);

        JPanel background = new JPanel(new GridBagLayout());
        background.setBackground(CREAM);

        JPanel card = new JPanel(new BorderLayout(0, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(24, 30, 25, 30)
        ));
        card.setPreferredSize(new Dimension(390, 610));

        // FoodLink branding
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("FoodLink");
        logo.setFont(new Font("SansSerif", Font.BOLD, 36));
        logo.setForeground(DARK_GREEN);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Good food. Easy ordering.");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(GREEN);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(logo);
        header.add(Box.createVerticalStrut(5));
        header.add(tagline);
        header.add(Box.createVerticalStrut(22));

        // Both options remain visible
        JPanel tabs = new JPanel(new GridLayout(1, 2, 8, 0));
        tabs.setBackground(Color.WHITE);

        signInTab = createTab("Sign In");
        signUpTab = createTab("Sign Up");

        tabs.add(signInTab);
        tabs.add(signUpTab);

        header.add(tabs);
        header.add(Box.createVerticalStrut(20));

        // Switch forms inside the same card
        formLayout = new CardLayout();
        formPanel = new JPanel(formLayout);
        formPanel.setBackground(Color.WHITE);

        formPanel.add(createLoginForm(), "LOGIN");
        formPanel.add(createSignupForm(), "SIGNUP");

        signInTab.addActionListener(e -> showForm("LOGIN"));
        signUpTab.addActionListener(e -> {
            refreshShops();
            showForm("SIGNUP");
        });

        card.add(header, BorderLayout.NORTH);
        card.add(formPanel, BorderLayout.CENTER);

        background.add(card);
        setContentPane(background);

        showForm("LOGIN");
        setVisible(true);
    }

    // ================= TAB DESIGN =================

    private JButton createTab(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private void showForm(String form) {
        formLayout.show(formPanel, form);

        boolean login = form.equals("LOGIN");

        signInTab.setBackground(login ? GREEN : new Color(240, 243, 236));
        signInTab.setForeground(login ? Color.WHITE : DARK_GREEN);

        signUpTab.setBackground(login ? new Color(240, 243, 236) : ORANGE);
        signUpTab.setForeground(login ? DARK_GREEN : Color.WHITE);

        revalidate();
        repaint();
    }

    // ================= SIGN IN FORM =================

    private JPanel createLoginForm() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Welcome back!");
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(DARK_GREEN);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel("Sign in to continue to FoodLink.");
        description.setFont(new Font("SansSerif", Font.PLAIN, 13));
        description.setForeground(Color.GRAY);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginRole = new JComboBox<>(
                new String[]{"Customer", "Shopkeeper"});
        loginUsername = new JTextField();
        loginPassword = new JPasswordField();

        styleField(loginRole);
        styleField(loginUsername);
        styleField(loginPassword);

        JCheckBox showPassword = new JCheckBox("Show password");
        showPassword.setBackground(Color.WHITE);
        showPassword.setForeground(DARK_GREEN);
        showPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        showPassword.addActionListener(e ->
                loginPassword.setEchoChar(
                        showPassword.isSelected() ? (char) 0 : '\u2022'
                ));

        JButton loginButton = createButton("SIGN IN", GREEN);

        loginButton.addActionListener(e -> signIn());

        panel.add(heading);
        panel.add(Box.createVerticalStrut(5));
        panel.add(description);
        panel.add(Box.createVerticalStrut(22));

        panel.add(createFieldRow("Account type", loginRole));
        panel.add(createFieldRow("Username", loginUsername));
        panel.add(createFieldRow("Password", loginPassword));

        panel.add(showPassword);
        panel.add(Box.createVerticalStrut(22));
        panel.add(loginButton);

        return panel;
    }

    // ================= SIGN UP FORM =================

    private JPanel createSignupForm() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Create your account");
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(DARK_GREEN);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel("Enter your details to get started.");
        description.setFont(new Font("SansSerif", Font.PLAIN, 13));
        description.setForeground(Color.GRAY);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        signupRole = new JComboBox<>(
                new String[]{"Customer", "Shopkeeper"});
        signupName = new JTextField();
        signupUsername = new JTextField();
        signupEmail = new JTextField();
        signupPassword = new JPasswordField();
        confirmPassword = new JPasswordField();
        shopCombo = new JComboBox<>();

        styleField(signupRole);
        styleField(signupName);
        styleField(signupUsername);
        styleField(signupEmail);
        styleField(signupPassword);
        styleField(confirmPassword);
        styleField(shopCombo);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(5));
        panel.add(description);
        panel.add(Box.createVerticalStrut(15));

        panel.add(createFieldRow("Account type", signupRole));
        panel.add(createFieldRow("Full name", signupName));
        panel.add(createFieldRow("Create username", signupUsername));
        panel.add(createFieldRow("Create password", signupPassword));
        panel.add(createFieldRow("Confirm password", confirmPassword));

        emailRow = createFieldRow("Email (optional)", signupEmail);
        shopRow = createFieldRow("Choose your shop", shopCombo);

        panel.add(emailRow);
        panel.add(shopRow);

        JButton createButton = createButton("CREATE ACCOUNT", GREEN);

        createButton.addActionListener(e -> signUp());

        panel.add(Box.createVerticalStrut(8));
        panel.add(createButton);
        panel.add(Box.createVerticalStrut(15));

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(Color.WHITE);

        outer.add(scroll, BorderLayout.CENTER);

        signupRole.addActionListener(e -> updateSignupFields());
        updateSignupFields();

        return outer;
    }

    // ================= FORM COMPONENTS =================

    private JPanel createFieldRow(String label, JComponent field) {
        JPanel row = new JPanel();
        row.setBackground(Color.WHITE);
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel(label);
        title.setFont(new Font("SansSerif", Font.BOLD, 13));
        title.setForeground(DARK_GREEN);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(300, 36));
        field.setMinimumSize(new Dimension(100, 36));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        row.add(title);
        row.add(Box.createVerticalStrut(5));
        row.add(field);
        row.add(Box.createVerticalStrut(12));

        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

        return row;
    }

    private void styleField(JComponent field) {
        field.setBackground(Color.WHITE);
        field.setForeground(DARK_GREEN);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(5, 9, 5, 9)
        ));
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(300, 43));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        return button;
    }

    // ================= ROLE-BASED FIELDS =================

    private void updateSignupFields() {
        if (signupRole == null || emailRow == null || shopRow == null) {
            return;
        }

        boolean shopkeeper =
                "Shopkeeper".equals(signupRole.getSelectedItem());

        emailRow.setVisible(!shopkeeper);
        shopRow.setVisible(shopkeeper);

        formPanel.revalidate();
        formPanel.repaint();
    }

    // ================= LOAD SHOPS =================

    private void refreshShops() {
        if (shopCombo == null) {
            return;
        }

        shopCombo.removeAllItems();

        String sql =
                "SELECT shop_id, shop_name FROM shops ORDER BY shop_name";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                shopCombo.addItem(new ShopOption(
                        rs.getLong("shop_id"),
                        rs.getString("shop_name")
                ));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not load shops.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= SIGN UP ACTION =================

    private void signUp() {
        String role = (String) signupRole.getSelectedItem();
        String name = signupName.getText().trim();
        String username = signupUsername.getText().trim();
        String password = new String(signupPassword.getPassword());
        String confirm = new String(confirmPassword.getPassword());
        String email = signupEmail.getText().trim();

        if (name.isEmpty() || username.isEmpty()
                || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please complete all required fields.");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this,
                    "The passwords do not match.");
            return;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this,
                    "Your password must have at least 6 characters.");
            return;
        }

        if ("Customer".equals(role)
                && !email.isEmpty()
                && !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address.");
            return;
        }

        if ("Shopkeeper".equals(role) && shopCombo.getItemCount() == 0) {
            refreshShops();
        }

        String table = "Customer".equals(role)
                ? "customers" : "shopkeepers";

        String checkSql =
                "SELECT 1 FROM " + table + " WHERE username = ?";

        try (Connection con = DatabaseConnection.getConnection()) {

            try (PreparedStatement check =
                         con.prepareStatement(checkSql)) {

                check.setString(1, username);

                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this,
                                "That username is already taken.");
                        return;
                    }
                }
            }

            if ("Customer".equals(role)) {
                String sql =
                        "INSERT INTO customers "
                        + "(username, password, name, email) "
                        + "VALUES (?, ?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, name);

                    if (email.isEmpty()) {
                        ps.setNull(4, Types.VARCHAR);
                    } else {
                        ps.setString(4, email);
                    }

                    ps.executeUpdate();
                }

            } else {
                ShopOption shop = (ShopOption) shopCombo.getSelectedItem();

                if (shop == null) {
                    JOptionPane.showMessageDialog(this,
                            "No shop is available. Please add a shop first.");
                    return;
                }

                String sql =
                        "INSERT INTO shopkeepers "
                        + "(username, password, name, shop_id) "
                        + "VALUES (?, ?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, name);
                    ps.setLong(4, shop.id);
                    ps.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Your account has been created successfully!\n"
                    + "You can now sign in.");

            loginRole.setSelectedItem(role);
            loginUsername.setText(username);
            loginPassword.setText("");

            clearSignupFields();
            showForm("LOGIN");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not create your account.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearSignupFields() {
        signupName.setText("");
        signupUsername.setText("");
        signupEmail.setText("");
        signupPassword.setText("");
        confirmPassword.setText("");
    }

    // ================= SIGN IN ACTION =================

    private void signIn() {
        String role = (String) loginRole.getSelectedItem();
        String username = loginUsername.getText().trim();
        String password = new String(loginPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your username and password.");
            return;
        }

        String table = "Customer".equals(role)
                ? "customers" : "shopkeepers";

        String sql =
                "SELECT * FROM " + table + " WHERE username = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this,
                            "Account not found. Please sign up first.");
                    return;
                }

                String savedPassword = rs.getString("password");

                if (!savedPassword.equals(password)) {
                    JOptionPane.showMessageDialog(this,
                            "Incorrect password.");
                    return;
                }

                String name = rs.getString("name");

                if ("Customer".equals(role)) {
                    Customer customer =
                            new Customer(username, savedPassword, name);

                    dispose();

                    SwingUtilities.invokeLater(() ->
                            new CustomerHome(customer));

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Welcome, " + name + "!\n"
                            + "Shopkeeper authentication succeeded. "
                            + "Connect your teammate's dashboard here.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Login failed.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class ShopOption {
        private final long id;
        private final String name;

        ShopOption(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}