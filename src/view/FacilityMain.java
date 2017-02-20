package view;
import dal.DataAccess;
import model.facility.FacilityInfo;

public class FacilityMain {
	public static void main (String args[]){
		//bolesta
		FacilityInfo f = new FacilityInfo();
		f.addNewFacility(1, 1, 1, "conference");
		FacilityInfo c = f.getFacilityInformation();
		System.out.println("building number: " + c.buildingNo + "\n" +
		"room number: "+ c.roomNo + "\n" + "capacity: "
		+ c.capacity + "\n" + "room type: " +c.type);
		//bolesta
	}
}
