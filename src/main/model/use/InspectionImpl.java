package main.model.use;

import java.time.LocalDate;

public class InspectionImpl implements Inspection {
	
	private LocalDate date;
	private String description;
	
	/*public String toString(){
		String str = "";
		str += "Date: " + date.toString();
		str += "\n" + description;
		return str;
	}*/
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
