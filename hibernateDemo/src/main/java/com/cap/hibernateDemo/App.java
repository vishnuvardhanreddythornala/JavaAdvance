package com.cap.hibernateDemo;

/**
 * Hello world!
 */
import java.util.Scanner;
import com.cap.hibernateDemo.entity.student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
    	System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");
    	
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
        	do {
        		System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
        		System.out.println("1. Create Student");
        		System.out.println("2. Read Student");
        		System.out.println("3. Update Student");
        		System.out.println("4. Delete Student");
        		System.out.println("5. Cache Demo");   //new
        		System.out.println("5. Exit");
        		
        		choice  = sc.nextInt();
        		
        		switch(choice) {
        		case 1:
        			sc.nextLine();
        			System.out.print("Enter Name: ");
        			String name = sc.next();
        			
        			System.out.print("Enter Marks: ");
        			int marks = sc.nextInt();
        			
        			System.out.print("Enter Department Name: ");
        			String department = sc.next();
        			
        			createStudent(factory,name,marks,department);
        			break;
        			
        		case 2:
        			System.out.print("Student Id: ");
        			int readId = sc.nextInt();
        			
        			readStudentById(factory, readId);
                    break;
                    
        		case 3:
        			System.out.print("Enter Student Id to Update: ");
        			int updateId = sc.nextInt();
        			
        			System.out.print("Enter name to Update: ");
        			String newName = sc.next();
        			
        			System.out.print("Enter marks to Update: ");
        			 int newMarks = sc.nextInt();

                     updateStudentMarks(factory, updateId, newName,newMarks);
                     break;
        		case 4:
                     System.out.print("Enter student ID to delete: ");
                     Long deleteId = sc.nextLong();

                     deleteStudentById(factory, deleteId);
                     break;

        		case 5:
        		    System.out.print("Enter Student Id for Cache Demo: ");
        		    int cacheId = sc.nextInt();
        		    cacheDemo(factory, cacheId);
        		    break;

        		case 6:
        		    System.out.println("Exiting application...");
        		    break;


                 default:
                     System.out.println("Invalid choice!");
        			
        		}
        	}while (choice != 6);
        } finally {
            sc.close();
            factory.close();
        }
        
    }

    private static void createStudent(SessionFactory factory, String name, int marks,String department){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try{
            student st = new student(name, marks,department);
            session.persist(st);
            tx.commit();
            System.out.println("CREATE: Student saved - " + st);
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    private static void readStudentById(SessionFactory factory, int id){
        Session session = factory.openSession();
        try {
        	student st = session.get(student.class, id);
        	
        	if(st != null) {
        		System.out.println("Read: Student found : Student Name - "+st.getName());
        		System.out.println("Read: Student found : Student Id - "+st.getMarks());
        		
        	}else {
        		System.out.println("Read: Student not found with id "+id);
        	}
        	
        }catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		session.close();
    	}
    }
    private static void deleteStudentById(SessionFactory factory, Long id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            student st = session.get(student.class, id);

            if (st != null) {
                session.remove(st);
                tx.commit();
                System.out.println("DELETE: Student removed - " + st);
            } else {
                System.out.println("DELETE: Student not found with id " + id);
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
    private static void updateStudentMarks(SessionFactory factory, int id,String name, int newMarks) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            student st = session.get(student.class, id);

            if (st != null) {
            	st.setName(name);
                st.setMarks(newMarks);
                tx.commit();
                System.out.println("UPDATE: Student updated - " + st.getName());
            } else {
                System.out.println("UPDATE: Student not found with id " + id);
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    //cacheDemo method
    private static void cacheDemo(SessionFactory factory, int id) {

        System.out.println("\n--- FIRST FETCH (DB HIT) ---");
        Session s1 = factory.openSession();
        student st1 = s1.get(student.class, id);

        if (st1 != null)
            System.out.println("Student Name: " + st1.getName());
        else
            System.out.println("Student not found");

        s1.close();

        System.out.println("\n--- SECOND FETCH (CACHE HIT) ---");
        Session s2 = factory.openSession();
        student st2 = s2.get(student.class, id);

        if (st2 != null)
            System.out.println("Student Name: " + st2.getName());
        else
            System.out.println("Student not found");

        s2.close();
    }

    



}
