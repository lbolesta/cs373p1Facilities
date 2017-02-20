package model.maintenance;

import java.time.ZonedDateTime;

public class FacilityProblem {
	private String description;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	
	public FacilityProblem(String description) {
		this.description = description;
		this.startTime = ZonedDateTime.now();
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ZonedDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartDate(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}
}
