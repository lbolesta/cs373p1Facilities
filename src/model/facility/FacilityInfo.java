
package model.facility;
import java.awt.List;
import java.util.ArrayList;

import dal.DataAccess;
//bolesta
public class FacilityInfo implements Facility {
	
	public int buildingNo, roomNo, capacity;; 
	public String type; //classroom, conference room, office, etc.
	public ArrayList<FacilityInfo> fac = new ArrayList<FacilityInfo>();
	
	public FacilityInfo(){}//default constructor
	
	public FacilityInfo(int b, int r, int c, String t){
		this.buildingNo = b; 
		this.roomNo = r; 
		this.capacity = c; 
		this.type = t; 
	} 

	
	@Override
	public void listFacilities(){
		
	}
	
	@Override
	public void addNewFacility(int q, int r, int s, String p) {
		fac.add(new FacilityInfo(q, r, s, p));
		
	}
	
	@Override
	public FacilityInfo getFacilityInformation() {
		FacilityInfo x = fac.get(0);
		return x; 
	}

	@Override
	public void requestAvailableCapacity() {
		
		
	}

	

	@Override
	public void addFacilityDetail() {
		
		
	}

	@Override
	public void removeFacility() {
		
		
	}

}//bolesta
