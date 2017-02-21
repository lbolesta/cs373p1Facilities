package main.model.facility;

import java.time.ZonedDateTime;
import java.util.List;

import main.model.maintenance.FacilityProblem;
import main.model.maintenance.IFacilityMaintenance;
import main.model.maintenance.MaintOrder;
import main.model.maintenance.MaintRequest;
import main.model.maintenance.MaintenanceService;
import main.model.use.IFacilityUse;
import main.model.use.Inspection;
import main.model.use.Reservation;
import main.model.use.UsageService;

public class ScheduleManager implements IFacilityMaintenance<MaintRequest, MaintOrder, FacilityProblem>, IFacilityUse<Reservation, Inspection> {
	private MaintenanceService maint;
	private UsageService usage;
	
	public MaintenanceService getMaint() {
		return maint;
	}
	
	public void setMaint(MaintenanceService maint) {
		this.maint = maint;
	}
	
	public UsageService getUsage() {
		return usage;
	}
	
	public void setUsage(UsageService usage) {
		this.usage = usage;
	}
	
	@Override
	public void makeFacilityMaintRequest(String description) {
		maint.makeFacilityMaintRequest(description);
	}
	
	@Override
	public void scheduleMaintenance(MaintRequest request, ZonedDateTime startTime, ZonedDateTime endTime) {
		if(isInUseDuringInterval(startTime, endTime) && !isUnderMaintenance(startTime, endTime)) {
			vacateFacility(startTime, endTime);
			maint.scheduleMaintenance(request, startTime, endTime);
			usage.assignFacilityToUse(startTime, endTime);
		} else {
			System.console().printf("Maintenance order conflicts with existing maintenance.");
		}
	}
	
	@Override
	public float calcMaintenanceCostForFacility() {
		return maint.calcMaintenanceCostForFacility();
	}
	@Override
	public float calcProblemRateForFacility(ZonedDateTime since) {
		return maint.calcProblemRateForFacility(since);
	}
	@Override
	public float calcDownTimeForFacility(ZonedDateTime since) {
		return maint.calcDownTimeForFacility(since);
	}
	@Override
	public List<MaintRequest> listMaintRequests() {
		return maint.listMaintRequests();
	}
	@Override
	public List<MaintOrder> listMaintenance() {
		return maint.listMaintenance();
	}
	@Override
	public List<FacilityProblem> listFacilityProblems() {
		return maint.listFacilityProblems();
	}
	@Override
	public boolean isInUseDuringInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
		return usage.isInUseDuringInterval(startTime, endTime);
	}
	@Override
	public boolean assignFacilityToUse(ZonedDateTime startTime, ZonedDateTime endTime) {
		return usage.assignFacilityToUse(startTime, endTime);
	}
	@Override
	public void vacateFacility(ZonedDateTime startTime, ZonedDateTime endTime) {
		usage.vacateFacility(startTime, endTime);
	}
	@Override
	public List<Inspection> listInspections() {
		return usage.listInspections();
	}
	@Override
	public List<Reservation> listActualUsage() {
		return usage.listActualUsage();
	}
	@Override
	public float calcUsageRate(ZonedDateTime since) {
		return usage.calcUsageRate(since);
	}
	
	public boolean isUnderMaintenance(ZonedDateTime startTime, ZonedDateTime endTime) {
		boolean underMaint = false;
		while (!underMaint){
			for (MaintOrder order : listMaintenance()) {
				ZonedDateTime oStart = order.getStartTime();
				ZonedDateTime oEnd = order.getEndTime();
				underMaint = (oStart.isBefore(startTime) && oEnd.isAfter(startTime)) || (oStart.isBefore(endTime) && oEnd.isAfter(endTime));
			}
		}
		return underMaint;
	}
	
	
}
