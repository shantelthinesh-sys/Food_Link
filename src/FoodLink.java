import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FoodLink extends JFrame implements ActionListener {

    // ---------------- FOODLINK THEME ----------------

    private final Color DARK_GREEN = new Color(24, 59, 46);
    private final Color ORANGE = new Color(246, 77, 32);
    private final Color CREAM = new Color(255, 249, 235);
    private final Color LIGHT_GREEN = new Color(235, 243, 233);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(48, 57, 51);
    private final Color MUTED = new Color(110, 120, 112);

    enum OrderStatus {
        PENDING, ACCEPTED, PREPARING, READY, DELIVERED, CANCELLED
    }

    class Order {
        String orderId;
        String customerName;
        String phone;
        String items;
        double amount;
        String paymentMethod;
        OrderStatus status;

        Order(String orderId, String customerName, String phone,
              String items, double amount, String paymentMethod) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.phone = phone;
            this.items = items;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
            this.status = OrderStatus.PENDING;
        }
    }

    private final ArrayList<Order> orders = new ArrayList<>();

    private int orderCount = 1000;
    private int paymentCount = 5000;

    private String customerName;
    private String phone;

    private double subtotal = 0;
    private double discount = 0;
    private double gst = 0;
    private double deliveryFee = 0;
    private double total = 0;

    private String selectedItems = "";
    private String selectedPayment = "";

    private JTextField nameField;
    private JTextField phoneField;

    private JLabel burgerQty;
    private JLabel friesQty;
    private JLabel pizzaQty;
    private JLabel coffeeQty;

    private JCheckBox burgerBox;
    private JCheckBox friesBox;
    private JCheckBox pizzaBox;
    private JCheckBox coffeeBox;

    // ---------------- CONSTRUCTOR ----------------

    public FoodLink() {
        setTitle("FoodLink - Food Delivered to You");
        setSize(1200, 760);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showHome();
        setVisible(true);
    }

    // ---------------- COMMON UI HELPERS ----------------

    private JPanel createPage() {
        JPanel panel = new JPanel(new BorderLayout(0, 18));
        panel.setBackground(CREAM);
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));
        return panel;
    }

    private JLabel createHeading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 27));
        label.setForeground(DARK_GREEN);
        return label;
    }

    private JLabel createSubtitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(MUTED);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(ORANGE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(11, 17, 11, 17));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(DARK_GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(ORANGE);
            }
        });

        return button;
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(230, 225, 215)),
                new EmptyBorder(20, 20, 20, 20)
        ));
        return card;
    }

    private JPanel createBrandHeader() {
        JPanel header = new JPanel(new BorderLayout(15, 0));
        header.setBackground(WHITE);
        header.setBorder(new EmptyBorder(15, 24, 15, 24));

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("FoodLink");
        logo.setFont(new Font("Arial", Font.BOLD, 27));
        logo.setForeground(DARK_GREEN);

        JLabel tagline = new JLabel("GOOD FOOD  •  GOOD MOOD");
        tagline.setFont(new Font("Arial", Font.PLAIN, 10));
        tagline.setForeground(MUTED);

        brand.add(logo);
        brand.add(tagline);

        JLabel profile = new JLabel(
                customerName == null ? "Welcome, Food Lover"
                        : "Welcome, " + customerName
        );
        profile.setFont(new Font("Arial", Font.BOLD, 13));
        profile.setForeground(DARK_GREEN);

        header.add(brand, BorderLayout.WEST);
        header.add(profile, BorderLayout.EAST);
        return header;
    }

    private void displayScreen(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().setBackground(CREAM);
        setLayout(new BorderLayout());
        add(createBrandHeader(), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void addPageIntro(JPanel panel, String title, String subtitle) {
        JPanel intro = new JPanel();
        intro.setBackground(CREAM);
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));

        JLabel heading = createHeading(title);
        JLabel sub = createSubtitle(subtitle);

        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);
        sub.setBorder(new EmptyBorder(7, 0, 0, 0));

        intro.add(heading);
        intro.add(sub);
        panel.add(intro, BorderLayout.NORTH);
    }

    private JPanel createButtonRow(JButton... buttons) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setBackground(CREAM);

        for (JButton button : buttons) {
            row.add(button);
        }
        return row;
    }

    private void styleTextArea(JTextArea area) {
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setForeground(TEXT);
        area.setBackground(WHITE);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new EmptyBorder(15, 15, 15, 15));
        area.setCaretPosition(0);
    }

    // ---------------- HOME ----------------

    void showHome() {
        JPanel page = createPage();
        page.setLayout(new BorderLayout(0, 20));

        JPanel top = new JPanel();
        top.setBackground(CREAM);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("WELCOME TO FOODLINK");
        welcome.setFont(new Font("Arial", Font.BOLD, 15));
        welcome.setForeground(ORANGE);

        JLabel title = new JLabel(
                "<html>Delicious Food,<br>Delivered to You</html>"
        );
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(WHITE);

        JLabel description = new JLabel(
                "Discover tasty food and enjoy your favourite meals."
        );
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setForeground(WHITE);

        JPanel banner = new JPanel();
        banner.setBackground(DARK_GREEN);
        banner.setLayout(new BoxLayout(banner, BoxLayout.Y_AXIS));
        banner.setBorder(new EmptyBorder(30, 30, 30, 30));

        banner.add(welcome);
        banner.add(Box.createVerticalStrut(10));
        banner.add(title);
        banner.add(Box.createVerticalStrut(10));
        banner.add(description);

        top.add(banner);
        top.add(Box.createVerticalStrut(20));

        JLabel menuHeading = createHeading("Explore FoodLink");
        menuHeading.setFont(new Font("Arial", Font.BOLD, 21));
        top.add(menuHeading);

        JPanel actions = new JPanel(new GridLayout(0, 2, 15, 15));
        actions.setBackground(CREAM);

        actions.add(createHomeCard(
                "Order Food",
                "Choose your favourite dishes and place an order.",
                "BROWSE FOOD",
                () -> showCustomerDetails()
        ));

        actions.add(createHomeCard(
                "My Orders",
                "View your recent orders and their status.",
                "VIEW ORDERS",
                () -> showMyOrders()
        ));

        actions.add(createHomeCard(
                "Track Order",
                "Check the current status of your orders.",
                "TRACK ORDER",
                () -> showTracking()
        ));

        actions.add(createHomeCard(
                "Payment History",
                "Review your completed payment details.",
                "VIEW PAYMENTS",
                () -> showPaymentHistory()
        ));

        actions.add(createHomeCard(
                "Shopkeeper Dashboard",
                "Add dishes and manage your shop menu.",
                "OPEN DASHBOARD",
                () -> showShopkeeperDashboard()
        ));

        JScrollPane scroll = new JScrollPane(actions);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREAM);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        page.add(top, BorderLayout.NORTH);
        page.add(scroll, BorderLayout.CENTER);

        displayScreen(page);
    }

    private JPanel createHomeCard(
            String title,
            String description,
            String buttonText,
            Runnable action) {

        JPanel card = createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(title);
        name.setFont(new Font("Arial", Font.BOLD, 17));
        name.setForeground(DARK_GREEN);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel details = new JLabel(
                "<html><div style='width:250px'>"
                        + description + "</div></html>"
        );
        details.setFont(new Font("Arial", Font.PLAIN, 13));
        details.setForeground(MUTED);
        details.setBorder(new EmptyBorder(10, 0, 18, 0));
        details.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton button = createButton(buttonText);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(e -> action.run());

        card.add(name);
        card.add(details);
        card.add(button);
        return card;
    }

    // ---------------- CUSTOMER DETAILS ----------------

    void showCustomerDetails() {
        JPanel page = createPage();
        addPageIntro(page, "Your Details",
                "Enter your details before choosing your food.");

        JPanel card = createCard();
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(22);
        phoneField = new JTextField(22);

        addFormRow(card, gbc, 0, "Customer Name", nameField);
        addFormRow(card, gbc, 1, "Phone Number", phoneField);

        JButton backButton = createButton("Back");
        JButton nextButton = createButton("Continue");

        backButton.addActionListener(e -> showHome());

        nextButton.addActionListener(e -> {
            customerName = nameField.getText().trim();
            phone = phoneField.getText().trim();

            if (customerName.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter your name and phone number.");
                return;
            }

            showFoodMenu();
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(backButton, gbc);

        gbc.gridx = 1;
        card.add(nextButton, gbc);

        page.add(card, BorderLayout.CENTER);
        displayScreen(page);
    }

    private void addFormRow(
            JPanel card, GridBagConstraints gbc,
            int row, String labelText, JTextField field) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        JLabel label = new JLabel(labelText);
        label.setForeground(DARK_GREEN);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        card.add(field, gbc);
    }

    // ---------------- FOOD MENU ----------------

    void showFoodMenu() {
        JPanel page = createPage();
        addPageIntro(page, "Browse Food",
                "Choose dishes and adjust the quantity.");

        JPanel foods = new JPanel(new GridLayout(0, 2, 15, 15));
        foods.setBackground(CREAM);

        burgerBox = new JCheckBox("Chicken Burger  -  ₹120");
        friesBox = new JCheckBox("French Fries  -  ₹80");
        pizzaBox = new JCheckBox("Veg Pizza  -  ₹210");
        coffeeBox = new JCheckBox("Cold Coffee  -  ₹70");

        burgerQty = new JLabel("0", SwingConstants.CENTER);
        friesQty = new JLabel("0", SwingConstants.CENTER);
        pizzaQty = new JLabel("0", SwingConstants.CENTER);
        coffeeQty = new JLabel("0", SwingConstants.CENTER);

        foods.add(createFoodCard(
                "🍔", "Chicken Burger", 120,
                burgerBox, burgerQty,
                () -> increaseQuantity(burgerQty),
                () -> decreaseQuantity(burgerQty, burgerBox)
        ));

        foods.add(createFoodCard(
                "🍟", "French Fries", 80,
                friesBox, friesQty,
                () -> increaseQuantity(friesQty),
                () -> decreaseQuantity(friesQty, friesBox)
        ));

        foods.add(createFoodCard(
                "🍕", "Veg Pizza", 210,
                pizzaBox, pizzaQty,
                () -> increaseQuantity(pizzaQty),
                () -> decreaseQuantity(pizzaQty, pizzaBox)
        ));

        foods.add(createFoodCard(
                "☕", "Cold Coffee", 70,
                coffeeBox, coffeeQty,
                () -> increaseQuantity(coffeeQty),
                () -> decreaseQuantity(coffeeQty, coffeeBox)
        ));

        JScrollPane scroll = new JScrollPane(foods);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREAM);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JButton backButton = createButton("Back");
        JButton checkoutButton = createButton("Go to Checkout");

        backButton.addActionListener(e -> showCustomerDetails());

        checkoutButton.addActionListener(e -> {
            createSelectedItems();

            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please select at least one food item.");
                return;
            }

            calculateBill();
        });

        page.add(scroll, BorderLayout.CENTER);
        page.add(createButtonRow(backButton, checkoutButton),
                BorderLayout.SOUTH);

        displayScreen(page);
    }

    private JPanel createFoodCard(
            String emoji, String foodName, int price,
            JCheckBox box, JLabel quantity,
            Runnable plusAction, Runnable minusAction) {

        JPanel card = createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel(emoji);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        icon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel name = new JLabel(foodName);
        name.setFont(new Font("Arial", Font.BOLD, 17));
        name.setForeground(DARK_GREEN);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel cost = new JLabel("₹" + price);
        cost.setFont(new Font("Arial", Font.BOLD, 15));
        cost.setForeground(ORANGE);
        cost.setAlignmentX(Component.LEFT_ALIGNMENT);

        box.setText("Select item");
        box.setBackground(WHITE);
        box.setForeground(DARK_GREEN);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);

        box.addActionListener(e -> {
            if (box.isSelected()
                    && Integer.parseInt(quantity.getText()) == 0) {
                quantity.setText("1");
            } else if (!box.isSelected()) {
                quantity.setText("0");
            }
        });

        JButton minus = createButton("-");
        JButton plus = createButton("+");

        minus.addActionListener(e -> minusAction.run());
        plus.addActionListener(e -> {
            plusAction.run();
            box.setSelected(true);
        });

        JPanel quantityRow = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 0));
        quantityRow.setBackground(WHITE);
        quantityRow.add(minus);
        quantityRow.add(quantity);
        quantityRow.add(plus);

        card.add(icon);
        card.add(Box.createVerticalStrut(8));
        card.add(name);
        card.add(Box.createVerticalStrut(5));
        card.add(cost);
        card.add(Box.createVerticalStrut(8));
        card.add(box);
        card.add(Box.createVerticalStrut(8));
        card.add(quantityRow);

        return card;
    }

    // ---------------- QUANTITY ----------------

    void increaseQuantity(JLabel label) {
        int quantity = Integer.parseInt(label.getText());
        label.setText(String.valueOf(quantity + 1));
    }

    void decreaseQuantity(JLabel label, JCheckBox box) {
        int quantity = Integer.parseInt(label.getText());

        if (quantity > 0) {
            quantity--;
        }

        label.setText(String.valueOf(quantity));

        if (quantity == 0) {
            box.setSelected(false);
        }
    }

    // ---------------- SELECTED ITEMS ----------------

    void createSelectedItems() {
        ArrayList<String> selected = new ArrayList<>();

        if (burgerBox.isSelected()
                && Integer.parseInt(burgerQty.getText()) > 0) {
            selected.add("Chicken Burger x" + burgerQty.getText());
        }

        if (friesBox.isSelected()
                && Integer.parseInt(friesQty.getText()) > 0) {
            selected.add("French Fries x" + friesQty.getText());
        }

        if (pizzaBox.isSelected()
                && Integer.parseInt(pizzaQty.getText()) > 0) {
            selected.add("Veg Pizza x" + pizzaQty.getText());
        }

        if (coffeeBox.isSelected()
                && Integer.parseInt(coffeeQty.getText()) > 0) {
            selected.add("Cold Coffee x" + coffeeQty.getText());
        }

        selectedItems = String.join(", ", selected);
    }

    // ---------------- BILL ----------------

    void calculateBill() {
        subtotal = 0;

        if (burgerBox.isSelected()) {
            subtotal += 120 * Integer.parseInt(burgerQty.getText());
        }

        if (friesBox.isSelected()) {
            subtotal += 80 * Integer.parseInt(friesQty.getText());
        }

        if (pizzaBox.isSelected()) {
            subtotal += 210 * Integer.parseInt(pizzaQty.getText());
        }

        if (coffeeBox.isSelected()) {
            subtotal += 70 * Integer.parseInt(coffeeQty.getText());
        }

        discount = 0;

        if (subtotal >= 500) {
            discount = subtotal * 0.20;
        } else if (subtotal >= 300) {
            discount = subtotal * 0.10;
        }

        double afterDiscount = subtotal - discount;
        gst = afterDiscount * 0.05;
        deliveryFee = afterDiscount >= 500 ? 0 : 30;
        total = afterDiscount + gst + deliveryFee;

        showBill();
    }

    // ---------------- CHECKOUT ----------------

    void showBill() {
        JPanel page = createPage();
        addPageIntro(page, "Checkout",
                "Review your order before payment.");

        JPanel card = createCard();
        card.setLayout(new BorderLayout(10, 10));

        JTextArea billArea = new JTextArea();
        styleTextArea(billArea);

        billArea.setText(
                "CUSTOMER DETAILS\n"
                + "Name: " + customerName
                + "\nPhone: " + phone
                + "\n\nORDER ITEMS\n" + selectedItems
                + "\n\nBILL SUMMARY\n"
                + String.format("Subtotal: ₹%.2f%n", subtotal)
                + String.format("Discount: ₹%.2f%n", discount)
                + String.format("GST (5%%): ₹%.2f%n", gst)
                + String.format("Delivery Fee: ₹%.2f%n", deliveryFee)
                + "\n--------------------------------\n"
                + String.format("TOTAL: ₹%.2f", total)
        );

        card.add(new JScrollPane(billArea), BorderLayout.CENTER);

        JButton backButton = createButton("Back to Food");
        JButton paymentButton = createButton("Proceed to Payment");

        backButton.addActionListener(e -> showFoodMenu());
        paymentButton.addActionListener(e -> showPayment());

        page.add(card, BorderLayout.CENTER);
        page.add(createButtonRow(backButton, paymentButton),
                BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- PAYMENT ----------------

    void showPayment() {
        JPanel page = createPage();
        addPageIntro(page, "Payment",
                "Select your preferred payment method.");

        JPanel card = createCard();
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel amountLabel = new JLabel(
                String.format("Amount to Pay: ₹%.2f", total));
        amountLabel.setFont(new Font("Arial", Font.BOLD, 21));
        amountLabel.setForeground(DARK_GREEN);

        JComboBox<String> paymentBox = new JComboBox<>(
                new String[]{"Cash on Delivery", "UPI", "Card"});
        paymentBox.setFont(new Font("Arial", Font.PLAIN, 15));
        paymentBox.setPreferredSize(new Dimension(220, 38));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(amountLabel, gbc);

        gbc.gridy = 1;
        card.add(new JLabel("Payment Method:"), gbc);

        gbc.gridy = 2;
        card.add(paymentBox, gbc);

        JButton backButton = createButton("Back");
        JButton payButton = createButton("Confirm Payment");

        backButton.addActionListener(e -> showBill());

        payButton.addActionListener(e -> {
            selectedPayment = (String) paymentBox.getSelectedItem();

            if (selectedPayment == null) {
                JOptionPane.showMessageDialog(
                        this, "Please select a payment method.");
                return;
            }

            processPayment();
        });

        page.add(card, BorderLayout.CENTER);
        page.add(createButtonRow(backButton, payButton),
                BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- PROCESS PAYMENT ----------------

    void processPayment() {
        paymentCount++;

        JOptionPane.showMessageDialog(
                this,
                "Payment Successful!\nPayment ID: PAY" + paymentCount,
                "Payment Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        createOrder();
    }

    // ---------------- CREATE ORDER ----------------

    void createOrder() {
        orderCount++;

        String orderId = "FL" + orderCount;

        Order order = new Order(
                orderId,
                customerName,
                phone,
                selectedItems,
                total,
                selectedPayment
        );

        orders.add(order);
        showOrderConfirmation(order);
    }

    // ---------------- ORDER CONFIRMATION ----------------

    void showOrderConfirmation(Order order) {
        JPanel page = createPage();
        addPageIntro(page, "Order Confirmed!",
                "Thank you for ordering with FoodLink.");

        JPanel card = createCard();
        card.setLayout(new BorderLayout(10, 10));

        JLabel success = new JLabel("✓  Your order has been placed!");
        success.setFont(new Font("Arial", Font.BOLD, 20));
        success.setForeground(DARK_GREEN);
        success.setBorder(new EmptyBorder(0, 0, 10, 0));

        JTextArea details = new JTextArea();
        styleTextArea(details);

        details.setText(
                "Order ID: " + order.orderId
                + "\nCustomer: " + order.customerName
                + "\nPhone: " + order.phone
                + "\nItems: " + order.items
                + "\nPayment: " + order.paymentMethod
                + String.format("%nTotal: ₹%.2f", order.amount)
                + "\nStatus: " + order.status
        );

        JPanel inner = new JPanel(new BorderLayout(0, 10));
        inner.setBackground(WHITE);
        inner.add(success, BorderLayout.NORTH);
        inner.add(new JScrollPane(details), BorderLayout.CENTER);

        card.add(inner, BorderLayout.CENTER);

        JButton homeButton = createButton("Back to Home");
        homeButton.addActionListener(e -> showHome());

        page.add(card, BorderLayout.CENTER);
        page.add(createButtonRow(homeButton), BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- MY ORDERS ----------------

    void showMyOrders() {
        JPanel page = createPage();
        addPageIntro(page, "My Orders",
                "View the orders placed during this session.");

        JPanel card = createCard();
        card.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        styleTextArea(area);

        if (orders.isEmpty()) {
            area.setText("No orders available yet.\n\n"
                    + "Browse food and place your first order!");
        } else {
            for (Order order : orders) {
                area.append(
                        "ORDER " + order.orderId
                        + "\nItems: " + order.items
                        + String.format("%nAmount: ₹%.2f", order.amount)
                        + "\nPayment: " + order.paymentMethod
                        + "\nStatus: " + order.status
                        + "\n--------------------------------\n\n"
                );
            }
            area.setCaretPosition(0);
        }

        card.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton backButton = createButton("Back to Home");
        backButton.addActionListener(e -> showHome());

        page.add(card, BorderLayout.CENTER);
        page.add(createButtonRow(backButton), BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- TRACK ORDER ----------------

    void showTracking() {
        JPanel page = createPage();
        addPageIntro(page, "Track Order",
                "Check the latest order status.");

        JPanel list = new JPanel();
        list.setBackground(CREAM);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        if (orders.isEmpty()) {
            JPanel card = createCard();
            card.add(new JLabel("No orders to track yet."));
            list.add(card);
        } else {
            for (Order order : orders) {
                JPanel card = createCard();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 125));

                JLabel id = new JLabel("Order " + order.orderId);
                id.setFont(new Font("Arial", Font.BOLD, 17));
                id.setForeground(DARK_GREEN);

                JLabel items = new JLabel("Items: " + order.items);
                items.setForeground(TEXT);

                JLabel status = new JLabel("Status: " + order.status);
                status.setFont(new Font("Arial", Font.BOLD, 14));
                status.setForeground(ORANGE);

                card.add(id);
                card.add(Box.createVerticalStrut(7));
                card.add(items);
                card.add(Box.createVerticalStrut(7));
                card.add(status);

                list.add(card);
                list.add(Box.createVerticalStrut(12));
            }
        }

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREAM);

        JButton backButton = createButton("Back to Home");
        backButton.addActionListener(e -> showHome());

        page.add(scroll, BorderLayout.CENTER);
        page.add(createButtonRow(backButton), BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- PAYMENT HISTORY ----------------

    void showPaymentHistory() {
        JPanel page = createPage();
        addPageIntro(page, "Payment History",
                "Review payments associated with your orders.");

        JPanel card = createCard();
        card.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        styleTextArea(area);

        if (orders.isEmpty()) {
            area.setText("No payment history available yet.");
        } else {
            for (Order order : orders) {
                area.append(
                        "PAYMENT FOR " + order.orderId
                        + String.format("%nAmount: ₹%.2f", order.amount)
                        + "\nMethod: " + order.paymentMethod
                        + "\nPayment ID: PAY"
                        + (paymentCount - orders.size()
                        + orders.indexOf(order) + 1)
                        + "\n--------------------------------\n\n"
                );
            }
            area.setCaretPosition(0);
        }

        card.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton backButton = createButton("Back to Home");
        backButton.addActionListener(e -> showHome());

        page.add(card, BorderLayout.CENTER);
        page.add(createButtonRow(backButton), BorderLayout.SOUTH);

        displayScreen(page);
    }

    // ---------------- SHOPKEEPER DASHBOARD ----------------

    void showShopkeeperDashboard() {
        new foodlink.shopkeeper.ShopkeeperDashboard(1L);
    }

    // ---------------- ACTION LISTENER ----------------

    @Override
    public void actionPerformed(ActionEvent e) {
        // Button actions are handled by individual listeners.
    }

    // ---------------- MAIN ----------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FoodLink());
    }
}
