package foodlink.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import foodlink.model.FoodItem;
import foodlink.service.FoodService;

public class SearchFood extends JFrame {

    private JTextField searchField;
    private JPanel resultPanel;

    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color FOOD_GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(35, 35, 35);
    private final Color TEXT_GREY = new Color(100, 100, 100);

    public SearchFood() {
        this("");
    }

    public SearchFood(String keyword) {

        setTitle("FoodLink - Search Food");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(CREAM);

        setLayout(new BorderLayout());

        createTopBar();
        createSearchArea();
        createResultArea();

        if (keyword != null &&
                !keyword.trim().isEmpty()) {

            searchField.setText(keyword);
            searchFood();
        }

        setVisible(true);
    }

    private void createTopBar() {

        JPanel topBar =
                new JPanel(new BorderLayout());

        topBar.setBackground(DARK_GREEN);

        topBar.setPreferredSize(
                new Dimension(950, 75)
        );

        topBar.setBorder(
                new EmptyBorder(
                        0, 25, 0, 25
                )
        );

        JLabel logo =
                new JLabel("FoodLink");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        logo.setForeground(WHITE);

        JPanel logoPanel =
                new JPanel();

        logoPanel.setOpaque(false);

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Find your favourite food"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(
                new Color(
                        220,
                        235,
                        220
                )
        );

        logoPanel.add(logo);
        logoPanel.add(subtitle);

        topBar.add(
                logoPanel,
                BorderLayout.WEST
        );

        JLabel pageTitle =
                new JLabel("SEARCH FOOD");

        pageTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        pageTitle.setForeground(WHITE);

        topBar.add(
                pageTitle,
                BorderLayout.EAST
        );

        add(
                topBar,
                BorderLayout.NORTH
        );
    }

    private void createSearchArea() {

        JPanel searchArea =
                new JPanel(
                        new BorderLayout(15, 0)
                );

        searchArea.setBackground(CREAM);

        searchArea.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        15,
                        30
                )
        );

        JLabel heading =
                new JLabel(
                        "Search for food"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        heading.setForeground(
                DARK_GREEN
        );

        searchArea.add(
                heading,
                BorderLayout.WEST
        );

        JPanel inputPanel =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        inputPanel.setOpaque(false);

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        searchField.setPreferredSize(
                new Dimension(
                        300,
                        42
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        210,
                                        210
                                )
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        JButton searchButton =
                createButton(
                        "SEARCH",
                        ORANGE,
                        WHITE
                );

        searchButton.setPreferredSize(
                new Dimension(
                        110,
                        42
                )
        );

        inputPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        inputPanel.add(
                searchButton,
                BorderLayout.EAST
        );

        searchArea.add(
                inputPanel,
                BorderLayout.EAST
        );

        searchButton.addActionListener(
                e -> searchFood()
        );

        searchField.addActionListener(
                e -> searchFood()
        );

        add(
                searchArea,
                BorderLayout.CENTER
        );
    }

    private void createResultArea() {

        resultPanel =
                new JPanel();

        resultPanel.setBackground(CREAM);

        resultPanel.setLayout(
                new GridLayout(
                        0,
                        2,
                        18,
                        18
                )
        );

        resultPanel.setBorder(
                new EmptyBorder(
                        10,
                        30,
                        30,
                        30
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultPanel
                );

        scrollPane.setBorder(null);

        scrollPane.setBackground(CREAM);

        scrollPane.getViewport()
                .setBackground(CREAM);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scrollPane,
                BorderLayout.SOUTH
        );

        scrollPane.setPreferredSize(
                new Dimension(
                        950,
                        480
                )
        );
    }

    private void searchFood() {

        String keyword =
                searchField.getText().trim();

        resultPanel.removeAll();

        if (keyword.isEmpty()) {

            showMessage(
                    "Search for food",
                    "Enter a food name to see available items."
            );

            refreshResults();

            return;
        }

        ArrayList<FoodItem> foodItems =
                FoodService.searchFood(keyword);

        if (foodItems.isEmpty()) {

            showMessage(
                    "No food found",
                    "No food item matches \"" +
                    keyword +
                    "\"."
            );

        } else {

            for (FoodItem food : foodItems) {

                resultPanel.add(
                        createFoodCard(food)
                );
            }
        }

        refreshResults();
    }

    private JPanel createFoodCard(
            FoodItem food) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                12,
                                10
                        )
                );

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        225,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JLabel foodLabel =
                new JLabel("FOOD");

        foodLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        foodLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        10
                )
        );

        foodLabel.setForeground(WHITE);
        foodLabel.setBackground(FOOD_GREEN);
        foodLabel.setOpaque(true);

        foodLabel.setPreferredSize(
                new Dimension(
                        55,
                        45
                )
        );

        top.add(
                foodLabel,
                BorderLayout.WEST
        );

        JLabel status;

        if (food.isAvailable()) {

            status =
                    new JLabel(
                            "● Available"
                    );

            status.setForeground(
                    FOOD_GREEN
            );

        } else {

            status =
                    new JLabel(
                            "● Not Available"
                    );

            status.setForeground(
                    ORANGE
            );
        }

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        top.add(
                status,
                BorderLayout.EAST
        );

        card.add(
                top,
                BorderLayout.NORTH
        );

        JPanel details =
                new JPanel();

        details.setOpaque(false);

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel foodName =
                new JLabel(
                        food.getName()
                );

        foodName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        foodName.setForeground(
                TEXT_DARK
        );

        foodName.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel shop =
                new JLabel(
                        "Shop: " +
                        food.getShopName()
                );

        shop.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        shop.setForeground(
                TEXT_GREY
        );

        shop.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel price =
                new JLabel(
                        String.format(
                                "₹%.2f",
                                food.getPrice()
                        )
                );

        price.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        price.setForeground(
                ORANGE
        );

        price.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        details.add(foodName);

        details.add(
                Box.createVerticalStrut(6)
        );

        details.add(shop);

        details.add(
                Box.createVerticalStrut(8)
        );

        details.add(price);

        card.add(
                details,
                BorderLayout.CENTER
        );

        JButton viewButton =
                createButton(
                        "VIEW DETAILS",
                        FOOD_GREEN,
                        WHITE
                );

        viewButton.addActionListener(
                e -> {

                    FoodDetails detailsWindow =
                            new FoodDetails(food);

                    detailsWindow.setVisible(true);
                }
        );

        card.add(
                viewButton,
                BorderLayout.SOUTH
        );

        return card;
    }

    private void showMessage(
            String title,
            String message) {

        resultPanel.setLayout(
                new BorderLayout()
        );

        JPanel messagePanel =
                new JPanel();

        messagePanel.setBackground(CREAM);

        messagePanel.setLayout(
                new BoxLayout(
                        messagePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                DARK_GREEN
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel messageLabel =
                new JLabel(message);

        messageLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        messageLabel.setForeground(
                TEXT_GREY
        );

        messageLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        messagePanel.add(
                Box.createVerticalStrut(80)
        );

        messagePanel.add(
                titleLabel
        );

        messagePanel.add(
                Box.createVerticalStrut(8)
        );

        messagePanel.add(
                messageLabel
        );

        resultPanel.add(
                messagePanel,
                BorderLayout.CENTER
        );
    }

    private void refreshResults() {

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private JButton createButton(
            String text,
            Color background,
            Color foreground) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                foreground
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        11,
                        16,
                        11,
                        16
                )
        );

        return button;
    }
}