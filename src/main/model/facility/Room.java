package main.model.facility;

import java.util.ArrayList;
import java.util.List;

public class Room extends Facility {

	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private ScheduleManager schedule;
	
	public Room(String name, int capacity){
		subunits = new ArrayList<IFacility<UnitInfo>>();
		this.info = new UnitInfo(name, capacity);
		schedule = new ScheduleManager();
	}
	
	@Override
	public List<IFacility<UnitInfo>> listFacilities() {
		if(!subunits.contains(this)){
			subunits.add(this);
		}
		return subunits;  //just returns self
	}

	@Override
	public int requestAvailableCapacity() {
		return info.getCapacity();
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
