package main.model.use;

import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private User user;
	
	//TODO: don't construct if startTime <= endTime
	public Reservation(LocalDateTime startTime, LocalDateTime endTime, User user){
		this.setStartTime(startTime);
		if( !this.setEndTime(endTime) ) {
			return;
		}
		this.user = user;
	}
	
	private boolean isBefore(LocalDateTime a, LocalDateTime b){
		return ( b==null || a.isBefore(b) );
	}
	
	public String toString(){
		String str = "";
		str += "Start time: " + startTime.toString();
		str += "\nEnd time: " + endTime.toString();
		str += "\nUser: " + user.getName();
		return str;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public boolean setStartTime(LocalDateTime startTime) {
		if (isBefore(startTime, endTime)){
			this.startTime = startTime;
			return true;
		}
		return false;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public boolean setEndTime(LocalDateTime endTime) {
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
