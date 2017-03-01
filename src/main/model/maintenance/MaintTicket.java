package main.model.maintenance;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintTicket {
	private String description;
	private ZonedDateTime requestTime;
	private ZonedDateTime orderTime;
	private ZonedDateTime resolveTime;
	private ArrayList<Material> materials;
	private ArrayList<Worker> workers;
	
	public MaintTicket(String description, ZonedDateTime requestTime){
		this.description = description;
		this.requestTime = requestTime;
		materials = new ArrayList<Material>();
		workers = new ArrayList<Worker>();
	}
	
	public String toString(){
		String str = "";
		str += "Description: " + description;
		str += "\nRequest time: " + requestTime.toString();
		if(orderTime != null){
			str += "\nOrder time: " + orderTime.toString();
		}
		if(resolveTime != null){
			str += "\nResolve time: " + resolveTime.toString();
		}
		return str;
	}
	
	private boolean checkTime(ZonedDateTime req, ZonedDateTime ord, ZonedDateTime res){
		return (isBefore(req, ord) && isBefore(ord, res));
	}
	
	private boolean isBefore(ZonedDateTime a, ZonedDateTime b){
		return (a != null && (b == null || a.isBefore(b)) );
	}
	
	public String getState(ZonedDateTime relativeTime) {
		String state = "";
		if (orderTime == null) {
			state = "REQUEST";
		} else if (orderTime != null && isBefore(relativeTime, resolveTime)){
			state = "ORDER";
		} else if (isBefore(resolveTime, relativeTime)){
			state = "RESOLVED";
		}
		return state;
	}
	
	public double calcCost() {
		double cost = 0;
		float hours = (Duration.between(orderTime, resolveTime)).toHours();
		for (Material material : materials) {
			cost += material.getCost();
		}
		for (Worker worker : workers) {
			cost += worker.getWage() * hours;
		}
		return cost;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getRequestTime() {
		return requestTime;
	}

	public boolean setRequestTime(ZonedDateTime requestTime) {
		if (orderTime == null || checkTime(requestTime, orderTime, resolveTime)) {
			this.requestTime = requestTime;
			return true;
		}
		return false;
	}

	public ZonedDateTime getOrderTime() {
		return orderTime;
	}

	public boolean setOrderTime(ZonedDateTime orderTime) {
		if(checkTime(requestTime, orderTime, resolveTime)){
			this.orderTime = orderTime;
			return true;
		}
		return false;
	}

	public ZonedDateTime getResolveTime() {
		return resolveTime;
	}

	public boolean setResolveTime(ZonedDateTime resolveTime) {
		if(checkTime(requestTime, orderTime, resolveTime)){
			this.resolveTime = resolveTime;
			return true;
		}
		return false;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = (ArrayList<Material>) materials;
	}
	
	public void addMaterial(Material material) {
		this.materials.add(material);
	}
	
	public void removeMaterial(Material material) {
		this.materials.remove(material);
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = (ArrayList<Worker>) workers;
	}
	
	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}
	
	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}

}
