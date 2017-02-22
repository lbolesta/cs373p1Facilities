package test.model.maintenance;

import static org.junit.Assert.*
;


import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.maintenance.FacilityProblem;
import main.model.maintenance.MaintOrder;
import main.model.maintenance.Material;
import main.model.maintenance.Worker;

public class MaintOrderTest {

	ZonedDateTime defaultStartTime = ZonedDateTime.of(2016, 3, 1, 8, 0, 0, 0, ZoneId.systemDefault());
	ZonedDateTime defaultEndTime = ZonedDateTime.of(2016, 3, 1, 10, 0, 0, 0, ZoneId.systemDefault());
	FacilityProblem defaultProblem = new FacilityProblem("ceiling leaks", defaultStartTime);
	MaintOrder defaultOrder = new MaintOrder(defaultProblem, defaultStartTime, defaultEndTime);
	Material brick = new Material("brick", 2.50);
	Material mortar = new Material("mortar", 5.75);
	Worker Bill = new Worker("Bill", 1);
	Worker Bob = new Worker("Bob", 15);
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetFacilityProblem() {
		MaintOrder order = defaultOrder;
		assertNotEquals(order.getFacilityProblem(), null);
		FacilityProblem problem = new FacilityProblem("ceiling leaks", defaultStartTime);
		assertNotEquals(order.getFacilityProblem(), problem);
		order.setFacilityProblem(problem);
		assertEquals(order.getFacilityProblem(), problem);
	}

	@Test
	public void testCalcCost() {
		MaintOrder order = defaultOrder;
		assertEquals(order.calcCost(), 0.0, 0);
		Material material = new Material();
		material.setName("brick");
		material.setCost(.5);
		order.addMaterial(material);
		order.addMaterial(material);
		assertEquals(order.calcCost(), 1, 0);
		Worker worker = new Worker();
		worker.setName("Bill");
		worker.setWage(15);
		order.addWorker(worker);
		assertEquals(order.calcCost(), 31, 0);
	}

	@Test
	public void testGetAndSetStartTime() {
		MaintOrder order = defaultOrder;
		assertEquals(order.getStartTime(),defaultStartTime);
		assertFalse(order.setStartTime(ZonedDateTime.now()));
		assertEquals(order.getStartTime(),defaultStartTime);
		assertTrue(order.setStartTime(defaultStartTime.plusHours(1)));
		assertEquals(order.getStartTime(),defaultStartTime.plusHours(1));
	}

	@Test
	public void testGetAndSetEndTime() {
		MaintOrder order = defaultOrder;
		assertEquals(order.getEndTime(),defaultEndTime);
		assertFalse(order.setEndTime(defaultStartTime.minusHours(1)));
		assertEquals(order.getEndTime(),defaultEndTime);
		assertTrue(order.setEndTime(defaultStartTime.plusHours(1)));
		assertEquals(order.getEndTime(),defaultStartTime.plusHours(1));
	}

	@Test
	public void testMaterials() {
		MaintOrder order = defaultOrder;
		assertTrue(order.getMaterials().isEmpty());
		order.addMaterial(brick);
		order.addMaterial(mortar);
		order.addMaterial(brick);
		assertEquals(order.calcCost(), 10.75, 0);
		order.removeMaterial(mortar);
		assertEquals(order.calcCost(), 5, 0);
		order.removeMaterial(mortar);
		assertEquals(order.calcCost(), 5, 0);
	}

	@Test
	public void testWorkers(){
		MaintOrder order = defaultOrder;
		assertTrue(order.getWorkers().isEmpty());
		order.addWorker(Bill);
		order.addWorker(Bob);
		order.addWorker(Bill);
		assertEquals(order.calcCost(), 34, 0);
		order.removeWorker(Bob);
		assertEquals(order.calcCost(), 4, 0);
		order.removeWorker(Bob);
		assertEquals(order.calcCost(), 4, 0);
		order.removeWorker(Bill);
		assertEquals(order.calcCost(), 2, 0);
	}

}
