package main.model.maintenance;

import java.time.LocalDateTime;
import java.util.List;

public interface IFacilityMaintenance<T> {
	public void makeFacilityMaintRequest(String description, LocalDateTime start);
	public boolean scheduleMaintenance(T ticket, LocalDateTime startTime, LocalDateTime endTime);
	public float calcMaintenanceCostForFacility();
	public float calcProblemRateForFacility(LocalDateTime since, LocalDateTime til);
	public float calcDownTimeForFacility(LocalDateTime since, LocalDateTime til);
	public List<T> listMaintRequests();
	public List<T> listMaintenance();
	public List<String> listFacilityProblems();
}
