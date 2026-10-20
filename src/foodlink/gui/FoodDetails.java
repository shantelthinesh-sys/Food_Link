package foodlink.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import foodlink.model.FoodItem;

public class FoodDetails extends JFrame {

    private FoodItem foodItem;

    private final Color DARK_GREEN = new Color(24, 57, 43);
    private final Color FOOD_GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(244, 81, 30);
    private final Color MUSTARD = new Color(249, 168, 37);
    private final Color CREAM = new Color(255, 248, 231);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_GREEN = new Color(239, 247, 239);
    private final Color LIGHT_ORANGE = new Color(255, 243, 238);
    private final Color TEXT_DARK = new Color(35, 35, 35);
    private final Color TEXT_GREY = new Color(100, 100, 100);

    public FoodDetails(FoodItem foodItem) {

        this.foodItem = foodItem;

        setTitle("FoodLink - " + foodItem.getName());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(CREAM);

        setLayout(new BorderLayout());

        createTopBar();
        createFoodContent();

        setVisible(true);
    }

    private void createTopBar() {

        JPanel topBar = new JPanel(
                new BorderLayout()
        );

        topBar.setBackground(DARK_GREEN);
        topBar.setPreferredSize(
                new Dimension(800, 75)
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

        JLabel logo = new JLabel(
                "FoodLink"
        );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        logo.setForeground(WHITE);

        JLabel subtitle = new JLabel(
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

        JLabel pageTitle = new JLabel(
                "FOOD DETAILS"
        );

        pageTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
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

    private void createFoodContent() {

        JPanel mainPanel = new JPanel(
                new BorderLayout(
                        25,
                        20
                )
        );

        mainPanel.setBackground(CREAM);

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        30,
                        35
                )
        );

        JPanel foodVisual = createFoodVisual();

        mainPanel.add(
                foodVisual,
                BorderLayout.WEST
        );

        JPanel detailsPanel =
                createDetailsPanel();

        mainPanel.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    private JPanel createFoodVisual() {

        JPanel visualPanel = new JPanel(
                new BorderLayout()
        );

        visualPanel.setBackground(
                FOOD_GREEN
        );

        visualPanel.setPreferredSize(
                new Dimension(
                        270,
                        330
                )
        );

        visualPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                35,
                                105,
                                40
                        ),
                        1
                )
        );

        JLabel foodLabel = new JLabel(
                "FOOD"
        );

        foodLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        foodLabel.setForeground(WHITE);

        foodLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        visualPanel.add(
                foodLabel,
                BorderLayout.CENTER
        );

        JPanel bottom = new JPanel();

        bottom.setOpaque(false);

        JLabel shopLabel = new JLabel(
                foodItem.getShopName()
        );

        shopLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        shopLabel.setForeground(WHITE);

        bottom.add(shopLabel);

        visualPanel.add(
                bottom,
                BorderLayout.SOUTH
        );

        return visualPanel;
    }

    private JPanel createDetailsPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        225,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading = new JLabel(
                foodItem.getName()
        );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        heading.setForeground(
                DARK_GREEN
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(heading);

        panel.add(
                Box.createVerticalStrut(8)
        );

        JLabel shop = new JLabel(
                foodItem.getShopName()
        );

        shop.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        shop.setForeground(
                TEXT_GREY
        );

        shop.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(shop);

        panel.add(
                Box.createVerticalStrut(20)
        );

        JLabel price = new JLabel(
                String.format(
                        "₹%.2f",
                        foodItem.getPrice()
                )
        );

        price.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        price.setForeground(
                ORANGE
        );

        price.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(price);

        panel.add(
                Box.createVerticalStrut(15)
        );

        JPanel statusPanel =
                createStatusPanel();

        statusPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(statusPanel);

        panel.add(
                Box.createVerticalGlue()
        );

        JPanel buttons =
                createButtonPanel();

        buttons.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(buttons);

        return panel;
    }

    private JPanel createStatusPanel() {

        JPanel panel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        8
                )
        );

        if (foodItem.isAvailable()) {

            panel.setBackground(
                    LIGHT_GREEN
            );

        } else {

            panel.setBackground(
                    LIGHT_ORANGE
            );
        }

        JLabel status;

        if (foodItem.isAvailable()) {

            status = new JLabel(
                    "● Available"
            );

            status.setForeground(
                    FOOD_GREEN
            );

        } else {

            status = new JLabel(
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
                        14
                )
        );

        panel.add(status);

        return panel;
    }

    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(
                new GridLayout(
                        3,
                        1,
                        0,
                        10
                )
        );

        panel.setOpaque(false);

        JButton addToCart =
                createButton(
                        "ADD TO CART",
                        ORANGE,
                        WHITE
                );

        JButton viewCart =
                createButton(
                        "VIEW CART",
                        FOOD_GREEN,
                        WHITE
                );

        JButton back =
                createButton(
                        "BACK",
                        DARK_GREEN,
                        WHITE
                );

        panel.add(addToCart);
        panel.add(viewCart);
        panel.add(back);

        addToCart.addActionListener(e -> {

            if (foodItem.isAvailable()) {

                Cart cart = new Cart();

                cart.addItem(foodItem);

                showSuccessMessage(
                        foodItem.getName()
                        + " added to cart."
                );

            } else {

                showErrorMessage(
                        "This food item is currently not available."
                );
            }
        });

        viewCart.addActionListener(e -> {

            if (!foodItem.isAvailable()) {

                showErrorMessage(
                        "This food item is currently not available."
                );

                return;
            }

            Cart cart = new Cart();

            cart.addItem(foodItem);
        });

        back.addActionListener(
                e -> dispose()
        );

        return panel;
    }

    private JButton createButton(
            String text,
            Color background,
            Color foreground) {

        JButton button = new JButton(
                text
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
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
                        12,
                        15,
                        12,
                        15
                )
        );

        return button;
    }

    private void showSuccessMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Added to Cart",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showErrorMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "FoodLink",
                JOptionPane.WARNING_MESSAGE
        );
    }
}