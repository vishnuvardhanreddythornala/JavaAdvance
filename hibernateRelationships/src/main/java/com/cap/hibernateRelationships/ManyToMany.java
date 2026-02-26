package com.cap.hibernateRelationships;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.cap.entity.Course;
import com.cap.entity.Student;

public class ManyToMany {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernatemanytomany.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MANY TO MANY MENU =====");
            System.out.println("1. Add Student with Courses");
            System.out.println("2. View All Students");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Student Courses");
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

                        System.out.print("Enter Student Name: ");
                        String studentName = sc.nextLine();

                        Student student = new Student(studentName);

                        System.out.print("How many courses? ");
                        int n = sc.nextInt();
                        sc.nextLine();

                        List<Course> courses = new ArrayList<>();

                        for (int i = 1; i <= n; i++) {
                            System.out.print("Enter Course Name " + i + ": ");
                            String courseName = sc.nextLine();

                            Course course = new Course(courseName);
                            courses.add(course);
                        }

                        student.setCourses(courses);

                        tx = session.beginTransaction();
                        session.persist(student);
                        tx.commit();

                        System.out.println("Student & Courses Added!");
                        break;
                    case 2:
                        Query<Student> query = session.createQuery("from Student", Student.class);
                        List<Student> students = query.list();

                        System.out.println("\n--- STUDENT LIST ---");

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            students.forEach(s ->
                                System.out.println("ID: " + s.getId() + ", Name: " + s.getName())
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine();

                        Student existingStudent = session.get(Student.class, sid);

                        if (existingStudent == null) {
                            System.out.println("Student Not Found!");
                            break;
                        }

                        System.out.print("Enter Course Name to Enroll: ");
                        String cname = sc.nextLine();

                        tx = session.beginTransaction();

                        Query<Course> courseQuery = session.createQuery(
                                "from Course where name = :name", Course.class);
                        courseQuery.setParameter("name", cname);

                        Course existingCourse = courseQuery.uniqueResult();

                        if (existingCourse == null) {
                            existingCourse = new Course(cname);
                            session.persist(existingCourse);
                        }

                        existingStudent.getCourses().add(existingCourse);

                        session.merge(existingStudent);

                        tx.commit();

                        System.out.println("Student Enrolled!");
                        break;
                    case 4:
                        System.out.print("Enter Student ID: ");
                        int viewId = sc.nextInt();

                        Student viewStudent = session.get(Student.class, viewId);

                        if (viewStudent == null) {
                            System.out.println("Student Not Found!");
                            break;
                        }

                        System.out.println("\nStudent: " + viewStudent.getName());

                        List<Course> studentCourses = viewStudent.getCourses();

                        if (studentCourses == null || studentCourses.isEmpty()) {
                            System.out.println("No courses enrolled.");
                        } else {
                            studentCourses.forEach(c ->
                                System.out.println("Course ID: " + c.getId() +
                                                   ", Name: " + c.getName())
                            );
                        }
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
