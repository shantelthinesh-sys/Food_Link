package foodlink.model;

public class FoodItem {

    private int foodId;
    private String name;
    private double price;
    private String shopName;
    private boolean available;

    // Constructor used by ShopkeeperService
    public FoodItem(
            int foodId,
            String name,
            double price,
            String shopName,
            boolean available) {

        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.shopName = shopName;
        this.available = available;
    }

    // Constructor without ID
    public FoodItem(
            String name,
            double price,
            String shopName,
            boolean available) {

        this.name = name;
        this.price = price;
        this.shopName = shopName;
        this.available = available;
    }

    public int getFoodId() {
        return foodId;
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

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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

    @Override
    public String toString() {
        return name + " - ₹" + price;
    }
}