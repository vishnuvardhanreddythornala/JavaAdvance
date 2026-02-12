
/*2.Write a Java program to: Store integers in an ArrayList Count how many times a given number appears in the list */
import java.util.ArrayList;
import java.util.Scanner;

public class NumberCount {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(20);
        numbers.add(60);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int find = sc.nextInt();
        int count = 0;

        for (int num : numbers) {
            if (num == find) {
                count++;
            }
        }
        System.out.println(count);
        sc.close();

    }
}
