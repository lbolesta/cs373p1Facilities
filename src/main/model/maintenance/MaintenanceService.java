package main.model.maintenance;

import java.time.LocalDateTime;
import java.util.List;

public interface MaintenanceService<T> {
	public void makeFacilityMaintRequest(String description, LocalDateTime start);
	public void scheduleMaintenance(T ticket, LocalDateTime startTime, LocalDateTime endTime);
	public float calcMaintenanceCostForFacility();
	public float calcProblemRateForFacility(LocalDateTime since, LocalDateTime til);
	public float calcDownTimeForFacility(LocalDateTime since, LocalDateTime til);
	public List<T> listMaintRequests();
	public List<T> listMaintenance();
	public List<String> listFacilityProblems();
	public MaintTicket getMaintTicket(String description);
}
