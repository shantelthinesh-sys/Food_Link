package foodlink.model;

public class FoodItem {

    private String name;
    private double price;
    private String shop;
    private boolean available;

    public FoodItem(String name, double price, String shop, boolean available) {
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getShop() {
        return shop;
    }

    public boolean isAvailable() {
        return available;
    }
}