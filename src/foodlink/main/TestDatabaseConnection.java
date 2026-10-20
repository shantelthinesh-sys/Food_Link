package foodlink.main;

import java.sql.Connection;

import foodlink.service.DatabaseConnection;

public class TestDatabaseConnection {

    public static void main(String[] args) {

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection != null) {

            System.out.println(
                    "FoodLink Supabase connection test PASSED."
            );

            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println(
                    "FoodLink Supabase connection test FAILED."
            );
        }
    }
}