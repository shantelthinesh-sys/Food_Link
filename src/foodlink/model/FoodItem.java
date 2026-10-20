package foodlink.model;

public class FoodItem {

    private int id;
    private String name;
    private double price;
    private String shopName;
    private boolean available;

    public FoodItem(
            int id,
            String name,
            double price,
            String shopName,
            boolean available) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.shopName = shopName;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getShopName() {
        return shopName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}