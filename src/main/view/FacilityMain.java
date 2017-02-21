
package main.view;
import java.awt.List;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.*;

import main.model.facility.*;
import main.model.maintenance.*;
import main.model.use.*;


public class FacilityMain {
	public static void main (String args[]){
	
		ArrayList<String> detail = new ArrayList<>();
		detail.add("Needs admin approval");
		detail.add("Downtown Campus");
		
		UnitInfo a = new UnitInfo();
		a.setName("Lewis Towers");
		a.setIdNumber(1);
		a.setCapacity(300);
		a.setDetails(detail);
		ArrayList<String> x = (ArrayList<String>) a.getDetails();
	
		System.out.println("Name: " +a.getName() + "\n" + "ID Number: " + 
		a.getIdNumber() + "\n" + "Capacity: " +
		a.getCapacity() + "\n" + "Details: " + x.toString() + "\n");
		
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
		
		UnitInfo b = new UnitInfo();
		b.setName("IC");
		b.setIdNumber(2);
		b.setCapacity(700);
		b.setDetails(detailTwo);
		ArrayList<String> p = (ArrayList<String>) b.getDetails();
		
		System.out.println("Name: " +b.getName() + "\n" + "ID Number: " + 
		b.getIdNumber() + "\n" + "Capacity: " +
		b.getCapacity() + "\n" + "Details: " + p.toString() + "\n" + "\n");
		
		UsageService u = new UsageService();
		u.assignFacilityToUse((ZonedDateTime.parse("2017-02-28T12:30:40Z[America/Chicago]")), 
				(ZonedDateTime.parse("2017-02-28T14:30:40Z[America/Chicago]")));
		
	}
}
