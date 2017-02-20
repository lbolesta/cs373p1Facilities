package model.maintenance;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class MaintenanceClient implements IFacilityMaintenance<MaintRequest, MaintOrder, FacilityProblem> {

	private List<MaintRequest> requests;
	private List<MaintOrder> orders;
	private List<FacilityProblem> problems;
	
	@Override
	public void makeFacilityMaintRequest(String description) {
		MaintRequest request = new MaintRequest(description);
		this.requests.add(request);
	}

	@Override
	public void scheduleMaintenance(MaintRequest request, ZonedDateTime startTime) {
		FacilityProblem facilityProblem = request.getFacilityProblem();
		MaintOrder order = new MaintOrder(facilityProblem, startTime);
		orders.add(order);
	}

	@Override
	public float calcMaintenanceCostForFacility() {
		float cost = 0;
		for (MaintOrder order : this.orders) {
			cost += order.getCost();
		}
		return cost;
	}

	@Override
	public float calcProblemRateForFacility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calcDownTimeForFacility() {
		// TODO Calculate total time facility open
		int downTime = 0;
		for (FacilityProblem problem : listFacilityProblems()) {
			Duration d = Duration.between(problem.getStartTime(), problem.getEndTime());
			downTime += d.getSeconds();
		}
		return downTime;
	}

	@Override
	public List<MaintRequest> listMaintRequests() {
		return this.requests;
	}

	@Override
	public List<MaintOrder> listMaintenance() {
		return this.orders;
	}

	@Override
	public List<FacilityProblem> listFacilityProblems() {
		for (MaintRequest request : requests) {
			FacilityProblem problem = request.getFacilityProblem();
			this.problems.add(problem);
		}
		return this.problems;
	}

}
