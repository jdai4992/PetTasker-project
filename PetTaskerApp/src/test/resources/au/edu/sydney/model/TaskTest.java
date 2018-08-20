package au.edu.sydney.model;

import java.util.Date;

import junit.framework.TestCase;

public class TaskTest extends TestCase {

	private Task task;
	
	public void setUp() throws Exception {
		task = new Task();
	}
	
	public void testSetAndGetTaskId() {
		int taskId = 2;
		assertNotNull(task.getId());
		task.setId(taskId);
		assertEquals(taskId, task.getId());
	}
	
	public void testSetAndGetTaskName() {
		String taskName = "Walk and look after my dog";
		assertNull(task.getTaskName());
		task.setTaskName(taskName);
		assertEquals(taskName, task.getTaskName());
	}
	
	public void testSetAndGetTaskDescription() {
		String taskDescription = "Look after my dog while I'm away for 2 hours";
		assertNull(task.getTaskDescription());
		task.setTaskDescription(taskDescription);
		assertEquals(taskDescription, task.getTaskDescription());
	}
	
	public void testSetAndGetTaskPrice() {
		Integer taskPrice = 20;
		assertEquals(0, 0);
		assertNull(task.getTaskPrice());
		task.setTaskPrice(taskPrice);
		assertEquals(taskPrice, task.getTaskPrice());
		assertEquals(taskPrice, task.getTaskPrice(), 0);
	}
	
	public void testSetAndGetTaskStartDate() {
		Date startDate = new Date();
		assertNull(task.getStartDate());
		task.setStartDate(startDate);
		assertEquals(startDate, task.getStartDate());
	}
	
	public void testSetAndGetTaskStatus() {
		String taskStatus = "openTask";
		assertNull(task.getTaskStatus());
		task.setTaskStatus(taskStatus);
		assertEquals(taskStatus, task.getTaskStatus());
		
		taskStatus = "completedTask";
		task.setTaskStatus(taskStatus);
		assertEquals(taskStatus, task.getTaskStatus());
	}
	
	public void testSetAndGetTaskAdderId() {
		int userId = 1;
		assertNotNull(task.getAdderId());
		task.setAdderId(userId);
		assertEquals(userId, task.getAdderId());
	}
	
	public void testSetAndGetTaskWorkerId() {
		int userId = 2;
		assertNotNull(task.getWorkerId());
		task.setWorkerId(userId);
		assertEquals(userId, task.getWorkerId());
	}
	
	public void testSetAndGetAdderCompletedStatus() {
		Boolean adderCompletedStatus = false;
		assertNull(task.getAdderCompletedStatus());
		task.setAdderCompletedStatus(adderCompletedStatus);
		assertEquals(adderCompletedStatus, task.getAdderCompletedStatus());
		
		adderCompletedStatus = true;
		task.setAdderCompletedStatus(adderCompletedStatus);
		assertEquals(adderCompletedStatus, task.getAdderCompletedStatus());
		assertNotNull(task.getAdderCompletedStatus());
	}
	
	public void testSetAndGetWorkerCompletedStatus() {
		Boolean workerCompletedStatus = false;
		assertNull(task.getWorkerCompletedStatus());
		task.setWorkerCompletedStatus(workerCompletedStatus);
		assertEquals(workerCompletedStatus, task.getWorkerCompletedStatus());
		
		workerCompletedStatus = true;
		task.setWorkerCompletedStatus(workerCompletedStatus);
		assertEquals(workerCompletedStatus, task.getWorkerCompletedStatus());
		assertNotNull(task.getWorkerCompletedStatus());
	}
	
	public void testSetAndGetDel() {
		Boolean del = false;
		assertNull(task.getDel());
		task.setDel(del);
		assertEquals(del, task.getDel());
		
		del = true;
		task.setDel(del);
		assertEquals(del, task.getDel());
		assertNotNull(task.getDel());
	}
	
}