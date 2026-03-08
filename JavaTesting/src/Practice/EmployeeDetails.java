package Practice;
//3.Write a Java program to:
//Store employee ID and name using HashMap
//Display all employee details
import java.util.HashMap;
import java.util.Map;
public class EmployeeDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> employees = new HashMap<>();
		employees.put(101,"vishnu");
		employees.put(102,"Vardhan");
		employees.put(103,"Reddy");
		System.out.println("Employee Details: ");
		for(Map.Entry<Integer,String> entry: employees.entrySet()) {
			System.out.println("Employee Id: "+entry.getKey() + ", Name: "+entry.getValue());
		}

	}

}
