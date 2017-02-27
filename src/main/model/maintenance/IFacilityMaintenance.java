package main.model.maintenance;

import java.time.ZonedDateTime;
import java.util.List;

public interface IFacilityMaintenance<T> {
	public void makeFacilityMaintRequest(String description, ZonedDateTime start);
	public void scheduleMaintenance(T ticket, ZonedDateTime startTime, ZonedDateTime endTime);
	public float calcMaintenanceCostForFacility();
	public float calcProblemRateForFacility(ZonedDateTime since, ZonedDateTime til);
	public float calcDownTimeForFacility(ZonedDateTime since, ZonedDateTime til);
	public List<T> listMaintRequests();
	public List<T> listMaintenance();
	public List<String> listFacilityProblems();
}
