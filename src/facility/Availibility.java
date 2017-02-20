package facility;

public interface Availibility {
	public void listAvailable();
	public void listInUse();
	public void listUnderMaintenance();
	public void assignFacilityToUse();
	public void reserveFacility();
	public void makeMaintenanceRequest();
	public void scheduleMaintenance();
	public void listMaintenanceRequests();
	public void listProblems();
	
}
