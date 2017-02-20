package model.facility;

import java.util.List;

import model.maintenance.MaintenanceClient;
import model.use.UsageClient;

public class Room extends Facility {

	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private MaintenanceClient maint;
	private UsageClient usage;
	
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
	public MaintenanceClient getMaintenanceClient() {
		return maint;
	}

	@Override
	public UsageClient getUsageClient() {
		return usage;
	}

}
