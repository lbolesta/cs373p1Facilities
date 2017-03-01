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
	private static User maintUser = new User("Maintenance");
	
	public ScheduleManager(){
		maint = new MaintenanceService();
		usage = new UsageService();
	}
	
	public MaintenanceService getMaint() {
		return maint;
	}
	
	public UsageService getUsage() {
		return usage;
	}
	
	@Override
	public void makeFacilityMaintRequest(String description, ZonedDateTime start) {
		maint.makeFacilityMaintRequest(description, start);
	}
	
	@Override
	public boolean scheduleMaintenance(MaintTicket ticket, ZonedDateTime startTime, ZonedDateTime endTime) {
		if(isUnderMaintenance(startTime, endTime)) {
			return false;
		}
		else if(isInUseDuringInterval(startTime, endTime)) {
			vacateFacility(startTime, endTime);
		}
		maint.scheduleMaintenance(ticket, startTime, endTime);
		usage.assignFacilityToUse(startTime, endTime, maintUser);
		return true;
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
	
	private boolean isUnderMaintenance(ZonedDateTime startTime, ZonedDateTime endTime) {
		for (MaintTicket ticket : listMaintenance()) {
			ZonedDateTime oStart = ticket.getOrderTime();
			ZonedDateTime oEnd = ticket.getResolveTime();
			if (startTime.isBefore(oStart) && (endTime.isBefore(oStart) || endTime.isEqual(oStart))) {
				break;
			} else if (endTime.isAfter(oEnd) && (startTime.isAfter(oEnd) || startTime.isEqual(oEnd))) {
				break;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public void addInspections(List<Inspection> inspections) {
		usage.addInspections(inspections);
	}
	
	
}
