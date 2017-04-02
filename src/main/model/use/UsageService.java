package main.model.use;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UsageService {

	public boolean isInUseDuringInterval(LocalDateTime start, LocalDateTime end);
	public void assignFacilityToUse(LocalDateTime start, LocalDateTime end, User user);
	public void vacateFacility(LocalDateTime start, LocalDateTime end);
	public List<Inspection> listInspections();
	public List<Reservation> listActualUsage();
	public float calcUsageRate(LocalDateTime since, LocalDateTime til);
	public void addInspection(LocalDate data, String description);

}
