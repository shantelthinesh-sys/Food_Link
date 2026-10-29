package foodlink.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import foodlink.model.Customer;
import foodlink.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerProfile extends JDialog {

    private Customer customer;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;

    private final Color GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color TEXT = new Color(38, 55, 45);
    private final Color BORDER = new Color(229, 222, 207);

    public CustomerProfile(Customer customer) {

        this.customer = customer;

        setTitle("FoodLink - My Profile");

        setSize(560, 620);

        setLocationRelativeTo(null);

        setModal(true);

        setLayout(new BorderLayout());

        getContentPane().setBackground(CREAM);

        createHeader();

        createProfileForm();

        createBottomButtons();

        loadCustomerDetails();

        setVisible(true);
    }

    private void createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(DARK_GREEN);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        22, 28, 22, 28
                )
        );

        JLabel title = new JLabel("My Profile");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        JLabel subtitle =
                new JLabel("Manage your FoodLink account");

        subtitle.setForeground(
                new Color(220, 235, 220)
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        textPanel.setOpaque(false);

        textPanel.add(title);

        textPanel.add(subtitle);

        header.add(
                textPanel,
                BorderLayout.CENTER
        );

        add(
                header,
                BorderLayout.NORTH
        );
    }

    private void createProfileForm() {

        JPanel outer =
                new JPanel(
                        new BorderLayout()
                );

        outer.setOpaque(false);

        outer.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        15,
                        35
                )
        );

        JPanel form =
                new JPanel(
                        new GridLayout(
                                6,
                                1,
                                0,
                                8
                        )
                );

        form.setOpaque(false);

        JLabel accountLabel =
                new JLabel("ACCOUNT INFORMATION");

        accountLabel.setForeground(GREEN);

        accountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        form.add(accountLabel);

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setForeground(TEXT);

        JTextField usernameField =
                new JTextField();

        usernameField.setText(
                customer.getUsername()
        );

        usernameField.setEditable(false);

        styleField(usernameField);

        form.add(
                createFieldPanel(
                        usernameLabel,
                        usernameField
                )
        );

        JLabel nameLabel =
                new JLabel("Full Name");

        nameField =
                new JTextField();

        styleField(nameField);

        form.add(
                createFieldPanel(
                        nameLabel,
                        nameField
                )
        );

        JLabel phoneLabel =
                new JLabel("Phone Number");

        phoneField =
                new JTextField();

        styleField(phoneField);

        form.add(
                createFieldPanel(
                        phoneLabel,
                        phoneField
                )
        );

        JLabel emailLabel =
                new JLabel("Email Address");

        emailField =
                new JTextField();

        styleField(emailField);

        form.add(
                createFieldPanel(
                        emailLabel,
                        emailField
                )
        );

        outer.add(
                form,
                BorderLayout.NORTH
        );

        add(
                outer,
                BorderLayout.CENTER
        );
    }

    private JPanel createFieldPanel(
            JLabel label,
            JTextField field) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        panel.setOpaque(false);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT);

        panel.add(
                label,
                BorderLayout.NORTH
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    private void styleField(
            JTextField field) {

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        field.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        field.setBackground(Color.WHITE);
    }

    private void createBottomButtons() {

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                15
                        )
                );

        bottom.setOpaque(false);

        JButton cancel =
                new JButton("CLOSE");

        cancel.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        cancel.addActionListener(
                e -> dispose()
        );

        JButton save =
                new JButton("SAVE CHANGES");

        save.setPreferredSize(
                new Dimension(
                        150,
                        38
                )
        );

        save.setBackground(GREEN);

        save.setForeground(Color.WHITE);

        save.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        save.setFocusPainted(false);

        save.addActionListener(
                e -> saveDetails()
        );

        bottom.add(cancel);

        bottom.add(save);

        add(
                bottom,
                BorderLayout.SOUTH
        );
    }

    private void loadCustomerDetails() {

        String sql =
                "SELECT name, phone, email " +
                "FROM customers " +
                "WHERE username = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    customer.getUsername()
            );

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                nameField.setText(
                        result.getString("name")
                );

                phoneField.setText(
                        result.getString("phone") == null
                                ? ""
                                : result.getString("phone")
                );

                emailField.setText(
                        result.getString("email") == null
                                ? ""
                                : result.getString("email")
                );
            }

            result.close();

            statement.close();

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void saveDetails() {

        String name =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Name cannot be empty."
            );

            return;
        }

        String sql =
                "UPDATE customers " +
                "SET name = ?, phone = ?, email = ? " +
                "WHERE username = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);

            statement.setString(2, phone);

            statement.setString(3, email);

            statement.setString(
                    4,
                    customer.getUsername()
            );

            statement.executeUpdate();

            statement.close();

            connection.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Profile updated successfully!"
            );

            dispose();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update profile."
            );
        }
    }
}