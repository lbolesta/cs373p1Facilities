package model.facility;

import java.util.List;

public interface IFacility {
	public List<?> listFacilities();
	public Object getFacilityInformation();
	public int requestAvailableCapacity();
	public void addNewFacility(IFacility f);
	public void addNewFacilityDetail(Object param, String detail);
	public void removeFacility();
}
