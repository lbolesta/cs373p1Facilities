package main.model.maintenance;

public class WorkerImpl implements Worker {
	private String name;
	private double wage;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getWage() {
		return wage;
	}
	
	public void setWage(double wage) {
		this.wage = wage;
	}
}
