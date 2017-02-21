package main.model.facility;

import java.util.List;

public interface IFacility<T> {
	public List<IFacility<T>> listFacilities();
	public T getFacilityInformation();
	public int requestAvailableCapacity();
	public void addNewFacility(IFacility<T> facility);
	public void addNewFacilityDetail(String detail);
	public void removeFacility(IFacility<T> facility);
}
