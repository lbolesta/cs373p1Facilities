package main.model.maintenance;

import java.time.ZonedDateTime;

public class MaintRequest {
	private FacilityProblem facilityProblem;
	private ZonedDateTime requestTime;
	
	public MaintRequest(String description) {
		this.facilityProblem = new FacilityProblem(description, requestTime);
		this.requestTime = ZonedDateTime.now();
	}
	
	public FacilityProblem getFacilityProblem() {
		return facilityProblem;
	}
	
	public void setFacilityProblem(FacilityProblem facilityProblem) {
		this.facilityProblem = facilityProblem;
	}

	public ZonedDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(ZonedDateTime requestDate) {
		this.requestTime = requestDate;
	}
	
	
}
