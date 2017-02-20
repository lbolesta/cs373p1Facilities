package model.facility;

import java.util.List;

import model.maintenance.MaintenanceClient;

public class Campus extends Facility {

	private List<IFacility<UnitInfo>> subunits;
	private UnitInfo info;
	private MaintenanceClient maint;

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

}
