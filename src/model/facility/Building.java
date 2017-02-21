package model.facility;

import java.util.List;

import model.maintenance.MaintenanceClient;
import model.use.UsageClient;

public class Building extends Facility {
	
	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;

	@Override
	public UnitInfo getInfo() {
		return info;
	}

	@Override
	public ScheduleManager getScheduleManager() {
		return schedule;
	}

	@Override
	public List<IFacility<UnitInfo>> listFacilities() {
		return subunits;
	}

	@Override
	public int requestAvailableCapacity() {
		int availableCapacity = 0;
		for (IFacility<UnitInfo> subunit : subunits){
			availableCapacity += subunit.requestAvailableCapacity();
		}
		return availableCapacity;
	}

}
