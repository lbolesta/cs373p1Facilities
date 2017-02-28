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
		for (Reservation reservation : reservations) {
			ZonedDateTime resStart = reservation.getStartTime();
			ZonedDateTime resEnd = reservation.getEndTime();
			if (startTime.isBefore(resStart) && (endTime.isBefore(resStart) || endTime.isEqual(resStart))) {
				break;
			} else if (endTime.isAfter(resEnd) && (startTime.isAfter(resEnd) || startTime.isEqual(resEnd))) {
				break;
			} else {
				return true;
			}
		}
		return false;
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
			List<Reservation> removals = new ArrayList<Reservation>();
			for (Reservation reservation : reservations){
				ZonedDateTime resStart = reservation.getStartTime();
				ZonedDateTime resEnd = reservation.getEndTime();
				if (resEnd.isAfter(startTime) && resStart.isBefore(endTime)) {
					removals.add(reservation);
				}
			}
			for (Reservation reservation : removals) {
				this.removeReservation(reservation);
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
	public float calcUsageRate(ZonedDateTime since, ZonedDateTime til) {
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

	public void addInspections(List<Inspection> inspections) {
		for (Inspection i : inspections) {
			if (!this.inspections.contains(i)){
				this.inspections.add(i);
			}
		}
	}

}
