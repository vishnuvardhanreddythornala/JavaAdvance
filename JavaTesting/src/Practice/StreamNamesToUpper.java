package Practice;
/*
 * 2.Write a Java program using Stream API to:
	Store names in a list
	Convert all names to uppercase
	Display the result
 */
import java.util.Arrays;
import java.util.List;
public class StreamNamesToUpper {
	public static void main(String[] args) {
	List<String> names = Arrays.asList("vishnu","vardhan","reddy");
	names.stream().map(name -> name.toUpperCase()).forEach(System.out::println);
}
}
