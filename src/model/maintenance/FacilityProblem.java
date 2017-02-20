package model.maintenance;

import java.time.ZonedDateTime;

public class FacilityProblem {
	private String description;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private boolean assigned;
	
	public FacilityProblem(String description) {
		this.description = description;
		startTime = ZonedDateTime.now();
		assigned = false;
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
	
	public void setStartDate() {
		this.startTime = ZonedDateTime.now();
	}
	
	public void setStartDate(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		if (endTime == null) {
			return ZonedDateTime.now();
		} else {
			return endTime;
		}
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}
