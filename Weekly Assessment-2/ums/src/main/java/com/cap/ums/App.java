package com.cap.ums;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cap.entity.Course;
import com.cap.entity.Department;
import com.cap.entity.Student;
import com.cap.entity.StudentIdCard;

public class App{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        int choice;

        do {
            System.out.println("\n UNIVERSITY MANAGEMENT SYSTEM ");
            System.out.println("1. Insert Data");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    insertData(session, sc);
                    break;

                case 2:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 2);

        session.close();
        sc.close();
    }

    private static void insertData(Session session, Scanner sc) {

        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter Department Name: ");
            String deptName = sc.nextLine();

            Department dept = new Department();
            dept.setName(deptName);

            
            System.out.print("Enter Student 1 Name: ");
            String s1Name = sc.nextLine();

            System.out.print("Enter Student 2 Name: ");
            String s2Name = sc.nextLine();

            Student s1 = new Student();
            s1.setName(s1Name);

            Student s2 = new Student();
            s2.setName(s2Name);

           
            s1.setDepartment(dept);
            s2.setDepartment(dept);

            dept.getStudents().add(s1);
            dept.getStudents().add(s2);

           
            System.out.print("Enter ID Card Number for " + s1Name + ": ");
            String cardNum1 = sc.nextLine();

            System.out.print("Enter ID Card Number for " + s2Name + ": ");
            String cardNum2 = sc.nextLine();

            StudentIdCard card1 = new StudentIdCard();
            card1.setCardNumber(cardNum1);

            StudentIdCard card2 = new StudentIdCard();
            card2.setCardNumber(cardNum2);

            s1.setIdCard(card1);
            s2.setIdCard(card2);

            card1.setStudent(s1);
            card2.setStudent(s2);

            
            System.out.print("Enter Course 1 Name: ");
            String c1Name = sc.nextLine();

            System.out.print("Enter Course 2 Name: ");
            String c2Name = sc.nextLine();

            Course c1 = new Course();
            c1.setCourseName(c1Name);

            Course c2 = new Course();
            c2.setCourseName(c2Name);

            
            s1.getCourses().add(c1);
            s1.getCourses().add(c2);

            s2.getCourses().add(c1);

            c1.getStudents().add(s1);
            c1.getStudents().add(s2);
            c2.getStudents().add(s1);
            session.persist(dept);

            tx.commit();
            System.out.println("Data Inserted Successfully");

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }
}

