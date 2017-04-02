package main.model.use;

import java.time.LocalDateTime;

public interface Reservation {
	
	public LocalDateTime getStartTime();
	public void setStartTime(LocalDateTime start);
	public LocalDateTime getEndTime();
	public void setEndTime(LocalDateTime end);
	public User getUser();
	public void setUser(User user);
	
}
