
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
	
		/*ArrayList<String> detail = new ArrayList<>();
		detail.add("Needs admin approval");
		detail.add("Downtown Campus");
		
		System.out.println("Insert Facility...");
		UnitInfo a = new UnitInfo();
		a.setName("Lewis Towers");
		a.setIdNumber(1);
		a.setCapacity(300);
		a.setDetails(detail);
		System.out.println("adding a single detail to already established list (details list) \n");
		a.addDetail("Single added detail");//add a new detail
		ArrayList<String> x = (ArrayList<String>) a.getDetails();
		
	
		System.out.println("Name: " +a.getName() + "\n" + "ID Number: " + 
		a.getIdNumber() + "\n" + "Capacity: " +
		a.getCapacity() + "\n" + "Details: " + x.toString() + "\n");
		
		System.out.println("Removing 'Single added detail' ");
		a.removeDetail("Single added detail");
		System.out.println("List reprinted with detail removed ");
		System.out.println("Details: " + x.toString() + "\n");
		
		MaintRequest m = new MaintRequest("Lights flicker in Lewis 238");
		System.out.println("Request Made");
		m.setRequestTime(ZonedDateTime.now().minusDays(1).minusHours(1));
		System.out.println("Maintenance Request Date: " + m.getRequestTime() + "\n");
		
		System.out.println("Facility problem: " + m.getFacilityProblem().getDescription());
		
		MaintOrder mo = new MaintOrder(m.getFacilityProblem(), ZonedDateTime.now().minusDays(1), ZonedDateTime.now().minusDays(1).plusHours(1));
		System.out.println("Logged Problem: " + mo.getFacilityProblem().getDescription() + "\n");
		
		ArrayList<String> detailTwo = new ArrayList<>();
		detailTwo.add("Reservations Allowed");
		detailTwo.add("Lake Shore Campus");
		
		System.out.println("Insert Facility...");
		UnitInfo b = new UnitInfo();
		b.setName("IC");
		b.setIdNumber(2);
		b.setCapacity(700);
		b.setDetails(detailTwo);
		ArrayList<String> p = (ArrayList<String>) b.getDetails();
		
		System.out.println("Name: " +b.getName() + "\n" + "ID Number: " + 
		b.getIdNumber() + "\n" + "Capacity: " +
		b.getCapacity() + "\n" + "Details: " + p.toString() + "\n" + "\n");
		

		System.out.println("Insert User...");
		User user = new User();
		user.setName("Lauren");
		user.setIdNumber(2);
		System.out.println("User Name: " + user.getName() + "\n" + "User ID: " + user.getIdNumber() + "\n");
		
		
		ZonedDateTime start = ZonedDateTime.of(2016, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
		ZonedDateTime end = ZonedDateTime.of(2016, 3, 1, 10, 0, 0, 0, ZoneId.systemDefault());
		Reservation res = new Reservation();
		res.setUser(user);
		System.out.println("Reservation under: " + user.getName() + ", " + 
		"ID Number: " + user.getIdNumber());
		res.setStartTime(start);
		res.setEndTime(end);
		System.out.println("Start Time: " + start + "\n" + "End Time: " + end);*/
		
		}
	}

