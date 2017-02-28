package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class Building extends Facility {
	
	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	public Building(String name){
		subunits = new ArrayList<IFacility<UnitInfo>>();
		this.info = new UnitInfo(name, 0);
		schedule = new ScheduleManager();
	}

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
