package model.facility;

import java.util.List;

import model.maintenance.MaintenanceClient;
import model.use.UsageClient;

public class Building extends Facility {
	
	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private MaintenanceClient maint;
	private UsageClient usage;

	@Override
	public UnitInfo getInfo() {
		return info;
	}

	@Override
	public MaintenanceClient getMaintenanceClient() {
		return maint;
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

	@Override
	public UsageClient getUsageClient() {
		return usage;
	}

}
