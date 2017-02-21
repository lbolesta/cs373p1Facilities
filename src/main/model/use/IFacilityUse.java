package main.model.use;

import java.time.ZonedDateTime;
import java.util.List;

public interface IFacilityUse<T,S> {

	public boolean isInUseDuringInterval(ZonedDateTime startTime, ZonedDateTime endTime);
	public boolean assignFacilityToUse(ZonedDateTime startTime, ZonedDateTime endTime);
	public void vacateFacility(ZonedDateTime startTime, ZonedDateTime endTime);
	public List<S> listInspections();
	public List<T> listActualUsage();
	public float calcUsageRate(ZonedDateTime since);

}
