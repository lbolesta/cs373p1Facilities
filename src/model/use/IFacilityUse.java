package model.use;

import java.util.List;

public interface IFacilityUse {

	public boolean isInUseDuringInterval(int startTime, int endTime);
	public void assignFacilityToUse();
	public void vacateFacility();
	public List<?> listInspections();
	public List<?> listActualUsage();
	public float calcUsageRate();

}
