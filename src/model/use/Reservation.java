package model.use;

import java.time.ZonedDateTime;

public class Reservation {
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
