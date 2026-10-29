package foodlink.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import foodlink.model.FoodItem;

public class FoodService {

    public static ArrayList<FoodItem> getFoodList() {
        ArrayList<FoodItem> foodList = new ArrayList<>();

        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "ORDER BY f.food_id";

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection == null) {
                return foodList;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet result = statement.executeQuery()) {

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
            }
        } catch (Exception e) {
            System.out.println("Error loading food items!");
            e.printStackTrace();
        }

        return foodList;
    }

    public static ArrayList<FoodItem> searchFood(String keyword) {
        ArrayList<FoodItem> result = new ArrayList<>();

        if (keyword == null) {
            return result;
        }

        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "WHERE LOWER(f.food_name) LIKE ? " +
                "ORDER BY f.food_id";

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection == null) {
                return result;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + keyword.trim().toLowerCase() + "%");

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        FoodItem food = new FoodItem(
                                resultSet.getInt("food_id"),
                                resultSet.getString("food_name"),
                                resultSet.getDouble("price"),
                                resultSet.getString("shop_name"),
                                resultSet.getBoolean("available")
                        );

                        result.add(food);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error searching food items!");
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<FoodItem> getFoodByShop(String shopName) {
        ArrayList<FoodItem> result = new ArrayList<>();

        if (shopName == null) {
            return result;
        }

        String sql =
                "SELECT f.food_id, f.food_name, f.price, " +
                "s.shop_name, f.available " +
                "FROM food_items f " +
                "JOIN shops s ON f.shop_id = s.shop_id " +
                "WHERE LOWER(s.shop_name) = ? " +
                "ORDER BY f.food_id";

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection == null) {
                return result;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, shopName.trim().toLowerCase());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        FoodItem food = new FoodItem(
                                resultSet.getInt("food_id"),
                                resultSet.getString("food_name"),
                                result.getClass() == null ? 0.0 : resultSet.getDouble("price"),
                                resultSet.getString("shop_name"),
                                resultSet.getBoolean("available")
                        );

                        result.add(food);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading food items for shop!");
            e.printStackTrace();
        }

        return result;
    }
}