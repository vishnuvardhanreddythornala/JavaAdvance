package com.cap.hibernateRestaurantApp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cap.entity.Menu;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== RESTAURANT MENU =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            Session session = null;
            Transaction tx = null;

            try {
                session = sessionFactory.openSession();

                switch (choice) {

                    case 1:
                        sc.nextLine(); 

                        System.out.print("Enter Item Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();

                        sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();

                        System.out.print("Available (true/false): ");
                        boolean available = sc.nextBoolean();

                        tx = session.beginTransaction();

                        Menu item = new Menu(name, price, category, available);
                        session.persist(item);

                        tx.commit();
                        System.out.println("Menu Item Added!");
                        break;

                    case 2:
                        Query<Menu> query = session.createQuery("from Menu", Menu.class);
                        List<Menu> items = query.list();

                        System.out.println("\n--- MENU ITEMS ---");

                        if (items.isEmpty()) {
                            System.out.println("No items found.");
                        } else {
                            items.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Item ID: ");
                        int updateId = sc.nextInt();

                        System.out.print("Enter New Price: ");
                        double newPrice = sc.nextDouble();

                        tx = session.beginTransaction();

                        Menu updateItem = session.get(Menu.class, updateId);

                        if (updateItem != null) {
                            updateItem.setPrice(newPrice);
                            session.merge(updateItem);
                            System.out.println("Price Updated!");
                        } else {
                            System.out.println("Item Not Found!");
                        }

                        tx.commit();
                        break;
                    case 4:
                        System.out.print("Enter Item ID: ");
                        int deleteId = sc.nextInt();

                        tx = session.beginTransaction();

                        Menu deleteItem = session.get(Menu.class, deleteId);

                        if (deleteItem != null) {
                            session.remove(deleteItem);
                            System.out.println("Item Deleted!");
                        } else {
                            System.out.println("Item Not Found!");
                        }

                        tx.commit();
                        break;
                    case 5:
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
