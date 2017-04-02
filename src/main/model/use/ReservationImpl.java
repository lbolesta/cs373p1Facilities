package main.model.use;

import java.time.LocalDateTime;

public class ReservationImpl implements Reservation {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private User user;
	
	//TODO: don't construct if startTime <= endTime
	/*public ReservationImpl(LocalDateTime startTime, LocalDateTime endTime, User user){
		this.setStartTime(startTime);
		if( !this.setEndTime(endTime) ) {
			return;
		}
		this.user = user;
	}*/
	
	private boolean isInOrder(LocalDateTime a, LocalDateTime b){
		return b==null || a.isBefore(b);
	}
	
	private LocalDateTime validateTime(LocalDateTime time, int pos) throws Exception {
		LocalDateTime start = startTime;
		LocalDateTime end = endTime;
		switch (pos) {
			case 0: start = time;
					break;
			case 1:	end = time;
					break;
			default: throw new Exception("ERROR: Set pos between 0 and 1");
		}
		
		if (isInOrder(start, end)) {
			return time;
		} else {
			throw new Exception("ERROR: Tried to insert time out of order");
		}
	}
	
	/*public String toString(){
		String str = "";
		str += "Start time: " + startTime.toString();
		str += "\nEnd time: " + endTime.toString();
		str += "\nUser: " + user.getName();
		return str;
	}*/
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime start) {
		try {
			this.startTime = validateTime(start, 0);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime end) {
		try {
			this.endTime = validateTime(end, 1);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
