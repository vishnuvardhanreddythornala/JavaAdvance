package Practice;
/*
 * 3.Write a Java program using Stream API to:
	Store integers in a list
	Count how many numbers are greater than 50
 */
import java.util.List;
import java.util.Arrays;
public class StreamCountNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = Arrays.asList(10,25,56,50,76,98);
		long count = nums.stream().filter(n -> n>50).count();
		System.out.println("Count numbers greater than 50: " + count);
	}

}
