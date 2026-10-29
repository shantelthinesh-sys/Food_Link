<<<<<<< HEAD
package foodlink.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/postgres";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "#18Shantel@2";

    public static Connection getConnection() {

        try {

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println(
                    "FoodLink database connected successfully!"
            );

            return connection;

        } catch (Exception e) {

            System.out.println(
                    "Database connection failed!"
            );

            e.printStackTrace();

            return null;
        }
    }
}
=======

package foodlink.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/FoodLink";

    private static final String USER = "postgres";

    private static final String PASSWORD = "7002";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            System.out.println("PostgreSQL connected successfully!");
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("PostgreSQL connection failed.");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Connection test passed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> refs/heads/master
