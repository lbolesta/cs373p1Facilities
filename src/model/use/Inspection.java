package model.use;

import java.time.ZonedDateTime;

public class Inspection {
	private ZonedDateTime date;
	private String description;
	
	public ZonedDateTime getDate() {
		return date;
	}
	
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
