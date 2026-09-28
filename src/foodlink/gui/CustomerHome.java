package foodlink.gui;

import javax.swing.*;
import java.awt.*;

import foodlink.model.Customer;

public class CustomerHome extends JFrame {

    private Customer customer;

    public CustomerHome(Customer customer) {

        this.customer = customer;

        setTitle("FoodLink - Customer Home");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FOODLINK");

        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(40, 30, 250, 40);

        add(title);

        JLabel welcome = new JLabel(
                "Welcome, " + customer.getName());

        welcome.setFont(new Font("Arial", Font.PLAIN, 18));
        welcome.setBounds(40, 75, 300, 30);

        add(welcome);

        JLabel heading = new JLabel(
                "What would you like to eat today?");

        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(40, 140, 450, 40);

        add(heading);

        JButton csCanteen =
                new JButton("CS Canteen");

        csCanteen.setBounds(50, 220, 200, 60);

        add(csCanteen);

        JButton aerospace =
                new JButton("Aerospace Canteen");

        aerospace.setBounds(280, 220, 200, 60);

        add(aerospace);

        JButton cake =
                new JButton("Cake World");

        cake.setBounds(510, 220, 200, 60);

        add(cake);

        JButton juice =
                new JButton("Juice Shop");

        juice.setBounds(50, 310, 200, 60);

        add(juice);

        JButton milkyMist =
                new JButton("Milky Mist");

        milkyMist.setBounds(280, 310, 200, 60);

        add(milkyMist);

        JButton search =
                new JButton("Search Food");

        search.setBounds(510, 310, 200, 60);

        add(search);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}