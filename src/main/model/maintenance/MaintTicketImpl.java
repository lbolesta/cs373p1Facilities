package main.model.maintenance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaintTicketImpl implements MaintTicket {
	private String description;
	private List<Material> materials = new ArrayList<Material>();
	private Set<Worker> workers = new HashSet<Worker>();
	
	private LocalDateTime requestedAt;
	private LocalDateTime orderedAt;
	private LocalDateTime resolvedAt;
	
	
	private LocalDateTime validateTime(LocalDateTime time, int pos) throws Exception {
		LocalDateTime request = requestedAt;
		LocalDateTime order = orderedAt;
		LocalDateTime resolve = resolvedAt;
		switch (pos) {
			case 0: request = time;
					break;
			case 1:	order = time;
					break;
			case 2: resolve = time;
					break;
			default: throw new Exception("ERROR: Set pos between 0 and 2");
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
	
	@Override
	public String getState(LocalDateTime atTime) {
		String state = "";
		if (orderedAt == null) {
			state = "REQUEST";
		} else if (orderedAt != null && isInOrder(atTime, resolvedAt)){
			state = "ORDER";
		} else if (isInOrder(resolvedAt, atTime)){
			state = "RESOLVED";
		}
		return state;
	}
	
	@Override
	public double calcCost() {
		double cost = 0;
		float hours = (Duration.between(orderedAt, resolvedAt)).toHours();
		
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
		return requestedAt;
	}

	public void setRequestTime(LocalDateTime requestTime){
		try {
			this.requestedAt = validateTime(requestTime, 0);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public LocalDateTime getOrderTime() {
		return orderedAt;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		try {
			this.orderedAt = validateTime(orderTime, 1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public LocalDateTime getResolveTime() {
		return resolvedAt;
	}

	public void setResolveTime(LocalDateTime resolveTime) {
		try {
			this.resolvedAt = validateTime(resolveTime, 2);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Material> getMaterials() {
		return materials;
	}
	
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
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
	
	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
	
	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}
	
	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}

}
