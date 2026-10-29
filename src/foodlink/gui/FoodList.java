package foodlink.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import foodlink.model.FoodItem;
import foodlink.service.FoodService;

public class FoodList extends JFrame {

    private String shopName;
    private JPanel foodPanel;

    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color FOOD_GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_GREEN = new Color(239, 247, 239);
    private final Color LIGHT_ORANGE = new Color(255, 243, 238);
    private final Color TEXT_DARK = new Color(35, 35, 35);
    private final Color TEXT_GREY = new Color(100, 100, 100);

    public FoodList(String shopName) {

        this.shopName = shopName;

        setTitle("FoodLink - " + shopName);
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(CREAM);

        setLayout(new BorderLayout());

        createTopBar();
        createMainContent();

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

        JPanel logoPanel = new JPanel();

        logoPanel.setOpaque(false);

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
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

        JLabel subtitle =
                new JLabel(
                        "Fresh food. Simple ordering."
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

        JLabel shopTitle =
                new JLabel(
                        shopName
                );

        shopTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        shopTitle.setForeground(WHITE);

        topBar.add(
                shopTitle,
                BorderLayout.EAST
        );

        add(
                topBar,
                BorderLayout.NORTH
        );
    }

    private void createMainContent() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(CREAM);

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        JPanel headingPanel =
                new JPanel(
                        new BorderLayout()
                );

        headingPanel.setOpaque(false);

        JPanel headingText =
                new JPanel();

        headingText.setOpaque(false);

        headingText.setLayout(
                new BoxLayout(
                        headingText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(
                        shopName
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        heading.setForeground(
                DARK_GREEN
        );

        JLabel subHeading =
                new JLabel(
                        "Available food from this shop"
                );

        subHeading.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subHeading.setForeground(
                TEXT_GREY
        );

        headingText.add(heading);

        headingText.add(
                Box.createVerticalStrut(5)
        );

        headingText.add(subHeading);

        headingPanel.add(
                headingText,
                BorderLayout.WEST
        );

        JButton backButton =
                createButton(
                        "BACK",
                        DARK_GREEN,
                        WHITE
                );

        headingPanel.add(
                backButton,
                BorderLayout.EAST
        );

        backButton.addActionListener(
                e -> dispose()
        );

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        foodPanel = new JPanel();

        foodPanel.setBackground(CREAM);

        foodPanel.setLayout(
                new GridLayout(
                        0,
                        2,
                        18,
                        18
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(foodPanel);

        scrollPane.setBorder(null);

        scrollPane.setBackground(CREAM);

        scrollPane.getViewport()
                .setBackground(CREAM);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        loadFoodItems();
    }

    private void loadFoodItems() {

        ArrayList<FoodItem> foodItems =
                FoodService.getFoodByShop(
                        shopName
                );

        foodPanel.removeAll();

        if (foodItems.isEmpty()) {

            showEmptyMessage();

        } else {

            for (FoodItem food : foodItems) {

                JPanel card =
                        createFoodCard(food);

                foodPanel.add(card);
            }
        }

        foodPanel.revalidate();
        foodPanel.repaint();
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

        JLabel foodIcon =
                new JLabel("FOOD");

        foodIcon.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        foodIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        10
                )
        );

        foodIcon.setForeground(WHITE);

        foodIcon.setBackground(
                FOOD_GREEN
        );

        foodIcon.setOpaque(true);

        foodIcon.setPreferredSize(
                new Dimension(
                        55,
                        45
                )
        );

        top.add(
                foodIcon,
                BorderLayout.WEST
        );

        JPanel statusPanel =
                new JPanel();

        statusPanel.setOpaque(false);

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

        statusPanel.add(status);

        top.add(
                statusPanel,
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

        JLabel name =
                new JLabel(
                        food.getName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        name.setForeground(
                TEXT_DARK
        );

        name.setAlignmentX(
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

        details.add(name);

        details.add(
                Box.createVerticalStrut(8)
        );

        details.add(price);

        card.add(
                details,
                BorderLayout.CENTER
        );

        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );

        bottom.setOpaque(false);

        JButton viewButton =
                createButton(
                        "VIEW DETAILS",
                        FOOD_GREEN,
                        WHITE
                );

        if (!food.isAvailable()) {

            viewButton.setBackground(
                    new Color(
                            150,
                            150,
                            150
                    )
            );
        }

        bottom.add(
                viewButton,
                BorderLayout.CENTER
        );

        viewButton.addActionListener(e -> {

            FoodDetails detailsWindow =
                    new FoodDetails(food);

            detailsWindow.setVisible(true);
        });

        card.add(
                bottom,
                BorderLayout.SOUTH
        );

        return card;
    }

    private void showEmptyMessage() {

        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setBackground(CREAM);

        emptyPanel.setLayout(
                new BoxLayout(
                        emptyPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "No food items available"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(DARK_GREEN);

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel message =
                new JLabel(
                        "This shop has not added any food items yet."
                );

        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        message.setForeground(TEXT_GREY);

        message.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        emptyPanel.add(
                Box.createVerticalStrut(100)
        );

        emptyPanel.add(title);

        emptyPanel.add(
                Box.createVerticalStrut(8)
        );

        emptyPanel.add(message);

        foodPanel.setLayout(
                new BorderLayout()
        );

        foodPanel.add(
                emptyPanel,
                BorderLayout.CENTER
        );
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