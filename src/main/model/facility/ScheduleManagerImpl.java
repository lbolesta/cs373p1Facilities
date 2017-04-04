package main.model.facility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.model.maintenance.MaintService;
import main.model.maintenance.MaintServiceImpl;
import main.model.maintenance.MaintTicket;
import main.model.use.UsageService;
import main.model.use.Inspection;
import main.model.use.Reservation;
import main.model.use.UsageService;
import main.model.use.User;

public class ScheduleManager implements MaintService, UsageService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	
	private MaintService maint = (MaintService) context.getBean("maintService");
	private UsageService usage = (UsageService) context.getBean("usageService");
	private User maintUser = (User) context.getBean("user");
	
	/*public ScheduleManager(){
		maint = new MaintServiceImpl();
		usage = new UsageService();
	}*/
	
	public MaintService getMaint() {
		return maint;
	}
	
	public UsageService getUsage() {
		return usage;
	}
	
	@Override
	public void makeFacilityMaintRequest(String description, LocalDateTime start) {
		maint.makeFacilityMaintRequest(description, start);
	}
	
	@Override
	public void scheduleMaintenance(MaintTicket ticket, LocalDateTime startTime, LocalDateTime endTime) {
		if(isUnderMaintenance(startTime, endTime)) {
			return;
		}
		else if(isInUseDuringInterval(startTime, endTime)) {
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
	public float calcProblemRateForFacility(LocalDateTime since, LocalDateTime til) {
		return maint.calcProblemRateForFacility(since, til);
	}
	@Override
	public float calcDownTimeForFacility(LocalDateTime since, LocalDateTime til) {
		return maint.calcDownTimeForFacility(since, til);
	}
	@Override
	public List<MaintTicket> listMaintRequests() {
		return (List<MaintTicket>) maint.listMaintRequests();
	}
	@Override
	public List<MaintTicket> listMaintenance() {
		return (List<MaintTicket>) maint.listMaintenance();
	}
	@Override
	public List<String> listFacilityProblems() {
		return maint.listFacilityProblems();
	}
	@Override
	public boolean isInUseDuringInterval(LocalDateTime startTime, LocalDateTime endTime) {
		return usage.isInUseDuringInterval(startTime, endTime);
	}
	@Override
	public void assignFacilityToUse(LocalDateTime startTime, LocalDateTime endTime, User user) {
		usage.assignFacilityToUse(startTime, endTime, user);
	}
	@Override
	public void vacateFacility(LocalDateTime startTime, LocalDateTime endTime) {
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
	public float calcUsageRate(LocalDateTime since, LocalDateTime til) {
		return usage.calcUsageRate(since, til);
	}
	
	private boolean isUnderMaintenance(LocalDateTime startTime, LocalDateTime endTime) {
		for (MaintTicket ticket : listMaintenance()) {
			LocalDateTime oStart = ticket.getOrderTime();
			LocalDateTime oEnd = ticket.getResolveTime();
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

	@Override
	public MaintTicket getMaintTicket(String description) {
		return maint.getMaintTicket(description);
	}

	@Override
	public void addInspection(LocalDate date, String description) {
		usage.addInspection(date, description);
		
	}
	
	
}
