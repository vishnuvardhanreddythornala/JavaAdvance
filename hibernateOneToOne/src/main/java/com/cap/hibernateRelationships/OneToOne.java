package com.cap.hibernateRelationships;

/**
 * Hello world!
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Passport;
import com.cap.entity.Person;

import java.util.Scanner;

public class OneToOne {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Person.class);
        cfg.addAnnotatedClass(Passport.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Person + Passport");
            System.out.println("2. Fetch Person by ID");
            System.out.println("3. Delete Person by ID");
            System.out.println("4. Read all Persons");
            System.out.println("5. Exit");
            System.out.println("6. Fetch Details by Passport ID");

            int choice = Integer.parseInt(sc.nextLine());

            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter passport number: ");
                    String passportNumber = sc.nextLine();

                    System.out.print("Enter country: ");
                    String country = sc.nextLine();

                    Passport passport = new Passport(passportNumber, country);
                    Person person = new Person(name, passport);

                    session.persist(person);
                    tx.commit();

                    System.out.println("Saved Person with Passport successfully.");
                }

                case 2 -> {
                    System.out.print("Enter person ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Person person = session.get(Person.class, id);

                    if (person == null) {
                        System.out.println("No person found with ID " + id);
                    } else {
                        System.out.println("Name: " + person.getName());
                        System.out.println("Passport: " + person.getPassport().getPassportNumber());
                        System.out.println("Country: " + person.getPassport().getCountry());
                    }

                    tx.commit();
                }

                case 3 -> {
                    System.out.print("Enter person ID to delete: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Person person = session.get(Person.class, id);

                    if (person == null) {
                        System.out.println("No person found with ID " + id);
                    } else {
                        session.remove(person); // cascades to Passport
                        tx.commit();
                        System.out.println("Deleted Person and Passport.");
                    }
                }
                case 4 -> {
                    var persons = session
                            .createQuery("from Person", Person.class)
                            .getResultList();

                    if (persons.isEmpty()) {
                        System.out.println("No persons found.");
                    } else {
                        for (Person p : persons) {
                            System.out.println("ID: " + p.getId());
                            System.out.println("Name: " + p.getName());

                            if (p.getPassport() != null) {
                                System.out.println("Passport: " + p.getPassport().getPassportNumber());
                                System.out.println("Country: " + p.getPassport().getCountry());
                            } else {
                                System.out.println("Passport: NONE");
                            }

                            System.out.println("------------------------");
                        }
                    }

                    tx.commit();
                }

                case 5 -> {
                    tx.commit();
                    running = false;
                    System.out.println("Exiting...");
                }

                case 6 -> {
                    System.out.print("Enter passport ID: ");
                    int pid = Integer.parseInt(sc.nextLine());

                    Passport passport = session.get(Passport.class, pid);

                    if (passport == null) {
                        System.out.println("No passport found with ID " + pid);
                    } else {
                        System.out.println("Passport No: " + passport.getPassportNumber());
                        System.out.println("Country: " + passport.getCountry());

                        if (passport.getPerson() != null) {
                            System.out.println("Belongs to Person: " + passport.getPerson().getName());
                            System.out.println("Person ID: " + passport.getPerson().getId());
                        } else {
                            System.out.println("Person: NULL (bidirectional mapping broken)");
                        }
                    }

                    tx.commit();
                }

                default -> {
                    tx.commit();
                    System.out.println("Invalid choice.");
                }
            }

            session.close();
        }

        factory.close();
        sc.close();
    }
}