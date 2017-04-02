package main.model.use;

import java.time.LocalDateTime;

public interface Inspection {
	
	public LocalDateTime getDate();
	public void setDate(LocalDateTime date);
	public String getDescription();
	public void setDescription(String description);
	
}
