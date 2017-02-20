package model.maintenance;

import java.util.List;
import java.time.ZonedDateTime;

public class MaintOrder {
	private FacilityProblem facilityProblem;
	private float cost;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private List<Material> materials;
	private List<Worker> workers;
	
	public MaintOrder(FacilityProblem facilityProblem, ZonedDateTime startTime){
		this.setFacilityProblem(facilityProblem);
		this.startTime = startTime;
	}

	public FacilityProblem getFacilityProblem() {
		return facilityProblem;
	}

	public void setFacilityProblem(FacilityProblem facilityProblem) {
		this.facilityProblem = facilityProblem;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
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

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	
	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}
	
	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}
}
