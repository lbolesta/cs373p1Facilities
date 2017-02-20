package model.facility;

import java.util.List;

import model.maintenance.MaintenanceClient;

abstract public class Facility implements IFacility<UnitInfo> {
	
	public abstract UnitInfo getInfo();
	public abstract MaintenanceClient getMaintenanceClient();

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
		listFacilities().remove(facility);
	}
}
