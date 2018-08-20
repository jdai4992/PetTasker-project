package au.edu.sydney.service;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.sydney.model.Task;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.model.TaskWorkerId;
import au.edu.sydney.model.User;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
								"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
public class TaskApplierServiceTest {
     
    @Autowired
    private TaskApplierService taskApplierService;
     
    @Test
    //place transactional and rollback so that it doesn't actually save it into the database
    @Transactional
    @Rollback(true)
    public void testAddTaskApplier() throws Exception {
        TaskAppliers taskApplier = new TaskAppliers();
		Task task = new Task();
		User user = new User();
        //get all the taskAppliers from the database
        List<Task> taskAppliers = taskApplierService.getAllTaskAppliers();
        assertEquals(taskAppliers.size(), taskAppliers.size());
        
        assertNull(taskApplier.getId());
        assertNull(taskApplier.getReasonForApply());
     
        //set new taskApplier properties for new taskApplier
		int userId = 2;
		int taskId = 1;
		task.setId(taskId);
		user.setId(userId);
		taskApplier.setId(new TaskWorkerId(user,task));
		String reasonForApply = "I'm a keen person who loves pets. Would love to look after your pet dog";
		taskApplier.setReasonForApply(reasonForApply);
		
        assertNotNull(taskApplier.getId());
        assertNotNull(taskApplier.getReasonForApply());
        
		//add the taskApplierService
		taskApplierService.addNewTaskApplier(taskApplier);
        
        //get all the taskAppliers again
        taskAppliers = taskApplierService.getAllTaskAppliers();
        
        assertEquals(taskAppliers.size(), taskAppliers.size());
        assertEquals(taskId, taskApplier.getId().getTask().getId());
        assertEquals(userId, taskApplier.getId().getUser().getId());
        assertEquals(reasonForApply, taskApplier.getReasonForApply());
    }
}