package main.model.facility;

import java.util.List;

public interface IFacility<T> {
	public List<T> listFacilities();
	public UnitInfo getFacilityInformation();
	public int requestAvailableCapacity();
	public void addNewFacility(T facility);
	public void addNewFacilityDetail(String detail);
	public void removeFacility(T facility);
}
