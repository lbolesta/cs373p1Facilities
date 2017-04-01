package main.model.use;

import java.time.LocalDateTime;
import java.util.List;

public interface IFacilityUse<T,S> {

	public boolean isInUseDuringInterval(LocalDateTime startTime, LocalDateTime endTime);
	public boolean assignFacilityToUse(LocalDateTime startTime, LocalDateTime endTime, User user);
	public void vacateFacility(LocalDateTime startTime, LocalDateTime endTime);
	public List<S> listInspections();
	public List<T> listActualUsage();
	public float calcUsageRate(LocalDateTime since, LocalDateTime til);

}
