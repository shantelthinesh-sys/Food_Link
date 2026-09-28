package foodlink.main;

import foodlink.gui.CustomerHome;
import foodlink.model.Customer;

public class TestCustomerHome {

    public static void main(String[] args) {

        Customer customer =
                new Customer("shantel", "1234", "Shantel");

        new CustomerHome(customer);
    }
}