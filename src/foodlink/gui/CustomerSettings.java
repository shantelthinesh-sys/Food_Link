package foodlink.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import foodlink.model.Customer;
import foodlink.service.DatabaseConnection;

public class CustomerSettings extends JDialog {

    private Customer customer;

    private JComboBox<String> languageBox;
    private JToggleButton notificationButton;

    private final Color GREEN =
            new Color(46, 125, 50);

    private final Color ORANGE =
            new Color(244, 81, 30);

    private final Color CREAM =
            new Color(255, 248, 231);

    private final Color DARK_GREEN =
            new Color(24, 57, 43);

    private final Color TEXT =
            new Color(38, 55, 45);

    private final Color MUTED =
            new Color(105, 120, 110);

    private final Color BORDER =
            new Color(229, 222, 207);

    public CustomerSettings(Customer customer) {

        this.customer = customer;

        setTitle("FoodLink - Settings");

        setSize(560, 520);

        setLocationRelativeTo(null);

        setModal(true);

        setLayout(
                new BorderLayout()
        );

        getContentPane()
                .setBackground(CREAM);

        createHeader();

        createSettings();

        createBottomButtons();

        loadSettings();

        setVisible(true);
    }

    private void createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(DARK_GREEN);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        28,
                        22,
                        28
                )
        );

        JLabel title =
                new JLabel("Settings");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Personalize your FoodLink experience"
                );

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

        JPanel text =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        text.setOpaque(false);

        text.add(title);

        text.add(subtitle);

        header.add(
                text,
                BorderLayout.CENTER
        );

        add(
                header,
                BorderLayout.NORTH
        );
    }

    private void createSettings() {

        JPanel outer =
                new JPanel(
                        new BorderLayout()
                );

        outer.setOpaque(false);

        outer.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        10,
                        35
                )
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                15
                        )
                );

        panel.setOpaque(false);

        JLabel section =
                new JLabel(
                        "APP PREFERENCES"
                );

        section.setForeground(GREEN);

        section.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        panel.add(section);

        panel.add(
                createLanguagePanel()
        );

        panel.add(
                createNotificationPanel()
        );

        outer.add(
                panel,
                BorderLayout.NORTH
        );

        add(
                outer,
                BorderLayout.CENTER
        );
    }

    private JPanel createLanguagePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        JPanel text =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
                        )
                );

        text.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Language"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Choose your preferred language"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(MUTED);

        text.add(title);

        text.add(subtitle);

        languageBox =
                new JComboBox<>(
                        new String[]{
                                "English",
                                "Tamil",
                                "Hindi"
                        }
                );

        languageBox.setPreferredSize(
                new Dimension(
                        120,
                        38
                )
        );

        panel.add(
                text,
                BorderLayout.CENTER
        );

        panel.add(
                languageBox,
                BorderLayout.EAST
        );

        return panel;
    }

    private JPanel createNotificationPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        JPanel text =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
                        )
                );

        text.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Notifications"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Receive order and FoodLink updates"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(MUTED);

        text.add(title);

        text.add(subtitle);

        notificationButton =
                new JToggleButton(
                        "ON"
                );

        notificationButton.setPreferredSize(
                new Dimension(
                        75,
                        38
                )
        );

        notificationButton.setFocusPainted(false);

        notificationButton.setSelected(true);

        notificationButton.addActionListener(
                e -> updateNotificationButton()
        );

        panel.add(
                text,
                BorderLayout.CENTER
        );

        panel.add(
                notificationButton,
                BorderLayout.EAST
        );

        return panel;
    }

    private void updateNotificationButton() {

        if (notificationButton.isSelected()) {

            notificationButton.setText("ON");

            notificationButton.setBackground(GREEN);

            notificationButton.setForeground(Color.WHITE);

        } else {

            notificationButton.setText("OFF");

            notificationButton.setBackground(
                    new Color(220, 220, 220)
            );

            notificationButton.setForeground(TEXT);
        }
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

        JButton close =
                new JButton(
                        "CLOSE"
                );

        close.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        close.addActionListener(
                e -> dispose()
        );

        JButton save =
                new JButton(
                        "SAVE SETTINGS"
                );

        save.setPreferredSize(
                new Dimension(
                        145,
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
                e -> saveSettings()
        );

        bottom.add(close);

        bottom.add(save);

        add(
                bottom,
                BorderLayout.SOUTH
        );
    }

    private void loadSettings() {

        String sql =
                "SELECT language, notifications_enabled " +
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

                String language =
                        result.getString("language");

                if (language != null) {

                    languageBox.setSelectedItem(
                            language
                    );
                }

                boolean notifications =
                        result.getBoolean(
                                "notifications_enabled"
                        );

                notificationButton.setSelected(
                        notifications
                );

                updateNotificationButton();
            }

            result.close();

            statement.close();

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void saveSettings() {

        String language =
                languageBox
                        .getSelectedItem()
                        .toString();

        boolean notifications =
                notificationButton.isSelected();

        String sql =
                "UPDATE customers " +
                "SET language = ?, " +
                "notifications_enabled = ? " +
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
                    language
            );

            statement.setBoolean(
                    2,
                    notifications
            );

            statement.setString(
                    3,
                    customer.getUsername()
            );

            statement.executeUpdate();

            statement.close();

            connection.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Settings saved successfully!"
            );

            dispose();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save settings."
            );
        }
    }
}