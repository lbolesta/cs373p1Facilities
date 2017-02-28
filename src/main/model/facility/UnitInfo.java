package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class UnitInfo {
	private int capacity;
	private String name;
	private List<String> details;
	
	public UnitInfo(String name, int capacity){
		setName(name);
		setCapacity(capacity);
		details = new ArrayList<String>();
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	//TODO check against duplicate names
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDetails() {
		return details;
	}
	
	public void addDetail(String detail) {
		if (!details.contains(detail)){
			details.add(detail);
		}
	}
	
	public void removeDetail(String detail) {
		if (details.contains(detail)){
			details.remove(detail);
		}
	}
	
}
