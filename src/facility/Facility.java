package facility;

public interface Facility {
	//public void listFacilitiesInUse ();//choose params and returns 
	//public void listAvailableFacilities (); //choose params
	//public void listFacilitiesInMaintenance (); //choose params
	public FacilityInfo getFacilityInformation(); //choose params and returns 
	public void requestAvailableCapacity(); //choose params and returns 
	public void addNewFacility(int q, int r, int s, String p); //choose params and returns 
	public void addFacilityDetail(); 
	public void removeFacility();  //choose params and returns 
	public void listFacilities();

}
