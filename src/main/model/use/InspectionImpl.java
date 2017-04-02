package main.model.use;

import java.time.LocalDateTime;

public class InspectionImpl implements Inspection {
	
	private LocalDateTime date;
	private String description;
	
	/*public String toString(){
		String str = "";
		str += "Date: " + date.toString();
		str += "\n" + description;
		return str;
	}*/
	
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
