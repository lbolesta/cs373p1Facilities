package main.model.use;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsageServiceImpl implements UsageService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
	
	private List<Reservation> reservations;
	private List<Inspection> inspections;
	
	public UsageServiceImpl() {
		reservations = new ArrayList<Reservation>();
		inspections = new ArrayList<Inspection>();
	}

	public boolean isInUseDuringInterval(LocalDateTime start, LocalDateTime end) {
		for (Reservation reservation : reservations) {
			LocalDateTime resStart = reservation.getStartTime();
			LocalDateTime resEnd = reservation.getEndTime();
			if (start.isBefore(resStart) && (end.isBefore(resStart) || end.isEqual(resStart))) {
				break;
			} else if (end.isAfter(resEnd) && (start.isAfter(resEnd) || start.isEqual(resEnd))) {
				break;
			} else {
				return true;
			}
		}
		return false;
	}

	public void assignFacilityToUse(LocalDateTime start, LocalDateTime end, User user) {
		boolean inUse = isInUseDuringInterval(start, end);
		
		if(!inUse){
			Reservation r = (Reservation) context.getBean("reservation");
			r.setStartTime(start);
			r.setEndTime(end);
			r.setUser(user);
			this.addReservation(r);
		}
	}

	public void vacateFacility(LocalDateTime start, LocalDateTime end) {
		boolean inUse = isInUseDuringInterval(start, end);
		if(inUse) {
			List<Reservation> removals = new ArrayList<Reservation>();
			for (Reservation reservation : reservations){
				LocalDateTime resStart = reservation.getStartTime();
				LocalDateTime resEnd = reservation.getEndTime();
				if (resEnd.isAfter(start) && resStart.isBefore(end)) {
					removals.add(reservation);
				}
			}
			for (Reservation reservation : removals) {
				this.removeReservation(reservation);
			}
		}
	}

	public List<Inspection> listInspections() {
		return inspections;
	}

	public List<Reservation> listActualUsage() {
		return reservations;
	}

	public float calcUsageRate(LocalDateTime since, LocalDateTime til) {
		float usageTime = 0;
		float total = (Duration.between(since, til)).toHours();
		for (Reservation reservation : reservations){
			if(reservation.getStartTime().isAfter(since)) {
				Duration d = Duration.between(reservation.getStartTime(), reservation.getEndTime());
				usageTime += d.toHours();
			} else if (reservation.getEndTime().isAfter(since)) {
				Duration d = Duration.between(since, reservation.getEndTime());
				usageTime += d.toHours();
			}
		}
		return usageTime/total;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	private void addReservation(Reservation reservation){
		reservations.add(reservation);
	}
	
	private void removeReservation(Reservation reservation) {
		if (reservations.contains(reservation)){
			reservations.remove(reservation);
		}
	}

	public void addInspection(LocalDate date, String description) {
		Inspection i = (Inspection) context.getBean("inspection");
		if (!this.inspections.contains(i)){
			this.inspections.add(i);
		}
	}

}
