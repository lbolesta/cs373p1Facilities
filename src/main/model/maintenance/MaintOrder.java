package main.model.maintenance;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.ZonedDateTime;

public class MaintOrder {
	private FacilityProblem facilityProblem;
	private double cost;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private ArrayList<Material> materials;
	private ArrayList<Worker> workers;
	
	public MaintOrder(FacilityProblem facilityProblem, ZonedDateTime startTime, ZonedDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.setFacilityProblem(facilityProblem);
		cost = 0;
		materials = new ArrayList<Material>();
		workers = new ArrayList<Worker>();
	}

	public FacilityProblem getFacilityProblem() {
		return facilityProblem;
	}

	public void setFacilityProblem(FacilityProblem facilityProblem) {
		this.facilityProblem = facilityProblem;
		this.facilityProblem.setAssigned(true);
		this.facilityProblem.setEndTime(this.endTime);
	}

	public double calcCost() {
		cost = 0;
		float hours = (Duration.between(startTime, endTime)).toHours();
		for (Material material : materials) {
			cost += material.getCost();
		}
		for (Worker worker : workers) {
			cost += worker.getWage() * hours;
		}
		return cost;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public boolean setStartTime(ZonedDateTime startTime) {
		if(startTime.isBefore(this.endTime)){
			this.startTime = startTime;
			return true;
		}
		return false;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public boolean setEndTime(ZonedDateTime endTime) {
		if(endTime.isAfter(this.startTime)){
			this.endTime = endTime;
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
