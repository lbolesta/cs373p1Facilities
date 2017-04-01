package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class Building implements IFacility<Room> {
	
	private List<Room> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	public Building(String name){
		subunits = new ArrayList<Room>();
		this.info = new UnitInfo(name, 0);
		schedule = new ScheduleManager();
	}
	
	public ScheduleManager getScheduleManager() {
		return schedule;
	}
	
	public List<Room> listFacilities() {
		return subunits;
	}

	public int requestAvailableCapacity() {
		int availableCapacity = 0;
		for (Room subunit : subunits){
			availableCapacity += subunit.requestAvailableCapacity();
		}
		return availableCapacity;
	}
	
	public Room getFacility(String name){
		for (Room r : listFacilities()){
			if (r.getFacilityInformation().getName().equals(name)){
				return r;
			}
		}
		return null;
	}
	
	public UnitInfo getFacilityInformation() {
		return info;
	}
	
	public void addNewFacility(Room facility) {
		if (!this.listFacilities().contains(facility)){
			listFacilities().add(facility);
			getFacilityInformation().setCapacity(requestAvailableCapacity());
		}
	}
	
	public void addNewFacilityDetail(String detail) {
		getFacilityInformation().addDetail(detail);
	}
	
	public void removeFacilityDetail(String detail){
		getFacilityInformation().removeDetail(detail);
	}
	
	public void removeFacility(Room facility) {
		if(listFacilities().contains(facility)){
			listFacilities().remove(facility);
		}
	}

}
