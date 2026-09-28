package foodlink.service;

import java.util.ArrayList;

import foodlink.model.FoodItem;

public class FoodService {

    private static ArrayList<FoodItem> foodList = new ArrayList<>();

    static {

        foodList.add(new FoodItem(
                1,
                "Chicken Burger",
                "Craver's Point",
                "Fast Food",
                80,
                true));

        foodList.add(new FoodItem(
                2,
                "French Fries",
                "Craver's Point",
                "Fast Food",
                50,
                true));

        foodList.add(new FoodItem(
                3,
                "Meals",
                "RV Canteen",
                "Meals",
                70,
                true));

        foodList.add(new FoodItem(
                4,
                "Chicken Biryani",
                "Hyderabad Biryani",
                "Biryani",
                120,
                true));

        foodList.add(new FoodItem(
                5,
                "Veg Biryani",
                "Hyderabad Biryani",
                "Biryani",
                80,
                true));

        foodList.add(new FoodItem(
                6,
                "Pizza",
                "Foody Buddy",
                "Fast Food",
                120,
                true));

        foodList.add(new FoodItem(
                7,
                "Chicken Sandwich",
                "Foody Buddy",
                "Fast Food",
                70,
                true));

        foodList.add(new FoodItem(
                8,
                "Fresh Lime Juice",
                "Juice Shop",
                "Juice",
                40,
                true));

        foodList.add(new FoodItem(
                9,
                "Mango Juice",
                "Juice Shop",
                "Juice",
                50,
                true));

        foodList.add(new FoodItem(
                10,
                "Chocolate Cake",
                "Cake World",
                "Cake",
                60,
                true));

        foodList.add(new FoodItem(
                11,
                "Milkshake",
                "Milky Mist",
                "Beverage",
                60,
                true));

        foodList.add(new FoodItem(
                12,
                "Chicken Rice",
                "Aerospace Canteen",
                "Rice",
                100,
                true));
    }

    public static ArrayList<FoodItem> getFoodList() {
        return foodList;
    }

    public static ArrayList<FoodItem> searchFood(String keyword) {

        ArrayList<FoodItem> result = new ArrayList<>();

        for (FoodItem food : foodList) {

            if (food.getName().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(food);
            }
        }

        return result;
    }

    public static ArrayList<FoodItem> getFoodByShop(String shopName) {

        ArrayList<FoodItem> result = new ArrayList<>();

        for (FoodItem food : foodList) {

            if (food.getShopName().equalsIgnoreCase(shopName)) {

                result.add(food);
            }
        }

        return result;
    }
}