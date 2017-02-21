package main.model.maintenance;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceService implements IFacilityMaintenance<MaintRequest, MaintOrder, FacilityProblem> {

	private List<MaintRequest> requests;
	private List<MaintOrder> orders;
	private List<FacilityProblem> problems;
	
	public MaintenanceService() {
		requests = new ArrayList<MaintRequest>();
		orders = new ArrayList<MaintOrder>();
		problems = new ArrayList<FacilityProblem>();
	}
	
	@Override
	public void makeFacilityMaintRequest(String description) {
		MaintRequest request = new MaintRequest(description);
		requests.add(request);
	}

	@Override
	public void scheduleMaintenance(MaintRequest request, ZonedDateTime startTime, ZonedDateTime endTime) {
		FacilityProblem problem = request.getFacilityProblem();
		MaintOrder order = new MaintOrder(problem, startTime, endTime);
		orders.add(order);
	}

	@Override
	public float calcMaintenanceCostForFacility() {
		float cost = 0;
		for (MaintOrder order : this.orders) {
			cost += order.calcCost();
		}
		return cost;
	}

	@Override
	public float calcProblemRateForFacility(ZonedDateTime since) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float calcDownTimeForFacility(ZonedDateTime since) {
		float downTime = 0;
		ZonedDateTime now = ZonedDateTime.now();
		float total = (Duration.between(since, now)).toHours();
		for (FacilityProblem problem : listFacilityProblems()) {
			if(problem.getStartTime().isAfter(since)){
				Duration d = Duration.between(problem.getStartTime(), problem.getEndTime());
				downTime += d.toHours();
			} else if (problem.getEndTime().isAfter(since)){
				Duration d = Duration.between(since, problem.getEndTime());
				downTime += d.toHours();
			}
		}
		return downTime/total;
	}

	@Override
	public List<MaintRequest> listMaintRequests() {
		return requests;
	}

	@Override
	public List<MaintOrder> listMaintenance() {
		return orders;
	}

	@Override
	public List<FacilityProblem> listFacilityProblems() {
		for (MaintRequest request : requests) {
			FacilityProblem problem = request.getFacilityProblem();
			if (!problems.contains(problem)) {
				problems.add(problem);
			}
		}
		return problems;
	}

}
