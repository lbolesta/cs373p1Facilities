package facilityMaint;

public interface FacilityMaintenance {

	public void makeFacilityMaintRequest(); //choose params and return
	public void scheduleMaintenance();//choose params and return
	public void calcMaintenanceCostForFacility();//choose params and return
	public void calcProblemRateForFacility();//choose params and return
	public void calcDownTimeForFacility();//choose params and return
	public void listMaintRequests();//choose params and return
	public void listMaintenance();//choose params and return
	public void listFacilityProblems(); //choose params and return
}
