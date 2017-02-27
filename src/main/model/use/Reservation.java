package main.model.use;

import java.time.ZonedDateTime;

public class Reservation {
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private User user;
	
	//TODO: don't construct if startTime <= endTime
	public Reservation(ZonedDateTime startTime, ZonedDateTime endTime, User user){
		this.setStartTime(startTime);
		if( !this.setEndTime(endTime) ) {
			return;
		}
		this.user = user;
	}
	
	private boolean isBefore(ZonedDateTime a, ZonedDateTime b){
		return ( b==null || a.isBefore(b) );
	}
	
	public ZonedDateTime getStartTime() {
		return startTime;
	}
	
	public boolean setStartTime(ZonedDateTime startTime) {
		if (isBefore(startTime, endTime)){
			this.startTime = startTime;
			return true;
		}
		return false;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public boolean setEndTime(ZonedDateTime endTime) {
		if (isBefore(startTime, endTime)){
			this.endTime = endTime;
			return true;
		}
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
