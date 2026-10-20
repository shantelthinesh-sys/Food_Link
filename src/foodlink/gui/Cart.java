package foodlink.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import foodlink.model.FoodItem;

public class Cart extends JFrame {

    private ArrayList<FoodItem> cartItems;

    private JPanel itemPanel;
    private JLabel totalLabel;
    private JLabel itemCountLabel;

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

    public Cart() {

        cartItems = new ArrayList<>();

        setTitle("FoodLink - My Cart");
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

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_GREEN);
        topBar.setPreferredSize(new Dimension(950, 75));
        topBar.setBorder(
                new EmptyBorder(0, 25, 0, 25)
        );

        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(
                new BoxLayout(logoPanel, BoxLayout.Y_AXIS)
        );

        JLabel logo = new JLabel("FoodLink");
        logo.setFont(
                new Font("Arial", Font.BOLD, 27)
        );
        logo.setForeground(WHITE);

        JLabel subtitle = new JLabel("Fresh food. Simple ordering.");
        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 11)
        );
        subtitle.setForeground(
                new Color(220, 235, 220)
        );

        logoPanel.add(logo);
        logoPanel.add(subtitle);

        topBar.add(
                logoPanel,
                BorderLayout.WEST
        );

        JLabel cartTitle = new JLabel("MY CART");
        cartTitle.setFont(
                new Font("Arial", Font.BOLD, 20)
        );
        cartTitle.setForeground(WHITE);

        topBar.add(
                cartTitle,
                BorderLayout.EAST
        );

        add(
                topBar,
                BorderLayout.NORTH
        );
    }

    private void createMainContent() {

        JPanel mainPanel = new JPanel(
                new BorderLayout(20, 20)
        );

        mainPanel.setBackground(CREAM);
        mainPanel.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30
                )
        );

        JPanel headingPanel = new JPanel(
                new BorderLayout()
        );

        headingPanel.setOpaque(false);

        JPanel headingText = new JPanel();
        headingText.setOpaque(false);
        headingText.setLayout(
                new BoxLayout(
                        headingText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading = new JLabel(
                "Your Cart"
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        heading.setForeground(DARK_GREEN);

        itemCountLabel = new JLabel(
                "0 items"
        );

        itemCountLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        itemCountLabel.setForeground(
                TEXT_GREY
        );

        headingText.add(heading);
        headingText.add(Box.createVerticalStrut(4));
        headingText.add(itemCountLabel);

        headingPanel.add(
                headingText,
                BorderLayout.WEST
        );

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        itemPanel = new JPanel();

        itemPanel.setLayout(
                new BoxLayout(
                        itemPanel,
                        BoxLayout.Y_AXIS
                )
        );

        itemPanel.setBackground(CREAM);

        JScrollPane scrollPane =
                new JScrollPane(itemPanel);

        scrollPane.setBorder(null);
        scrollPane.setBackground(CREAM);
        scrollPane.getViewport().setBackground(CREAM);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel = createBottomPanel();

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    private JPanel createBottomPanel() {

        JPanel bottom = new JPanel(
                new BorderLayout()
        );

        bottom.setBackground(WHITE);
        bottom.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        new EmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        JPanel totalPanel = new JPanel();
        totalPanel.setOpaque(false);
        totalPanel.setLayout(
                new BoxLayout(
                        totalPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel totalText = new JLabel(
                "TOTAL AMOUNT"
        );

        totalText.setFont(
                new Font("Arial", Font.BOLD, 11)
        );

        totalText.setForeground(TEXT_GREY);

        totalLabel = new JLabel(
                "₹0.00"
        );

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        totalLabel.setForeground(ORANGE);

        totalPanel.add(totalText);
        totalPanel.add(
                Box.createVerticalStrut(3)
        );
        totalPanel.add(totalLabel);

        bottom.add(
                totalPanel,
                BorderLayout.WEST
        );

        JPanel buttons = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        5
                )
        );

        buttons.setOpaque(false);

        JButton backButton =
                createButton(
                        "BACK",
                        DARK_GREEN,
                        WHITE
                );

        JButton checkoutButton =
                createButton(
                        "PROCEED TO CHECKOUT",
                        ORANGE,
                        WHITE
                );

        buttons.add(backButton);
        buttons.add(checkoutButton);

        bottom.add(
                buttons,
                BorderLayout.EAST
        );

        backButton.addActionListener(
                e -> dispose()
        );

        checkoutButton.addActionListener(
                e -> openCheckout()
        );

        return bottom;
    }

    private JButton createButton(
            String text,
            Color background,
            Color foreground) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(foreground);
        button.setBackground(background);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                new EmptyBorder(
                        12, 20, 12, 20
                )
        );

        return button;
    }

    private void openCheckout() {

        if (cartItems.isEmpty()) {

            showMessage(
                    "Your cart is empty.",
                    "Cart"
            );

            return;
        }

        double total = calculateTotal();

        new Payment(total);
    }

    public void addItem(FoodItem foodItem) {

        if (!foodItem.isAvailable()) {

            showMessage(
                    "This food item is currently not available.",
                    "Food Unavailable"
            );

            return;
        }

        cartItems.add(foodItem);

        displayCart();
    }

    private double calculateTotal() {

        double total = 0;

        for (FoodItem item : cartItems) {

            total =
                    total
                    + item.getPrice();
        }

        return total;
    }

    private void displayCart() {

        itemPanel.removeAll();

        double total = 0;

        if (cartItems.isEmpty()) {

            showEmptyCart();

        } else {

            for (int i = 0;
                 i < cartItems.size();
                 i++) {

                FoodItem item =
                        cartItems.get(i);

                JPanel card =
                        createFoodCard(
                                item,
                                i
                        );

                itemPanel.add(card);

                itemPanel.add(
                        Box.createVerticalStrut(12)
                );

                total =
                        total
                        + item.getPrice();
            }
        }

        totalLabel.setText(
                String.format(
                        "₹%.2f",
                        total
                )
        );

        itemCountLabel.setText(
                cartItems.size()
                + (cartItems.size() == 1
                ? " item"
                : " items")
        );

        itemPanel.revalidate();
        itemPanel.repaint();
    }

    private JPanel createFoodCard(
            FoodItem item,
            int index) {

        JPanel card = new JPanel(
                new BorderLayout(
                        15, 10
                )
        );

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        new EmptyBorder(
                                15, 18, 15, 18
                        )
                )
        );

        JPanel left = new JPanel(
                new BorderLayout(
                        12, 0
                )
        );

        left.setOpaque(false);

        JLabel foodIcon =
                new JLabel("FOOD");

        foodIcon.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        foodIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        foodIcon.setForeground(WHITE);
        foodIcon.setBackground(FOOD_GREEN);
        foodIcon.setOpaque(true);

        foodIcon.setPreferredSize(
                new Dimension(65, 55)
        );

        left.add(
                foodIcon,
                BorderLayout.WEST
        );

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel name =
                new JLabel(
                        item.getName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        name.setForeground(TEXT_DARK);

        JLabel shop =
                new JLabel(
                        item.getShopName()
                );

        shop.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        shop.setForeground(TEXT_GREY);

        details.add(name);
        details.add(
                Box.createVerticalStrut(5)
        );
        details.add(shop);

        left.add(
                details,
                BorderLayout.CENTER
        );

        card.add(
                left,
                BorderLayout.CENTER
        );

        JPanel right = new JPanel();

        right.setOpaque(false);
        right.setLayout(
                new BoxLayout(
                        right,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel price =
                new JLabel(
                        String.format(
                                "₹%.2f",
                                item.getPrice()
                        )
                );

        price.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        price.setForeground(ORANGE);

        price.setAlignmentX(
                Component.RIGHT_ALIGNMENT
        );

        JButton remove =
                createButton(
                        "REMOVE",
                        LIGHT_ORANGE,
                        ORANGE
                );

        remove.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        remove.setAlignmentX(
                Component.RIGHT_ALIGNMENT
        );

        remove.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Remove "
                            + item.getName()
                            + " from your cart?",
                            "Remove Item",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                cartItems.remove(index);

                displayCart();
            }
        });

        right.add(price);

        right.add(
                Box.createVerticalStrut(8)
        );

        right.add(remove);

        card.add(
                right,
                BorderLayout.EAST
        );

        return card;
    }

    private void showEmptyCart() {

        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setBackground(CREAM);

        emptyPanel.setLayout(
                new BoxLayout(
                        emptyPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel icon =
                new JLabel("YOUR CART");

        icon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        icon.setForeground(
                FOOD_GREEN
        );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "Your cart is empty"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(
                DARK_GREEN
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel message =
                new JLabel(
                        "Browse FoodLink and add something delicious."
                );

        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        message.setForeground(
                TEXT_GREY
        );

        message.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        emptyPanel.add(
                Box.createVerticalStrut(100)
        );

        emptyPanel.add(icon);

        emptyPanel.add(
                Box.createVerticalStrut(12)
        );

        emptyPanel.add(title);

        emptyPanel.add(
                Box.createVerticalStrut(8)
        );

        emptyPanel.add(message);

        itemPanel.add(emptyPanel);
    }

    private void showMessage(
            String message,
            String title) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}