package mercer.entity;

public class Car {
	private Engine engine;

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public Car() {
		
	}
	public Car(Engine engine) {
		super();
		this.engine = engine;
	}
}
