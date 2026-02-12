package Practice;
/*
 * 1.Write a Java program using Stream API to:
	Store integers in a list
	Filter and display only even numbers
 */
import java.util.Arrays;
import java.util.List;
public class StreamEvenNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
		nums.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

	}

}
