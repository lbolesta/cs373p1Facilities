package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class Campus implements IFacility<Building> {

	private List<Building> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	public Campus(String name){
		subunits = new ArrayList<Building>();
		this.info = new UnitInfo(name, 0);
		schedule = new ScheduleManager();
	}
	
	public ScheduleManager getScheduleManager() {
		return schedule;
	}
	
	public List<Building> listFacilities() {
		return subunits;
	}

	public int requestAvailableCapacity() {
		int availableCapacity = 0;
		for (Building subunit : subunits){
			availableCapacity += subunit.requestAvailableCapacity();
		}
		return availableCapacity;
	}
	
	public UnitInfo getFacilityInformation() {
		return info;
	}
	
	public Building getFacility(String name){
		for (Building b : listFacilities()){
			if (b.getFacilityInformation().getName() == name){
				return b;
			}
		}
		return null;
	}
	
	public void addNewFacility(Building facility) {
		if (!listFacilities().contains(facility)){
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
	
	public void removeFacility(Building facility) {
		if(listFacilities().contains(facility)){
			listFacilities().remove(facility);
		}
	}
}
