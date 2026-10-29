```java
package foodlink.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import foodlink.model.FoodItem;

public class ShopkeeperService {

    public static ArrayList<FoodItem> getFoodItems(long shopId) {

        ArrayList<FoodItem> foodItems = new ArrayList<>();

        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "f.available, f.stock_quantity, s.shop_name " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "WHERE f.shop_id = ? " +
                "ORDER BY f.food_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, shopId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {

                    FoodItem food = new FoodItem(
                            result.getInt("food_id"),
                            result.getString("food_name"),
                            result.getDouble("price"),
                            result.getString("shop_name"),
                            result.getBoolean("available"),
                            result.getInt("stock_quantity")
                    );

                    foodItems.add(food);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodItems;
    }

    // Add food with stock quantity
    public static boolean addFood(
            long shopId,
            String foodName,
            double price,
            boolean available,
            int stockQuantity) {

        if (foodName == null || foodName.trim().isEmpty()
                || price < 0 || stockQuantity < 0) {
            return false;
        }

        String sql =
                "INSERT INTO food_items " +
                "(shop_id, food_name, price, available, stock_quantity) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, shopId);
            statement.setString(2, foodName.trim());
            statement.setDouble(3, price);
            statement.setBoolean(4, available);
            statement.setInt(5, stockQuantity);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Keep the old method so existing code still compiles
    public static boolean addFood(
            long shopId,
            String foodName,
            double price,
            boolean available) {

        return addFood(shopId, foodName, price, available, 0);
    }

    // Update food including stock quantity
    public static boolean updateFood(
            int foodId,
            String foodName,
            double price,
            boolean available,
            int stockQuantity) {

        if (foodName == null || foodName.trim().isEmpty()
                || price < 0 || stockQuantity < 0) {
            return false;
        }

        String sql =
                "UPDATE food_items " +
                "SET food_name = ?, price = ?, available = ?, " +
                "stock_quantity = ? " +
                "WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, foodName.trim());
            statement.setDouble(2, price);
            statement.setBoolean(3, available);
            statement.setInt(4, stockQuantity);
            statement.setInt(5, foodId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Keep the old method so existing code still compiles
    public static boolean updateFood(
            int foodId,
            String foodName,
            double price,
            boolean available) {

        return updateFood(foodId, foodName, price, available, 0);
    }

    public static boolean deleteFood(int foodId) {

        String sql = "DELETE FROM food_items WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, foodId);
            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAvailability(
            int foodId, boolean available) {

        String sql =
                "UPDATE food_items SET available = ? WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, available);
            statement.setInt(2, foodId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update stock separately
    public static boolean updateStockQuantity(
            int foodId, int stockQuantity) {

        if (stockQuantity < 0) {
            return false;
        }

        String sql =
                "UPDATE food_items SET stock_quantity = ? WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stockQuantity);
            statement.setInt(2, foodId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
```