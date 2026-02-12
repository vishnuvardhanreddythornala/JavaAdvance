package main.Com.cap;

public class Calculator {
	
	public int add(int a, int b) {
		return a+b;
	}
	public int Sub(int a, int b) {
		return a-b;
	}
	
	public boolean isEven(int a) {
		return a%2==0;
	}
	public Integer isOdd(int a) {
		if(a%2!=0) return a;
		return null;
	}
	
	public int divide(int a, int b) {
		if (b == 0) {
	        throw new ArithmeticException("Cannot divide by zero");
	    }
		return a/b;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c = new Calculator();
		System.out.println(c.add(2, 3));
		System.out.println(c.Sub(2, 3));
		System.out.println(c.isEven(4));
		System.out.println(c.divide(4, 2));
	}

}