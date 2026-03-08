package com.cap.hibernateRelationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Department;
import com.cap.entity.Employee;

public class ManyToOne {
    public static void main(String[] args) {

        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure("hibernatemanytoone.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class);

        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {

            System.out.println("\n---- MENU ----");
            System.out.println("1. Add Department with Employees");
            System.out.println("2. View Department (Department → Employees)");
            System.out.println("3. View Employee (Employee → Department)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1:
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                System.out.println("Enter Department Name:");
                String deptName = sc.nextLine();

                Department department = new Department(deptName);

                System.out.println("How many employees?");
                int count = sc.nextInt();
                sc.nextLine();

                List<Employee> employees = new ArrayList<>();

                for (int i = 1; i <= count; i++) {
                    System.out.println("Enter Employee Name " + i + ":");
                    String empName = sc.nextLine();

                    Employee emp = new Employee(empName);
                    emp.setDepartment(department); 

                    employees.add(emp);
                }

                department.setEmployees(employees);

                session.persist(department);

                tx.commit();
                session.close();

                System.out.println("Department & Employees saved successfully!");
                break;

            case 2:
                Session session2 = factory.openSession();

                System.out.println("Enter Department ID:");
                int deptId = sc.nextInt();

                Department d = session2.get(Department.class, deptId);

                if (d != null) {
                    System.out.println("Department Name: " + d.getDeptName());

                    if (d.getEmployees() != null) {
                        System.out.println("Employees:");
                        for (Employee e : d.getEmployees()) {
                            System.out.println("Employee ID: " + e.getId()
                                    + ", Name: " + e.getName());
                        }
                    }
                } else {
                    System.out.println("Department not found!");
                }

                session2.close();
                break;

            case 3:
                Session session3 = factory.openSession();

                System.out.println("Enter Employee ID:");
                int empId = sc.nextInt();

                Employee e = session3.get(Employee.class, empId);

                if (e != null) {
                    System.out.println("Employee Name: " + e.getName());

                    if (e.getDepartment() != null) {
                        System.out.println("Belongs To Department: "
                                + e.getDepartment().getDeptName());
                    }
                } else {
                    System.out.println("Employee not found!");
                }

                session3.close();
                break;

            case 4:
                factory.close();
                sc.close();
                System.out.println("Exiting...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice!");
            }
        }
    }
}