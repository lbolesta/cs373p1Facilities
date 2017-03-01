package main.model.use;

import java.time.ZonedDateTime;

public class Inspection {
	private ZonedDateTime date;
	private String description;
	
	public Inspection(String description, ZonedDateTime date) {
		this.description = description;
		this.date = date;
	}
	
	public String toString(){
		String str = "";
		str += "Date: " + date.toString();
		str += "\n" + description;
		return str;
	}
	
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
