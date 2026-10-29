package foodlink.model;

public class Shopkeeper {

    private int shopkeeperId;
    private String username;
    private String name;
    private long shopId;
    private String shopName;

    public Shopkeeper(
            int shopkeeperId,
            String username,
            String name,
            long shopId,
            String shopName) {

        this.shopkeeperId = shopkeeperId;
        this.username = username;
        this.name = name;
        this.shopId = shopId;
        this.shopName = shopName;
    }

    public int getShopkeeperId() {
        return shopkeeperId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public long getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }
}