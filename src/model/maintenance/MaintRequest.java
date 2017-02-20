package model.maintenance;

import java.time.ZonedDateTime;

public class MaintRequest {
	private FacilityProblem facilityProblem;
	private String description;
	private ZonedDateTime requestDate;
	private boolean assigned;
	
	public MaintRequest(String description) {
		this.description = description;
		this.facilityProblem = new FacilityProblem(description);
		this.requestDate = ZonedDateTime.now();
		this.assigned = false;
	}
	
	public FacilityProblem getFacilityProblem() {
		return facilityProblem;
	}
	
	public void setFacilityProblem(FacilityProblem facilityProblem) {
		this.facilityProblem = facilityProblem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(ZonedDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	
	
}
