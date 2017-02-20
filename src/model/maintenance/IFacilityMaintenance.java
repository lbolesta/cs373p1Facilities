package model.maintenance;

import java.time.ZonedDateTime;
import java.util.List;
import model.facility.IFacility;

public interface IFacilityMaintenance<S,T,W> {
	public void makeFacilityMaintRequest(String description);
	public void scheduleMaintenance(S request, ZonedDateTime startTime);
	public float calcMaintenanceCostForFacility();
	public float calcProblemRateForFacility();
	public int calcDownTimeForFacility();
	public List<S> listMaintRequests();
	public List<T> listMaintenance();
	public List<W> listFacilityProblems();
}
