package com.cap.hibernateRelationships;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cap.entity.Customer;
import com.cap.entity.Order;

public class OneToMany {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== CUSTOMER ORDER MENU =====");
            System.out.println("1. Add Customer with Orders");
            System.out.println("2. View All Customers");
            System.out.println("3. Add Order to Customer");
            System.out.println("4. View Customer Orders");
            System.out.println("5. Delete Customer");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            Session session = null;
            Transaction tx = null;

            try {
                session = sessionFactory.openSession();

                switch (choice) {

                    case 1:
                        sc.nextLine();

                        System.out.print("Enter Customer Name: ");
                        String name = sc.nextLine();

                        Customer customer = new Customer(name);

                        System.out.print("How many orders? ");
                        int n = sc.nextInt();
                        sc.nextLine();

                        List<Order> orders = new ArrayList<>();

                        for (int i = 1; i <= n; i++) {
                            System.out.print("Enter Order Date " + i + ": ");
                            String date = sc.nextLine();

                            Order order = new Order(date);
                            order.setCustomer(customer);
                            orders.add(order);
                        }

                        customer.setOrders(orders);

                        tx = session.beginTransaction();
                        session.persist(customer);
                        tx.commit();

                        System.out.println("Customer & Orders Added!");
                        break;

                    case 2:
                        Query<Customer> query = session.createQuery("from Customer", Customer.class);
                        List<Customer> customers = query.list();

                        System.out.println("\n--- CUSTOMER LIST ---");

                        if (customers.isEmpty()) {
                            System.out.println("No customers found.");
                        } else {
                            customers.forEach(c ->
                                System.out.println("ID: " + c.getId() + ", Name: " + c.getName())
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter Customer ID: ");
                        int custId = sc.nextInt();
                        sc.nextLine();

                        Customer existingCustomer = session.get(Customer.class, custId);

                        if (existingCustomer == null) {
                            System.out.println("❌ Customer Not Found!");
                            break;
                        }

                        System.out.print("Enter Order Date: ");
                        String newDate = sc.nextLine();

                        Order newOrder = new Order(newDate);
                        newOrder.setCustomer(existingCustomer);

                        tx = session.beginTransaction();
                        session.persist(newOrder);
                        tx.commit();

                        System.out.println("Order Added!");
                        break;

                    case 4:
                        System.out.print("Enter Customer ID: ");
                        int viewId = sc.nextInt();

                        Customer viewCustomer = session.get(Customer.class, viewId);

                        if (viewCustomer == null) {
                            System.out.println("Customer Not Found!");
                            break;
                        }

                        System.out.println("\nCustomer: " + viewCustomer.getName());
                        List<Order> custOrders = viewCustomer.getOrders();

                        if (custOrders == null || custOrders.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            custOrders.forEach(o ->
                                System.out.println("Order ID: " + o.getId() +
                                                   ", Date: " + o.getDate())
                            );
                        }
                        break;

                    case 5:
                        System.out.print("Enter Customer ID: ");
                        int deleteId = sc.nextInt();

                        tx = session.beginTransaction();

                        Customer deleteCustomer = session.get(Customer.class, deleteId);

                        if (deleteCustomer != null) {
                            session.remove(deleteCustomer);
                            System.out.println("Customer Deleted!");
                        } else {
                            System.out.println("Customer Not Found!");
                        }

                        tx.commit();
                        break;

                    case 6:
                        sessionFactory.close();
                        sc.close();
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (Exception e) {

                if (tx != null) tx.rollback();
                e.printStackTrace();

            } finally {

                if (session != null) session.close();
            }
        }
    }
}
