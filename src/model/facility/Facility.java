package model.facility;

import java.util.List;

abstract public class Facility implements IFacility<UnitInfo> {
	
	public abstract UnitInfo getInfo();
	public abstract ScheduleManager getScheduleManager();

	abstract public List<IFacility<UnitInfo>> listFacilities();
	abstract public int requestAvailableCapacity();
	
	public UnitInfo getFacilityInformation() {
		return getInfo();
	}
	
	public void addNewFacility(IFacility<UnitInfo> facility) {
		listFacilities().add(facility);
	}
	
	public void addNewFacilityDetail(String detail) {
		getInfo().addDetail(detail);
	}
	
	public void removeFacility(IFacility<UnitInfo> facility) {
		if(listFacilities().contains(facility)){
			listFacilities().remove(facility);
		}
	}
}
