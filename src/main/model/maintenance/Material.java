package main.model.maintenance;

public class Material {
	private String name;
	private double cost;
	
	public Material(){
		name = "";
		cost = 0;
	}
	
	public Material(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
}
