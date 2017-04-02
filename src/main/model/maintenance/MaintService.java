package main.model.maintenance;

import java.time.LocalDateTime;
import java.util.List;

public interface MaintService {
	public void makeFacilityMaintRequest(String description, LocalDateTime start);
	public void scheduleMaintenance(MaintTicket ticket, LocalDateTime start, LocalDateTime end);
	public float calcMaintenanceCostForFacility();
	public float calcProblemRateForFacility(LocalDateTime since, LocalDateTime til);
	public float calcDownTimeForFacility(LocalDateTime since, LocalDateTime til);
	public List<MaintTicket> listMaintRequests();
	public List<MaintTicket> listMaintenance();
	public List<String> listFacilityProblems();
	public MaintTicket getMaintTicket(String description);
}
