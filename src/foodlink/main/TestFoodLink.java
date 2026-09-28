package foodlink.main;

import foodlink.model.FoodItem;
import foodlink.service.FoodService;

public class TestFoodLink {

    public static void main(String[] args) {

        System.out.println("FOODLINK FOOD LIST");
        System.out.println("------------------");

        for (FoodItem food : FoodService.getFoodList()) {

            System.out.println(
                    food.getName()
                    + " - ₹"
                    + food.getPrice()
                    + " - "
                    + food.getShopName());
        }
    }
}