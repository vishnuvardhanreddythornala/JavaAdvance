import java.util.ArrayList;

public class StudentList {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        students.add("Thornala");
        students.add("Vishnu");
        students.add("Vardhan");
        students.add("Reddy");
        System.out.println("Student Names:");
        for (String name : students) {
            System.out.println(name);
        }
    }
}
