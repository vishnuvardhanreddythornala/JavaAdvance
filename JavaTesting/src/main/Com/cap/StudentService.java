package main.Com.cap;

public class StudentService {

    public boolean isEligible(int age) {
        if (age < 0) {
            throw new ArithmeticException("Age cannot be negative");
        }
        return age >= 18;
    }
}
