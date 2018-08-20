package au.edu.sydney.model;

import junit.framework.TestCase;

public class TaskAppliersTest extends TestCase {

	private TaskAppliers taskAppliers;
	private Task task;
	private User user;
	
	public void setUp() throws Exception {
		taskAppliers = new TaskAppliers();
		task = new Task();
		user = new User();
	}
	
	public void testSetAndGetTaskAppliersId() {
		int userId = 2;
		int taskId = 1;
		task.setId(taskId);
		user.setId(userId);
		taskAppliers.setId(new TaskWorkerId(user,task));
		assertEquals(taskId, taskAppliers.getId().getTask().getId());
		assertEquals(userId, taskAppliers.getId().getUser().getId());
		
		userId = 1;
		taskId = 2;
		task.setId(taskId);
		user.setId(userId);
		taskAppliers.setId(new TaskWorkerId(user,task));
		assertEquals(taskId, taskAppliers.getId().getTask().getId());
		assertEquals(userId, taskAppliers.getId().getUser().getId());
	}
	
	public void testSetAndGetReasonForApply() {
		String reasonForApply = "I'm a keen person who loves pets. Would love to look after your pet dog";
		assertNull(taskAppliers.getReasonForApply());
		taskAppliers.setReasonForApply(reasonForApply);
		assertEquals(reasonForApply, taskAppliers.getReasonForApply());
	}
}