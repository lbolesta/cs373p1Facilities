package main.model.maintenance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintServiceImpl implements MaintService {

	private List<MaintTicket> tickets;
	
	public MaintServiceImpl() {
		tickets = new ArrayList<MaintTicket>();
	}
	
	@Override
	public void makeFacilityMaintRequest(String description, LocalDateTime start) {
		MaintTicket ticket = new MaintTicketImpl();
		ticket.setDescription(description);
		ticket.setRequestTime(start);
		tickets.add(ticket);
	}
	
	@Override
	public MaintTicket getMaintTicket(String description) {
		for (MaintTicket t : tickets) {
			if (t.getDescription() == description) {
				return t;
			}
		}
		return null;
	}

	@Override
	public void scheduleMaintenance(MaintTicket ticket, LocalDateTime startTime, LocalDateTime endTime) {
		ticket.setOrderTime(startTime);
		ticket.setResolveTime(endTime);
	}

	@Override
	public float calcMaintenanceCostForFacility() {
		float cost = 0;
		for (MaintTicket t : tickets) {
			cost += t.calcCost();
		}
		return cost;
	}

	@Override
	public float calcProblemRateForFacility(LocalDateTime since, LocalDateTime til) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float calcDownTimeForFacility(LocalDateTime since, LocalDateTime til) {
		float downTime = 0;
		float total = (Duration.between(since, til)).toHours();
		for (MaintTicket t : tickets) {
			if(t.getRequestTime().isAfter(since)) {
				if (!t.getResolveTime().isAfter(til)) {
					Duration d = Duration.between(t.getRequestTime(), t.getResolveTime());
					downTime += d.toHours();
				} else {
					Duration d = Duration.between(t.getRequestTime(), til);
					downTime += d.toHours();
				}
			} else if (!t.getResolveTime().isBefore(since)){
				Duration d = Duration.between(since, t.getResolveTime());
				downTime += d.toHours();
			}
		}
		return downTime/total;
	}

	@Override
	public List<MaintTicket> listMaintRequests() {
		List<MaintTicket> requests = new ArrayList<MaintTicket>();
		for (MaintTicket t : tickets) {
			if (t.getState(LocalDateTime.now()) == "REQUEST") {
				requests.add(t);
			}
		}
		return requests;
	}

	@Override
	public List<MaintTicket> listMaintenance() {
		List<MaintTicket> orders = new ArrayList<MaintTicket>();
		for (MaintTicket t : tickets) {
			if (t.getState(LocalDateTime.now()) == "ORDER" || t.getState(LocalDateTime.now()) == "RESOLVED") {
				orders.add(t);
			}
		}
		return orders;
	}

	@Override
	public List<String> listFacilityProblems() {
		List<String> problems = new ArrayList<String>();
		for (MaintTicket t : tickets) {
			String problem = t.getDescription();
			problems.add(problem);
		}
		return problems;
	}

}
