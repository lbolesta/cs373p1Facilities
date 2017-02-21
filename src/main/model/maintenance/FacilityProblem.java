package main.model.maintenance;

import java.time.ZonedDateTime;

public class FacilityProblem {
	private String description;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private boolean assigned;
	
	public FacilityProblem(String description, ZonedDateTime startTime){
		this.description = description;
		this.startTime = startTime;
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
	
	public boolean setStartTime(ZonedDateTime startTime) {
		if(endTime == null || startTime.isBefore(endTime)) {
			this.startTime = startTime;
			return true;
		}
		return false;
	}

	public ZonedDateTime getEndTime() {
		if (endTime == null) {
			return ZonedDateTime.now();
		} else {
			return endTime;
		}
	}

	public boolean setEndTime(ZonedDateTime endTime) {
		if(endTime.isAfter(startTime)){
			this.endTime = endTime;
			return true;
		}
		return false;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}
