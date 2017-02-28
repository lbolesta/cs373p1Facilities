package main.model.facility;

import java.time.ZonedDateTime;
import java.util.List;

import main.model.maintenance.IFacilityMaintenance;
import main.model.maintenance.MaintTicket;
import main.model.maintenance.MaintenanceService;
import main.model.use.IFacilityUse;
import main.model.use.Inspection;
import main.model.use.Reservation;
import main.model.use.UsageService;
import main.model.use.User;

public class ScheduleManager implements IFacilityMaintenance<MaintTicket>, IFacilityUse<Reservation, Inspection> {
	private MaintenanceService maint;
	private UsageService usage;
	private User maintUser = new User("Maintenance");
	
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
	public void makeFacilityMaintRequest(String description, ZonedDateTime start) {
		maint.makeFacilityMaintRequest(description, start);
	}
	
	@Override
	public void scheduleMaintenance(MaintTicket ticket, ZonedDateTime startTime, ZonedDateTime endTime) {
		if(isUnderMaintenance(startTime, endTime)) {
			System.console().printf("Maintenance order conflicts with existing maintenance.");
			return;
		}
		else if(isInUseDuringInterval(startTime, endTime) && !isUnderMaintenance(startTime, endTime)) {
			vacateFacility(startTime, endTime);
		}
		maint.scheduleMaintenance(ticket, startTime, endTime);
		usage.assignFacilityToUse(startTime, endTime, maintUser);
	}
	
	@Override
	public float calcMaintenanceCostForFacility() {
		return maint.calcMaintenanceCostForFacility();
	}
	@Override
	public float calcProblemRateForFacility(ZonedDateTime since, ZonedDateTime til) {
		return maint.calcProblemRateForFacility(since, til);
	}
	@Override
	public float calcDownTimeForFacility(ZonedDateTime since, ZonedDateTime til) {
		return maint.calcDownTimeForFacility(since, til);
	}
	@Override
	public List<MaintTicket> listMaintRequests() {
		return maint.listMaintRequests();
	}
	@Override
	public List<MaintTicket> listMaintenance() {
		return maint.listMaintenance();
	}
	@Override
	public List<String> listFacilityProblems() {
		return maint.listFacilityProblems();
	}
	@Override
	public boolean isInUseDuringInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
		return usage.isInUseDuringInterval(startTime, endTime);
	}
	@Override
	public boolean assignFacilityToUse(ZonedDateTime startTime, ZonedDateTime endTime, User user) {
		return usage.assignFacilityToUse(startTime, endTime, user);
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
	public float calcUsageRate(ZonedDateTime since, ZonedDateTime til) {
		return usage.calcUsageRate(since, til);
	}
	
	public boolean isUnderMaintenance(ZonedDateTime startTime, ZonedDateTime endTime) {
		boolean underMaint = false;
		while (!underMaint){
			for (MaintTicket order : listMaintenance()) {
				ZonedDateTime oStart = order.getOrderTime();
				ZonedDateTime oEnd = order.getResolveTime();
				underMaint = (oStart.isBefore(startTime) && oEnd.isAfter(startTime)) || (oStart.isBefore(endTime) && oEnd.isAfter(endTime));
			}
		}
		return underMaint;
	}
	
	
}
