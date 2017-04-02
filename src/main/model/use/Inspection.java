package main.model.use;

import java.time.LocalDate;

public interface Inspection {
	
	public LocalDate getDate();
	public void setDate(LocalDate date);
	public String getDescription();
	public void setDescription(String description);
	
}
