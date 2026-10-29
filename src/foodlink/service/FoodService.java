package foodlink.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import foodlink.model.FoodItem;

public class FoodService {

<<<<<<< HEAD
=======
    private static final ArrayList<FoodItem> foodList = new ArrayList<>();

    static {
        foodList.add(new FoodItem(1, "Chicken Burger", "Craver's Point", "Fast Food", 80, true));
        foodList.add(new FoodItem(2, "French Fries", "Craver's Point", "Fast Food", 50, true));
        foodList.add(new FoodItem(3, "Meals", "RV Canteen", "Meals", 70, true));
        foodList.add(new FoodItem(4, "Chicken Biryani", "Hyderabad Biryani", "Biryani", 120, true));
        foodList.add(new FoodItem(5, "Veg Biryani", "Hyderabad Biryani", "Biryani", 80, true));
        foodList.add(new FoodItem(6, "Pizza", "Foody Buddy", "Fast Food", 120, true));
        foodList.add(new FoodItem(7, "Chicken Sandwich", "Foody Buddy", "Fast Food", 70, true));
        foodList.add(new FoodItem(8, "Fresh Lime Juice", "Juice Shop", "Juice", 40, true));
        foodList.add(new FoodItem(9, "Mango Juice", "Juice Shop", "Juice", 50, true));
        foodList.add(new FoodItem(10, "Chocolate Cake", "Cake World", "Cake", 60, true));
        foodList.add(new FoodItem(11, "Milkshake", "Milky Mist", "Beverage", 60, true));
        foodList.add(new FoodItem(12, "Chicken Rice", "Aerospace Canteen", "Rice", 100, true));
    }

>>>>>>> refs/heads/master
    public static ArrayList<FoodItem> getFoodList() {
<<<<<<< HEAD

        ArrayList<FoodItem> foodList = new ArrayList<>();

        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "ORDER BY f.food_id";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection == null) {
                return foodList;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                FoodItem food = new FoodItem(
                        result.getInt("food_id"),
                        result.getString("food_name"),
                        result.getDouble("price"),
                        result.getString("shop_name"),
                        result.getBoolean("available")
                );

                foodList.add(food);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading food items!"
            );

            e.printStackTrace();
        }

        return foodList;
=======
        return new ArrayList<>(foodList);
>>>>>>> refs/heads/master
    }

    public static ArrayList<FoodItem> searchFood(String keyword) {
        ArrayList<FoodItem> result = new ArrayList<>();

<<<<<<< HEAD
        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "WHERE LOWER(f.food_name) LIKE ? " +
                "ORDER BY f.food_id";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection == null) {
                return result;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%" + keyword.toLowerCase() + "%"
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                FoodItem food = new FoodItem(
                        resultSet.getInt("food_id"),
                        resultSet.getString("food_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("shop_name"),
                        resultSet.getBoolean("available")
                );

=======
        if (keyword == null) {
            return result;
        }

        keyword = keyword.trim().toLowerCase();

        for (FoodItem food : foodList) {
            if (food.getName() != null &&
                    food.getName().toLowerCase().contains(keyword)) {
>>>>>>> refs/heads/master
                result.add(food);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Error searching food items!"
            );

            e.printStackTrace();
        }

        return result;
    }

<<<<<<< HEAD
    public static ArrayList<FoodItem> getFoodByShop(
            String shopName) {

=======
    public static ArrayList<FoodItem> getFoodByShop(String shopName) {
>>>>>>> refs/heads/master
        ArrayList<FoodItem> result = new ArrayList<>();

<<<<<<< HEAD
        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "WHERE LOWER(s.shop_name) = ? " +
                "ORDER BY f.food_id";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection == null) {
                return result;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    shopName.toLowerCase()
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                FoodItem food = new FoodItem(
                        resultSet.getInt("food_id"),
                        resultSet.getString("food_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("shop_name"),
                        resultSet.getBoolean("available")
                );

=======
        if (shopName == null) {
            return result;
        }

        shopName = shopName.trim();

        for (FoodItem food : foodList) {
            if (food.getShopName() != null &&
                    food.getShopName().equalsIgnoreCase(shopName)) {
>>>>>>> refs/heads/master
                result.add(food);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading food items for shop!"
            );

            e.printStackTrace();
        }

        return result;
    }
}
