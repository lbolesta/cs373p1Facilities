package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class Room implements IFacility<Room> {

	private List<Room> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	public Room(String name, int capacity){
		subunits = new ArrayList<Room>();
		this.info = new UnitInfo(name, capacity);
		schedule = new ScheduleManager();
	}
	
	public UnitInfo getInfo() {
		return info;
	}
	
	public ScheduleManager getScheduleManager() {
		return schedule;
	}
	
	public List<Room> listFacilities() {
		if(!subunits.contains(this)){
			subunits.add(this);
		}
		return subunits;
	}

	public int requestAvailableCapacity() {
		addNewFacility(this);
		return info.getCapacity();
	}
	
	public UnitInfo getFacilityInformation() {
		return getInfo();
	}
	
	public void addNewFacility(Room facility) {
		/*if (!listFacilities().contains(facility)){
			listFacilities().add(facility);
			getInfo().setCapacity(requestAvailableCapacity());
		}*/
		return;
	}
	
	public void addNewFacilityDetail(String detail) {
		getInfo().addDetail(detail);
	}
	
	public void removeFacilityDetail(String detail){
		getInfo().removeDetail(detail);
	}
	
	public void removeFacility(Room facility) {
		/*if(listFacilities().contains(facility)){
			listFacilities().remove(facility);
		}*/
		return;
	}
}
