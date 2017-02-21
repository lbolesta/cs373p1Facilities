package main.model.facility;

import java.util.List;

public class UnitInfo {
	private int capacity;
	private String name;
	private int idNumber;
	private List<String> details;
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	public void addDetail(String detail) {
		details.add(detail);
	}
	
	public void removeDetail(String detail) {
		details.remove(detail);
	}
	
}
