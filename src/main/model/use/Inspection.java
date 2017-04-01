package main.model.use;

import java.time.LocalDateTime;

public class Inspection {
	private LocalDateTime date;
	private String description;
	
	public Inspection(String description, LocalDateTime date) {
		this.description = description;
		this.date = date;
	}
	
	public String toString(){
		String str = "";
		str += "Date: " + date.toString();
		str += "\n" + description;
		return str;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
