package main.model.facility;

import java.util.List;

public class Room extends Facility {

	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	@Override
	public List<IFacility<UnitInfo>> listFacilities() {
		subunits.add(this);
		return subunits;
	}

	@Override
	public int requestAvailableCapacity() {
		//TODO
		int totalCapacity = info.getCapacity();
		return totalCapacity;
	}

	@Override
	public UnitInfo getInfo() {
		return info;
	}
	
	@Override
	public ScheduleManager getScheduleManager() {
		return schedule;
	}
}
