package Driver;
import java.util.Scanner;

import mercer.entity.Car;
import mercer.entity.DieselEngine;
import mercer.entity.Engine;
import mercer.entity.PetrolEngine;

public class menu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. PetrolEngine");
		System.out.println("2. DieselEngine");
		
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		
		Car car = new Car();
		Engine engine = null;
		
		switch (choice) {
		case 1:
			engine = new PetrolEngine();
			break;
		case 2:
			engine = new DieselEngine();
			break;
		}
		
		//using field injection
		/*
		car.engine = engine;
		car.engine.run();
		System.out.println(car.engine.getClass());
		*/
		
		//Using setter injection
		/*
		car.setEngine(engine);
		car.getEngine().run();
		System.out.println(car.getEngine().getClass());
		*/
		
		//Using constructor Injection
		Car car1 = new Car(engine);
		car1.getEngine().run();
		System.out.println(car1.getEngine().getClass());
		
		sc.close();
	}
	

}
