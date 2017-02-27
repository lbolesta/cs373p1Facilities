package main.model.use;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsageService implements IFacilityUse<Reservation, Inspection> {
	
	private List<Reservation> reservations;
	private List<Inspection> inspections;
	
	public UsageService() {
		reservations = new ArrayList<Reservation>();
		inspections = new ArrayList<Inspection>();
	}

	@Override
	public boolean isInUseDuringInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
		boolean inUse = false;
		while (!inUse){
			for (Reservation reservation : reservations) {
				ZonedDateTime resStart = reservation.getStartTime();
				ZonedDateTime resEnd = reservation.getEndTime();
				inUse = (resStart.isBefore(startTime) && resEnd.isAfter(startTime)) || (resStart.isBefore(endTime) && resEnd.isAfter(endTime));
			}
		}
		return inUse;
	}

	@Override
	public boolean assignFacilityToUse(ZonedDateTime startTime, ZonedDateTime endTime, User user) {
		boolean inUse = isInUseDuringInterval(startTime, endTime);
		if(!inUse){
			Reservation reservation = new Reservation(startTime, endTime, user);
			this.addReservation(reservation);
		}
		return !inUse;
	}

	@Override
	public void vacateFacility(ZonedDateTime startTime, ZonedDateTime endTime) {
		boolean inUse = isInUseDuringInterval(startTime, endTime);
		if(inUse) {
			for (Reservation reservation : reservations){
				ZonedDateTime resStart = reservation.getStartTime();
				ZonedDateTime resEnd = reservation.getEndTime();
				if (resEnd.isAfter(startTime) && resStart.isBefore(endTime)) {
					this.removeReservation(reservation);
				}
			}
		}
	}

	@Override
	public List<Inspection> listInspections() {
		return inspections;
	}

	@Override
	public List<Reservation> listActualUsage() {
		return reservations;
	}

	@Override
	public float calcUsageRate(ZonedDateTime since) {
		float usageTime = 0;
		ZonedDateTime now = ZonedDateTime.now();
		float total = (Duration.between(since, now)).toHours();
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

	public List<Reservation> getReservations() {
		return reservations;
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

	public List<Inspection> getInspections() {
		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

}
