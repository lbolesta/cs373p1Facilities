package main.model.maintenance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaintTicket {
	private String description;
	private List<Material> materials = new ArrayList<Material>();
	private Set<Worker> workers = new HashSet<Worker>();
	
	private LocalDateTime requestTime;
	private LocalDateTime orderTime;
	private LocalDateTime resolveTime;
	
	/*public MaintTicket(String description, LocalDateTime requestTime){
		this.description = description;
		this.requestTime = requestTime;
	}*/
	
	private LocalDateTime validateTime(LocalDateTime time, int pos) throws Exception {
		LocalDateTime request = requestTime;
		LocalDateTime order = orderTime;
		LocalDateTime resolve = resolveTime;
		switch (pos) {
			case 0: request = time;
					break;
			case 1:	order = time;
					break;
			case 2: resolve = time;
					break;
		}
		
		if (isInOrder(request, order) && isInOrder(order, resolve)) {
			return time;
		} else {
			throw new Exception("ERROR: Tried to insert time out of order");
		}
	}
	
	private boolean isInOrder(LocalDateTime a, LocalDateTime b) {
		if (a == null && b == null) {
			return true;
		} else if (a != null && b == null) {
			return true;
		} else if (a == null && b != null) {
			return false;
		}
		return a.isBefore(b);
	}


	
	/*public String toString(){
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
	}*/
	
	public String getState(LocalDateTime relativeTime) {
		String state = "";
		if (orderTime == null) {
			state = "REQUEST";
		} else if (orderTime != null && isInOrder(relativeTime, resolveTime)){
			state = "ORDER";
		} else if (isInOrder(resolveTime, relativeTime)){
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
	

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime){
		try {
			this.requestTime = validateTime(requestTime, 0);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		try {
			this.orderTime = validateTime(orderTime, 1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public LocalDateTime getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(LocalDateTime resolveTime) {
		try {
			this.resolveTime = validateTime(resolveTime, 2);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public List<Material> getMaterials() {
		return materials;
	}
	
	public void addMaterial(Material material) {
		this.materials.add(material);
	}
	
	public void removeMaterial(Material material) {
		this.materials.remove(material);
	}

	public Set<Worker> getWorkers() {
		return workers;
	}
	
	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}
	
	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}

}
