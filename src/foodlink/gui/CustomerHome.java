package foodlink.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import foodlink.model.Customer;
public class CustomerHome extends JFrame {
    private Customer customer;
    // =========================
    // FOODLINK COLOUR PALETTE
    // =========================
    private final Color GREEN =
            new Color(46, 125, 50);
    private final Color ORANGE =
            new Color(244, 81, 30);
    private final Color MUSTARD =
            new Color(249, 168, 37);
    private final Color CREAM =
            new Color(255, 248, 231);
    private final Color DARK_GREEN =
            new Color(24, 57, 43);
    private final Color WHITE =
            Color.WHITE;
    private final Color TEXT =
            new Color(38, 55, 45);
    private final Color MUTED =
            new Color(105, 120, 110);
    private final Color BORDER =
            new Color(229, 222, 207);
    public CustomerHome(Customer customer) {
        this.customer = customer;
        setTitle("FoodLink - Food Delivered to You");
        setSize(1450, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        setLayout(
                new BorderLayout()
        );
        createTopBar();
        createSidebar();
        createMainContent();
        createRightPanel();
        setVisible(true);
    }
    // =========================================================
    // TOP BAR
    // =========================================================
    private void createTopBar() {
        JPanel topBar =
                new JPanel(
                        new BorderLayout()
                );
        topBar.setBackground(WHITE);
        topBar.setPreferredSize(
                new Dimension(
                        0,
                        78
                )
        );
        topBar.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        BORDER
                )
        );
        // =========================
        // LOGO
        // =========================
        JPanel logoPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                18,
                                13
                        )
                );
        logoPanel.setOpaque(false);
        JPanel logoIcon =
                new JPanel() {
                    @Override
                    protected void paintComponent(
                            Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 =
                                (Graphics2D) g;
                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );
                        g2.setColor(ORANGE);
                        g2.fillRoundRect(
                                0,
                                0,
                                48,
                                48,
                                16,
                                16
                        );
                        g2.setColor(WHITE);
                        g2.setFont(
                                new Font(
                                        "Arial",
                                        Font.BOLD,
                                        25
                                )
                        );
                        g2.drawString(
                                "F",
                                16,
                                33
                        );
                    }
                };
        logoIcon.setPreferredSize(
                new Dimension(
                        48,
                        48
                )
        );
        logoPanel.add(
                logoIcon
        );
        JPanel logoText =
                new JPanel();
        logoText.setOpaque(false);
        logoText.setLayout(
                new BoxLayout(
                        logoText,
                        BoxLayout.Y_AXIS
                )
        );
        JLabel foodLink =
                new JLabel(
                        "FoodLink"
                );
        foodLink.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );
        foodLink.setForeground(
                DARK_GREEN
        );
        JLabel tagline =
                new JLabel(
                        "GOOD FOOD  •  GOOD MOOD"
                );
        tagline.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        9
                )
        );
        tagline.setForeground(
                MUTED
        );
        logoText.add(
                foodLink
        );
        logoText.add(
                tagline
        );
        logoPanel.add(
                logoText
        );
        topBar.add(
                logoPanel,
                BorderLayout.WEST
        );
        // =========================
        // SEARCH BAR
        // =========================
        JPanel searchWrapper =
                new JPanel(
                        new BorderLayout()
                );
        searchWrapper.setOpaque(false);
        searchWrapper.setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        20,
                        16,
                        20
                )
        );
        JTextField searchField =
                new JTextField();
        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );
        searchField.setText(
                "  Search food, dishes or shops..."
        );
        searchField.setForeground(
                MUTED
        );
        searchField.setBackground(
                new Color(
                        247,
                        244,
                        239
                )
        );
        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        235,
                                        230,
                                        222
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );
        JButton searchButton =
                new JButton(
                        "SEARCH"
                );
        searchButton.setBackground(
                ORANGE
        );
        searchButton.setForeground(
                WHITE
        );
        searchButton.setFocusPainted(
                false
        );
        searchButton.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        0,
                        20
                )
        );
        searchButton.addActionListener(
                e -> {
                    String keyword =
                            searchField
                                    .getText()
                                    .trim();
                    if (!keyword.isEmpty()
                            && !keyword.startsWith(
                                    "Search")) {
                        new SearchFood(
                                keyword
                        );
                    }
                }
        );
        searchWrapper.add(
                searchField,
                BorderLayout.CENTER
        );
        searchWrapper.add(
                searchButton,
                BorderLayout.EAST
        );
        topBar.add(
                searchWrapper,
                BorderLayout.CENTER
        );
        // =========================
        // TOP RIGHT BUTTONS
        // =========================
        JPanel rightTop =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                18
                        )
                );
        rightTop.setOpaque(false);
        JButton notification =
                createIconButton(
                        "🔔",
                        "Notifications"
                );
        JButton cart =
                createIconButton(
                        "🛒",
                        "Cart"
                );
        JButton profile =
                createProfileButton();
        notification.addActionListener(
                e -> showMessage(
                        "No new notifications."
                )
        );
        cart.addActionListener(
                e -> new Cart()
        );
        profile.addActionListener(
                e -> showProfile()
        );
        rightTop.add(
                notification
        );
        rightTop.add(
                cart
        );
        rightTop.add(
                profile
        );
        topBar.add(
                rightTop,
                BorderLayout.EAST
        );
        add(
                topBar,
                BorderLayout.NORTH
        );
    }
    // =========================================================
    // SIDEBAR
    // =========================================================
    private void createSidebar() {
        JPanel sidebar =
                new JPanel();
        sidebar.setBackground(
                DARK_GREEN
        );
        sidebar.setPreferredSize(
                new Dimension(
                        235,
                        0
                )
        );
        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );
        sidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        16,
                        20,
                        16
                )
        );
        JLabel menu =
                new JLabel(
                        "MENU"
                );
        menu.setForeground(
                new Color(
                        190,
                        210,
                        201
                )
        );
        menu.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );
        menu.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        14,
                        12,
                        0
                )
        );
        sidebar.add(
                menu
        );
        sidebar.add(
                createSidebarButton(
                        "⌂",
                        "Dashboard",
                        true
                )
        );
        sidebar.add(
                createSidebarButton(
                        "🍴",
                        "Browse Food",
                        false
                )
        );
        sidebar.add(
                createSidebarButton(
                        "▣",
                        "My Orders",
                        false
                )
        );
        sidebar.add(
                createSidebarButton(
                        "🛒",
                        "Cart",
                        false
                )
        );
        sidebar.add(
                createSidebarButton(
                        "♡",
                        "Favorites",
                        false
                )
        );
        sidebar.add(
                createSidebarButton(
                        "▤",
                        "Transactions",
                        false
                )
        );
        sidebar.add(
                createSidebarButton(
                        "⚙",
                        "Settings",
                        false
                )
        );
        sidebar.add(
                Box.createVerticalStrut(
                        25
                )
        );
        JLabel shops =
                new JLabel(
                        "FOOD SHOPS"
                );
        shops.setForeground(
                new Color(
                        190,
                        210,
                        201
                )
        );
        shops.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );
        shops.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        14,
                        12,
                        0
                )
        );
        sidebar.add(
                shops
        );
        String[] shopNames = {
                "CS Canteen",
                "RV Canteen",
                "Hyderabad Biryani",
                "Foody Buddy",
                "Aerospace Canteen",
                "Cake World",
                "Juice Shop",
                "Milky Mist"
        };
        for (String shop : shopNames) {
            JButton button =
                    createSidebarButton(
                            "•",
                            shop,
                            false
                    );
            button.addActionListener(
                    e -> new FoodList(shop)
            );
            sidebar.add(
                    button
            );
        }
        sidebar.add(
                Box.createVerticalGlue()
        );
        // =========================
        // DELIVERY CARD
        // =========================
        JPanel delivery =
                new JPanel();
        delivery.setLayout(
                new BoxLayout(
                        delivery,
                        BoxLayout.Y_AXIS
                )
        );
        delivery.setBackground(
                new Color(
                        27,
                        67,
                        54
                )
        );
        delivery.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        47,
                                        91,
                                        76
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                15,
                                18,
                                15
                        )
                )
        );
        JLabel bike =
                new JLabel(
                        "FAST DELIVERY"
                );
        bike.setForeground(
                MUSTARD
        );
        bike.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );
        JLabel deliveryText =
                new JLabel(
                        "<html>Your favourite food,<br>"
                        + "delivered fresh.</html>"
                );
        deliveryText.setForeground(
                WHITE
        );
        deliveryText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );
        delivery.add(
                bike
        );
        delivery.add(
                Box.createVerticalStrut(
                        7
                )
        );
        delivery.add(
                deliveryText
        );
        sidebar.add(
                delivery
        );
        add(
                sidebar,
                BorderLayout.WEST
        );
    }
    private JButton createSidebarButton(
            String icon,
            String text,
            boolean selected) {
        JButton button =
                new JButton(
                        icon + "    " + text
                );
        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );
        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );
        button.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );
        button.setForeground(
                selected
                        ? WHITE
                        : new Color(
                                220,
                                231,
                                225
                        )
        );
        button.setBackground(
                selected
                        ? ORANGE
                        : DARK_GREEN
        );
        button.setFocusPainted(
                false
        );
        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        14,
                        10,
                        10
                )
        );
        if (!selected) {
            button.addMouseListener(
                    new MouseAdapter() {
                        @Override
                        public void mouseEntered(
                                MouseEvent e) {
                            button.setBackground(
                                    new Color(
                                            38,
                                            88,
                                            55
                                    )
                            );
                        }
                        @Override
                        public void mouseExited(
                                MouseEvent e) {
                            button.setBackground(
                                    DARK_GREEN
                            );
                        }
                    }
            );
        }
        if (text.equals("Cart")) {
            button.addActionListener(
                    e -> new Cart()
            );
        }
        if (text.equals("My Orders")
                || text.equals("Transactions")) {
            button.addActionListener(
                    e -> new OrderHistory()
            );
        }        if (text.equals("Settings")) {
            button.addActionListener(
                    e -> new CustomerSettings(customer)
            );
        }

        return button;
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================
    private void createMainContent() {
        JPanel main =
                new JPanel(
                        new BorderLayout()
                );
        main.setBackground(
                CREAM
        );
        JPanel content =
                new JPanel();
        content.setBackground(
                CREAM
        );
        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );
        content.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        30,
                        20
                )
        );
        content.add(
                createHero()
        );
        content.add(
                Box.createVerticalStrut(
                        18
                )
        );
        content.add(
                createCategories()
        );
        content.add(
                Box.createVerticalStrut(
                        25
                )
        );
        content.add(
                createSectionTitle(
                        "🔥  Popular Food Shops",
                        "Top campus food destinations"
                )
        );
        content.add(
                Box.createVerticalStrut(
                        12
                )
        );
        content.add(
                createShopGrid()
        );
        content.add(
                Box.createVerticalStrut(
                        25
                )
        );
        content.add(
                createSectionTitle(
                        "⭐  Recommended For You",
                        "Popular food choices"
                )
        );
        content.add(
                Box.createVerticalStrut(
                        12
                )
        );
        content.add(
                createRecommendationGrid()
        );
        JScrollPane scroll =
                new JScrollPane(
                        content
                );
        scroll.setBorder(null);
        scroll.getVerticalScrollBar()
                .setUnitIncrement(
                        18
                );
        main.add(
                scroll,
                BorderLayout.CENTER
        );
        add(
                main,
                BorderLayout.CENTER
        );
    }
    // =========================================================
    // HERO
    // =========================================================
    private JPanel createHero() {
        JPanel hero =
                new JPanel(
                        new BorderLayout()
                ) {
                    @Override
                    protected void paintComponent(
                            Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 =
                                (Graphics2D) g;
                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );
                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        DARK_GREEN,
                                        getWidth(),
                                        getHeight(),
                                        GREEN
                                );
                        g2.setPaint(
                                gradient
                        );
                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                24,
                                24
                        );
                    }
                };
        hero.setPreferredSize(
                new Dimension(
                        0,
                        225
                )
        );
        hero.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        35,
                        30,
                        30
                )
        );
        JPanel text =
                new JPanel();
        text.setOpaque(false);
        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );
        JLabel welcome =
                new JLabel(
                        "Welcome to FoodLink"
                );
        welcome.setForeground(
                MUSTARD
        );
        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );
        JLabel title =
                new JLabel(
                        "<html>Delicious Food,<br>"
                        + "Delivered to You</html>"
                );
        title.setForeground(
                WHITE
        );
        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );
        JLabel description =
                new JLabel(
                        "<html>Discover tasty food from your "
                        + "favourite campus shops.</html>"
                );
        description.setForeground(
                new Color(
                        235,
                        242,
                        237
                )
        );
        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );
        JButton order =
                new JButton(
                        "ORDER NOW  →"
                );
        order.setBackground(
                ORANGE
        );
        order.setForeground(
                WHITE
        );
        order.setFocusPainted(
                false
        );
        order.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );
        order.addActionListener(
                e -> new FoodList(
                        "CS Canteen"
                )
        );
        text.add(
                welcome
        );
        text.add(
                Box.createVerticalStrut(
                        7
                )
        );
        text.add(
                title
        );
        text.add(
                Box.createVerticalStrut(
                        7
                )
        );
        text.add(
                description
        );
        text.add(
                Box.createVerticalStrut(
                        15
                )
        );
        text.add(
                order
        );
        hero.add(
                text,
                BorderLayout.WEST
        );
        JLabel foodVisual =
                new JLabel(
                        "🍔",
                        SwingConstants.CENTER
                );
        foodVisual.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        105
                )
        );
        hero.add(
                foodVisual,
                BorderLayout.EAST
        );
        return hero;
    }
    // =========================================================
    // CATEGORIES
    // =========================================================
    private JPanel createCategories() {
        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                8,
                                10,
                                0
                        )
                );
        panel.setOpaque(false);
        String[][] categories = {
                {"🍔", "Burgers"},
                {"🍕", "Pizza"},
                {"🍜", "Noodles"},
                {"🍛", "Indian"},
                {"🥗", "Healthy"},
                {"🍰", "Desserts"},
                {"🥤", "Drinks"},
                {"🍗", "Meals"}
        };
        for (String[] category :
                categories) {
            JPanel card =
                    new JPanel();
            card.setLayout(
                    new BoxLayout(
                            card,
                            BoxLayout.Y_AXIS
                    )
            );
            card.setBackground(
                    WHITE
            );
            card.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    BORDER
                            ),
                            BorderFactory.createEmptyBorder(
                                    12,
                                    8,
                                    12,
                                    8
                            )
                    )
            );
            JLabel icon =
                    new JLabel(
                            category[0],
                            SwingConstants.CENTER
                    );
            icon.setFont(
                    new Font(
                            "Segoe UI Emoji",
                            Font.PLAIN,
                            30
                    )
            );
            icon.setAlignmentX(
                    CENTER_ALIGNMENT
            );
            JLabel name =
                    new JLabel(
                            category[1],
                            SwingConstants.CENTER
                    );
            name.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            11
                    )
            );
            name.setForeground(
                    TEXT
            );
            name.setAlignmentX(
                    CENTER_ALIGNMENT
            );
            card.add(
                    icon
            );
            card.add(
                    Box.createVerticalStrut(
                            6
                    )
            );
            card.add(
                    name
            );
            panel.add(
                    card
            );
        }
        return panel;
    }
    // =========================================================
    // SHOP GRID
    // =========================================================
    private JPanel createShopGrid() {
        JPanel grid =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                12,
                                12
                        )
                );
        grid.setOpaque(false);
        String[] shops = {
                "CS Canteen",
                "RV Canteen",
                "Hyderabad Biryani",
                "Foody Buddy",
                "Aerospace Canteen",
                "Cake World",
                "Juice Shop",
                "Milky Mist"
        };
        String[] icons = {
                "🍔",
                "🍛",
                "🍗",
                "🍕",
                "🍚",
                "🍰",
                "🥤",
                "🥛"
        };
        for (int i = 0;
             i < shops.length;
             i++) {
            grid.add(
                    createShopCard(
                            shops[i],
                            icons[i]
                    )
            );
        }
        return grid;
    }
    private JPanel createShopCard(
            String shop,
            String icon) {
        JPanel card =
                new JPanel(
                        new BorderLayout()
                );
        card.setBackground(
                WHITE
        );
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                14,
                                14,
                                14,
                                14
                        )
                )
        );
        JLabel foodIcon =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );
        foodIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        48
                )
        );
        JPanel iconPanel =
                new JPanel(
                        new BorderLayout()
                );
        iconPanel.setBackground(
                new Color(
                        244,
                        250,
                        238
                )
        );
        iconPanel.setPreferredSize(
                new Dimension(
                        0,
                        90
                )
        );
        iconPanel.add(
                foodIcon,
                BorderLayout.CENTER
        );
        card.add(
                iconPanel,
                BorderLayout.NORTH
        );
        JLabel name =
                new JLabel(
                        shop
                );
        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );
        name.setForeground(
                TEXT
        );
        name.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        0,
                        4,
                        0
                )
        );
        card.add(
                name,
                BorderLayout.CENTER
        );
        JLabel description =
                new JLabel(
                        "Fresh food • Campus favourite"
                );
        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        10
                )
        );
        description.setForeground(
                MUTED
        );
        JButton view =
                new JButton(
                        "VIEW SHOP  →"
                );
        view.setBackground(
                ORANGE
        );
        view.setForeground(
                WHITE
        );
        view.setFocusPainted(
                false
        );
        view.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );
        view.addActionListener(
                e -> new FoodList(shop)
        );
        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );
        bottom.setOpaque(false);
        bottom.add(
                description,
                BorderLayout.WEST
        );
        bottom.add(
                view,
                BorderLayout.EAST
        );
        card.add(
                bottom,
                BorderLayout.SOUTH
        );
        return card;
    }
    // =========================================================
    // RECOMMENDATIONS
    // =========================================================
    private JPanel createRecommendationGrid() {
        JPanel grid =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                12,
                                0
                        )
                );
        grid.setOpaque(false);
        grid.add(
                createRecommendation(
                        "Chicken Burger",
                        "🍔",
                        "₹80"
                )
        );
        grid.add(
                createRecommendation(
                        "Chicken Biryani",
                        "🍛",
                        "₹120"
                )
        );
        grid.add(
                createRecommendation(
                        "Pizza",
                        "🍕",
                        "₹120"
                )
        );
        grid.add(
                createRecommendation(
                        "Chocolate Cake",
                        "🍰",
                        "₹60"
                )
        );
        return grid;
    }
    private JPanel createRecommendation(
            String name,
            String icon,
            String price) {
        JPanel card =
                new JPanel(
                        new BorderLayout()
                );
        card.setBackground(
                WHITE
        );
        card.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );
        JLabel image =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );
        image.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        50
                )
        );
        image.setOpaque(true);
        image.setBackground(
                new Color(
                        244,
                        250,
                        238
                )
        );
        image.setPreferredSize(
                new Dimension(
                        0,
                        85
                )
        );
        card.add(
                image,
                BorderLayout.NORTH
        );
        JPanel details =
                new JPanel(
                        new BorderLayout()
                );
        details.setBackground(
                WHITE
        );
        details.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );
        JLabel foodName =
                new JLabel(
                        name
                );
        foodName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );
        foodName.setForeground(
                TEXT
        );
        JLabel foodPrice =
                new JLabel(
                        price
                );
        foodPrice.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );
        foodPrice.setForeground(
                GREEN
        );
        details.add(
                foodName,
                BorderLayout.WEST
        );
        details.add(
                foodPrice,
                BorderLayout.EAST
        );
        card.add(
                details,
                BorderLayout.CENTER
        );
        return card;
    }
    // =========================================================
    // RIGHT PANEL
    // =========================================================
    private void createRightPanel() {
        JPanel right =
                new JPanel();
        right.setPreferredSize(
                new Dimension(
                        285,
                        0
                )
        );
        right.setBackground(
                CREAM
        );
        right.setLayout(
                new BoxLayout(
                        right,
                        BoxLayout.Y_AXIS
                )
        );
        right.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        10,
                        20,
                        15
                )
        );
        right.add(
                createCartPreview()
        );
        right.add(
                Box.createVerticalStrut(
                        15
                )
        );
        right.add(
                createOffer()
        );
        right.add(
                Box.createVerticalStrut(
                        15
                )
        );
        right.add(
                createQuickProfile()
        );
        add(
                right,
                BorderLayout.EAST
        );
    }
    // =========================================================
    // CART PREVIEW
    // =========================================================
    private JPanel createCartPreview() {
        JPanel cart =
                createWhiteCard();
        cart.setLayout(
                new BoxLayout(
                        cart,
                        BoxLayout.Y_AXIS
                )
        );
        JLabel title =
                new JLabel(
                        "Your Cart"
                );
        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );
        title.setForeground(
                TEXT
        );
        cart.add(
                title
        );
        cart.add(
                Box.createVerticalStrut(
                        15
                )
        );
        JLabel message =
                new JLabel(
                        "<html>Your cart items will appear "
                        + "here when you add food.</html>"
                );
        message.setForeground(
                MUTED
        );
        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );
        cart.add(
                message
        );
        cart.add(
                Box.createVerticalStrut(
                        18
                )
        );
        JLabel total =
                new JLabel(
                        "Ready to order?"
                );
        total.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );
        total.setForeground(
                TEXT
        );
        cart.add(
                total
        );
        cart.add(
                Box.createVerticalStrut(
                        10
                )
        );
        JButton open =
                new JButton(
                        "VIEW CART  →"
                );
        open.setBackground(
                ORANGE
        );
        open.setForeground(
                WHITE
        );
        open.setFocusPainted(
                false
        );
        open.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );
        open.addActionListener(
                e -> new Cart()
        );
        cart.add(
                open
        );
        return cart;
    }
    // =========================================================
    // OFFER
    // =========================================================
    private JPanel createOffer() {
        JPanel offer =
                new JPanel();
        offer.setLayout(
                new BoxLayout(
                        offer,
                        BoxLayout.Y_AXIS
                )
        );
        offer.setBackground(
                DARK_GREEN
        );
        offer.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        18,
                        20,
                        18
                )
        );
        JLabel title =
                new JLabel(
                        "FOODLINK SPECIAL"
                );
        title.setForeground(
                MUSTARD
        );
        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );
        JLabel offerText =
                new JLabel(
                        "<html><b>Fresh food.</b><br>"
                        + "Great prices.<br>"
                        + "Happy students.</html>"
                );
        offerText.setForeground(
                WHITE
        );
        offerText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );
        offer.add(
                title
        );
        offer.add(
                Box.createVerticalStrut(
                        8
                )
        );
        offer.add(
                offerText
        );
        return offer;
    }
    // =========================================================
    // QUICK PROFILE
    // =========================================================
    private JPanel createQuickProfile() {
        JPanel profile = createWhiteCard();
        profile.setLayout(new BoxLayout(profile, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Quick Profile");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(TEXT);

        JLabel name = new JLabel(" " + customer.getName());
        name.setFont(new Font("Arial", Font.BOLD, 14));
        name.setForeground(TEXT);

        JLabel type = new JLabel(" FoodLink Customer");
        type.setForeground(MUTED);
        type.setFont(new Font("Arial", Font.PLAIN, 11));

        profile.add(title);
        profile.add(Box.createVerticalStrut(15));
        profile.add(name);
        profile.add(type);
        profile.add(Box.createVerticalStrut(15));

        String[] options = {
                "Edit Profile",
                "My Orders",
                "Transactions",
                "Settings",
                "Help & Support"
        };

        for (String option : options) {
            JButton button = new JButton(option);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBackground(WHITE);
            button.setForeground(TEXT);
            button.setFocusPainted(false);
            button.setBorder(
                    BorderFactory.createEmptyBorder(
                            8, 0, 8, 0
                    )
            );

            if (option.equals("Edit Profile")) {
                button.addActionListener(
                        e -> new CustomerProfile(customer)
                );
            }

            if (option.equals("My Orders")
                    || option.equals("Transactions")) {
                button.addActionListener(
                        e -> new OrderHistory()
                );
            }

            if (option.equals("Settings")) {
                button.addActionListener(
                        e -> new CustomerSettings(customer)
                );
            }

            if (option.equals("Help & Support")) {
                button.addActionListener(
                        e -> showMessage(
                                "For help, please contact the FoodLink support team."
                        )
                );
            }

            profile.add(button);
        }

        return profile;
    }

    // =========================================================
    // HELPERS
    // =========================================================
    private JPanel createWhiteCard() {
        JPanel panel =
                new JPanel();
        panel.setBackground(
                WHITE
        );
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );
        panel.setAlignmentX(
                LEFT_ALIGNMENT
        );
        return panel;
    }
    private JPanel createSectionTitle(
            String title,
            String subtitle) {
        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );
        panel.setOpaque(false);
        JLabel titleLabel =
                new JLabel(
                        title
                );
        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );
        titleLabel.setForeground(
                TEXT
        );
        JLabel sub =
                new JLabel(
                        subtitle
                );
        sub.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );
        sub.setForeground(
                MUTED
        );
        panel.add(
                titleLabel,
                BorderLayout.WEST
        );
        panel.add(
                sub,
                BorderLayout.EAST
        );
        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );
        return panel;
    }
    private JButton createIconButton(
            String icon,
            String tooltip) {
        JButton button =
                new JButton(
                        icon
                );
        button.setToolTipText(
                tooltip
        );
        button.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        20
                )
        );
        button.setBackground(
                WHITE
        );
        button.setBorder(null);
        button.setFocusPainted(
                false
        );
        return button;
    }
    private JButton createProfileButton() {
        JButton button =
                new JButton(
                        "<html><b>"
                        + customer.getName()
                        + "</b><br>"
                        + "<font size='2'>Food Lover</font>"
                        + "</html>"
                );
        button.setForeground(
                TEXT
        );
        button.setBackground(
                WHITE
        );
        button.setFocusPainted(
                false
        );
        button.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        8,
                        5,
                        8
                )
        );
        return button;
    }
    private void showProfile() {
        new CustomerProfile(customer);
    }
    private void showMessage(
            String message) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                message,
                "FoodLink",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
}
