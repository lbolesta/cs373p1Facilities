package main.model.facility;

import java.util.List;

abstract public class Facility implements IFacility<Facility> {
	
	public abstract UnitInfo getInfo();
	public abstract ScheduleManager getScheduleManager();

	abstract public List<Facility> listFacilities();
	abstract public int requestAvailableCapacity();
	
	public UnitInfo getFacilityInformation() {
		return getInfo();
	}
	
	public void addNewFacility(Facility facility) {
		if (!listFacilities().contains(facility)){
			listFacilities().add(facility);
			getInfo().setCapacity(requestAvailableCapacity());
		}
	}
	
	public void addNewFacilityDetail(String detail) {
		getInfo().addDetail(detail);
	}
	
	public void removeFacilityDetail(String detail){
		getInfo().removeDetail(detail);
	}
	
	public void removeFacility(IFacility<UnitInfo> facility) {
		if(listFacilities().contains(facility)){
			listFacilities().remove(facility);
		}
	}
}
