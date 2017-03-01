
package main.view;
import java.awt.List;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.*;

import main.model.facility.*;
import main.model.maintenance.*;
import main.model.use.*;


public class FacilityMain {
	public static void main (String args[]){
		ZonedDateTime start = ZonedDateTime.of(2017, 3, 5, 8, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime end = ZonedDateTime.of(2017, 3, 5, 10, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime AssignUseStart = ZonedDateTime.of(2017, 3, 10, 10, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime AssignUseEnd = ZonedDateTime.of(2017, 3, 5, 15, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime defaultStart = ZonedDateTime.of(2017, 10, 5, 10, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime defaultEnd = ZonedDateTime.of(2017, 10, 5, 15, 0, 0, 0, ZoneId.systemDefault());
		User Joe = new User("Joe");
		MaintTicket m = new MaintTicket("Sink will be fixed", ZonedDateTime.of(2017, 3, 5, 8, 0, 0, 0, ZoneId.systemDefault()));
		
		ScheduleManager schedule = new ScheduleManager();
		
		//make maintenance request
		schedule.makeFacilityMaintRequest("Fix Sink", ZonedDateTime.of(2017, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault()));
		System.out.println("Maintenance Request has been made \n");
		
		//print maintenance requests
		System.out.println("Listing maintenence requests:");
		java.util.List<MaintTicket> print = schedule.listMaintRequests();
		for (int i = 0; i < print.size(); i++){
			System.out.println(print.get(i) + "\n");//issue here- printing memory location even though using get metho
		}
		
		//schedule maintenance
		schedule.scheduleMaintenance(m, start, end);
		System.out.println("Maintenance has been scheduled \n");
		
		//print scheduled maintenance
		System.out.println("Listing scheduled maintenance:");
		java.util.List<MaintTicket> print2 = schedule.listMaintenance();
		for (int i = 0; i < print2.size(); i++){
			System.out.println(print2.get(i) + "\n");
		}//this is also not printing
		
		//checking if is use
		boolean l = schedule.isInUseDuringInterval(defaultStart, defaultEnd);
		if(l == true){
			System.out.println("Facility is in use");// prints correctly, maintenance scheduling working
		}
		else{
			System.out.println("Facility is not in use");
		}
		
		//assigning a facility to use
		schedule.assignFacilityToUse(AssignUseStart, AssignUseEnd, Joe);
		System.out.println("Facility has been assigned to " + Joe.getName() + "\n");
		
		//checking if facility is in use
		boolean k = schedule.isInUseDuringInterval(AssignUseStart, AssignUseEnd);
		if(k == true){
			System.out.println("Facility is in use, assigned to " + Joe.getName() + "\n");
		}
		else{
			System.out.println("Facility is not in use \n");//this if else should
			//print the first statement, Facility is in use because it was assigned
		}
		
		//float DT = schedule.calcDownTimeForFacility(start, end);
		//System.out.println("Down time is: " + DT);
		//this is throwing a null pointer exception, not sure if issue is method call or with method
		
		//adding a worker
		System.out.println("Adding worker: ");
		Worker bob = new Worker("Bob", 15.25);
		
		System.out.println("Worker " + bob.getName() + " added at $" +
		bob.getWage() + " " + "per hour \n" );
		
		//adding material
		System.out.println("Adding maintenance material:");
		Material screws = new Material("Screws", .05);
		System.out.println("Material " + screws.getName() + " " +
				"added at $" + screws.getCost() + " " + "per unit \n");
		
		//ensuring unique id numbers are created
		System.out.println("Joe's ID number: " + Joe.getIdNumber());
		User Lauren = new User("Lauren"); 
		System.out.println("Lauren's ID number: " + Lauren.getIdNumber());
		//creates a new ID number each time the program is run. okay for now,
		//think about fixing 
		
		//float UR = schedule.calcUsageRate(start, end);
		//System.out.println("Usage Rate = " + UR);
		//this is also giving a null pointer exception - unknown source on ZonedDateTime
		
		}
	}

