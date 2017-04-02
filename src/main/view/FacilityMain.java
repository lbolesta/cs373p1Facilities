
package main.view;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.core.io.FileSystemResource;

import main.model.facility.*;
import main.model.maintenance.*;
import main.model.use.*;


public class FacilityMain {
	public static void main (String args[]){

		
		ApplicationContext context = new ClassPathXmlApplicationContext("FacilitiesContext.xml");
		
		
		//FACILITY
		
		System.out.println("Demonstrating facility methods");
		
		//using spring for dependency injection
		Campus defaultCampus = (Campus) context.getBean("campus");
		Room defaultRoom = (Room) context.getBean("defaultRoom");
		Room defaultRoom2 = (Room) context.getBean("defaultRoom2");
		Building defaultBuilding = (Building) context.getBean("defaultBuilding");
		//Building defaultBuilding = new Building("Corboy Law Center");
		
		/*Room defaultRoom = new Room("711", 40);
		Room defaultRoom2 = new Room("712", 50);
		Building defaultBuilding = new Building("Corboy Law Center");
		Campus defaultCampus = new Campus("Loyola University");*/
		
		//addNewFacility()
		System.out.println();//at this line in debugger default building has the name corboy law center
		System.out.println("Adding facilities...");
		defaultCampus.addNewFacility(defaultBuilding);
		defaultCampus.getFacility("Corboy Law Center").addNewFacility(defaultRoom);
		defaultCampus.getFacility("Corboy Law Center").addNewFacility(defaultRoom2);//throws null pointer exception here
		//not sure why because in debugger default room 2 had capacity and name
		
		//listFacilities() and requestAvailableCapacity()
		System.out.println();
		System.out.println("Listing facilities...");
		for (Building b : defaultCampus.listFacilities()){
			System.out.println("Building name: " + b.getFacilityInformation().getName());
			System.out.println("Building capacity: " + b.requestAvailableCapacity());
		}
		for (Room r : defaultCampus.getFacility("Corboy Law Center").listFacilities()){
			System.out.println("Room name: " + r.getFacilityInformation().getName());
			System.out.println("Room capacity: " + r.requestAvailableCapacity());
		}
		
		//removeFacility()
		System.out.println();
		System.out.println("Removing facility...");
		defaultCampus.getFacility("Corboy Law Center").removeFacility(defaultRoom2);
		for (Building b : defaultCampus.listFacilities()){
			System.out.println("Building name: " + b.getFacilityInformation().getName());
			System.out.println("Building capacity: " + b.requestAvailableCapacity());
		}
		for (Room r : defaultCampus.getFacility("Corboy Law Center").listFacilities()){
			System.out.println("Room name: " + r.getFacilityInformation().getName());
			System.out.println("Room capacity: " + r.requestAvailableCapacity());
		}
		
		//addFacilityDetail()
		System.out.println();
		System.out.println("Adding facility detail...");
		defaultCampus.addNewFacilityDetail("Jesuit university");
		
		//getFacilityInformation()
		System.out.println(defaultCampus.getFacilityInformation().toString());
		
		
		//USAGE
		System.out.println();
		System.out.println("Demonstrating usage methods...");
		
		final LocalDateTime defaultStartTime = LocalDateTime.of(2016, 3, 1, 7, 0, 0, 0);
		final LocalDateTime defaultEndTime = LocalDateTime.of(2016, 3, 1, 10, 0, 0, 0);
		final User defaultUser = (User) context.getBean("UserA");
		
		Room room = defaultCampus.getFacility("Corboy Law Center").getFacility("711");
		ScheduleManager schedule = room.getScheduleManager();
		
		//assignFacilityToUse()
		System.out.println();
		System.out.println("Creating reservation...");
		schedule.assignFacilityToUse(defaultStartTime, defaultEndTime, defaultUser);
		
		//listActualUsage()
		for (Reservation r : schedule.listActualUsage()){
			System.out.println(r.toString());
		}
		
		//isInUseDuringInterval()
		System.out.println();
		System.out.println("Is in use between 8 & 10?");
		System.out.println(schedule.isInUseDuringInterval(defaultStartTime.plusHours(1), defaultEndTime));
		System.out.println("Is in use between 10 & 11?");
		System.out.println(schedule.isInUseDuringInterval(defaultEndTime, defaultEndTime.plusHours(1)));
		
		//calcUsageRate()
		System.out.println();
		System.out.println("Usage rate between 6 & 12?");
		final LocalDateTime defaultSinceTime = LocalDateTime.of(2016, 3, 1, 6, 0, 0, 0);
		final LocalDateTime defaultTilTime = LocalDateTime.of(2016, 3, 1, 12, 0, 0, 0);
		System.out.println(schedule.calcUsageRate(defaultSinceTime, defaultTilTime));
		
		//vacateFacility()
		System.out.println();
		System.out.println("Vacating room of any reservations in use from 8-10...");
		schedule.vacateFacility(defaultStartTime.plusHours(1), defaultEndTime);
		System.out.println("Is in use between 8 & 10?");
		System.out.println(schedule.isInUseDuringInterval(defaultStartTime.plusHours(1), defaultEndTime));
		
		//addInspection() and listInspections()
		System.out.println();
		System.out.println("Adding inspection...");

		final Inspection defaultInspection = (Inspection) context.getBean("defaultInspection");
		
		schedule.addInspection(defaultInspection.getDate(), defaultInspection.getDescription());
		
		for (Inspection i : schedule.listInspections()){
			System.out.println(i.toString());
		}
		
		
		//MAINTENANCE
		System.out.println();
		System.out.println("Demonstrating maintenance methods...");
		
		//makeFacilityMaintRequest()
		System.out.println();
		System.out.println("Making maintenance request...");
		schedule.makeFacilityMaintRequest("Replace lightbulb", defaultStartTime);
		
		//listMaintRequests()
		for (MaintTicket t : schedule.listMaintRequests()){
			System.out.println(t.toString());;
		}
		
		//scheduleMaintenance()
		System.out.println();
		System.out.println("Scheduling maintenance order...");
		MaintTicket defaultTicket = schedule.getMaint().getMaintTicket("Replace lightbulb");
		
		Material bulb = (Material) context.getBean("bulb");
		Worker Bob = (Worker) context.getBean("Bob");
		
		defaultTicket.addMaterial(bulb);
		defaultTicket.addWorker(Bob);
		
		schedule.scheduleMaintenance(defaultTicket, defaultStartTime.plusHours(2), defaultEndTime);
		
		//listMaintenance()
		for (MaintTicket t : schedule.listMaintenance()){
			System.out.println(t.toString());;
		}
		
		//calcMaintenanceCostForFacility()
		System.out.println();
		System.out.println("Calculating cost of maintenance...");
		System.out.println("Maintenance cost: " + String.format("%.2f", schedule.calcMaintenanceCostForFacility()));
		
		//calcDownTimeForFacility()
		System.out.println();
		System.out.println("Calculating downtime between 6 and 12...");
		System.out.println("Downtime: " + schedule.calcDownTimeForFacility(defaultSinceTime, defaultTilTime));
		
		//listProblems()
		System.out.println();
		System.out.println("Listing historical problems for facility...");
		for (String p : schedule.listFacilityProblems()){
			System.out.println(p);
		}
		
		((ClassPathXmlApplicationContext) context).close();
	}

}