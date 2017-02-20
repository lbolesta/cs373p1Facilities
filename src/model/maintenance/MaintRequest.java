package model.maintenance;

import java.time.ZonedDateTime;

public class MaintRequest {
	private FacilityProblem facilityProblem;
	private ZonedDateTime requestDate;
	
	public MaintRequest(String description) {
		this.facilityProblem = new FacilityProblem(description);
		this.requestDate = ZonedDateTime.now();
	}
	
	public FacilityProblem getFacilityProblem() {
		return facilityProblem;
	}
	
	public void setFacilityProblem(FacilityProblem facilityProblem) {
		this.facilityProblem = facilityProblem;
	}

	public ZonedDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(ZonedDateTime requestDate) {
		this.requestDate = requestDate;
	}
	
	
}
