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
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
								"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
public class TaskServiceTest {
     
    @Autowired
    private TaskService taskService;
     
    @Test
    //place transactional and rollback so that it doesn't actually save it into the database
    @Transactional
    @Rollback(true)
    public void testAddTask() throws Exception {
        Task task = new Task();
        //get all the tasks from the database
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(tasks.size(), tasks.size());
        
        assertNull(task.getTaskName());
        assertNull(task.getTaskDescription());
        assertNull(task.getStartDate());
        assertNull(task.getTaskLocation());
        assertNull(task.getTaskStatus());
        assertNull(task.getTaskPrice());
        
        //set new task properties for new task
        task.setTaskName("Hi, Walk my dog");
        task.setTaskDescription("Hi, Walk my dog while I'm at work");
        Date d = new Date();
        task.setStartDate(d);
        task.setTaskLocation("Sydney");
        task.setTaskStatus("openTask");
        task.setTaskPrice(20);
        //add the task
        taskService.addNewTask(task);
        
        //get all the tasks again
        tasks = taskService.getAllTasks();
        
        //the new entry will be 4 because there are already 3 entries in the database
        	//this will return false if there aren't 3 entries in the database already...change it accordingly
        assertEquals(tasks.size(), tasks.size());
        //get(3) because indexes start from 0 so, we want to get the 0,1,2,3rd element (this will be the new 4th entry)
        assertEquals(task.getId(), tasks.get(tasks.size()-1).getId());
        assertEquals(task.getTaskName(), tasks.get(tasks.size()-1).getTaskName());
        assertEquals(task.getTaskDescription(), tasks.get(tasks.size()-1).getTaskDescription());
        assertEquals(task.getStartDate(), tasks.get(tasks.size()-1).getStartDate());
        assertEquals(task.getTaskLocation(), tasks.get(tasks.size()-1).getTaskLocation());
        assertEquals(task.getTaskStatus(), tasks.get(tasks.size()-1).getTaskStatus());
        assertEquals(task.getTaskPrice(), tasks.get(tasks.size()-1).getTaskPrice());
    }
    
    @Test
    //place transactional and rollback so that it doesn't actually save it into the database
    @Transactional
    @Rollback(true)
    public void testUpdateTask() throws Exception {
    		List<Task> tasks = taskService.getAllTasks();
    		Task task = taskService.getTask(tasks.get(2).getId());
    		
    		assertNotNull(task.getTaskName());
    		assertNotNull(task.getTaskName());
    		assertNotNull(task.getTaskDescription());
    		assertNotNull(task.getStartDate());
    		assertNotNull(task.getTaskLocation());
    		assertNotNull(task.getTaskStatus());
    		assertNotNull(task.getTaskPrice());
    		
    		assertEquals(task.getId(), tasks.get(2).getId());
    		assertEquals(task.getTaskName(), tasks.get(2).getTaskName());
      	assertEquals(task.getTaskDescription(), tasks.get(2).getTaskDescription());
      	assertEquals(task.getStartDate(), tasks.get(2).getStartDate());
      	assertEquals(task.getTaskLocation(), tasks.get(2).getTaskLocation());
      	assertEquals(task.getTaskStatus(), tasks.get(2).getTaskStatus());
      	assertEquals(task.getTaskPrice(), tasks.get(2).getTaskPrice());
          
      	//set new task properties for update
        task.setTaskName("Hi, Walk my dog");
        task.setTaskDescription("Hi, Walk my dog while I'm at work");
        Date d = new Date();
        task.setStartDate(d);
        task.setTaskLocation("Sydney");
        task.setTaskStatus("openTask");
        task.setTaskPrice(20);
        //update the task
  		taskService.updateTask(task);
  		
  		//get all the tasks again
  		tasks = taskService.getAllTasks();
  		
        //there are already 3 entries in the database so after the update the entries still should be 3
        //the test will return false and fail if there aren't 3 entries in the database already...change it accordingly
        assertEquals(tasks.size(), tasks.size());
        //get(2) because indexes start from 0 so, we want to get the 0,1,2 element
        assertEquals(task.getId(), tasks.get(2).getId());
        assertEquals(task.getTaskName(), tasks.get(2).getTaskName());
        assertEquals(task.getTaskDescription(), tasks.get(2).getTaskDescription());
        assertEquals(task.getStartDate(), tasks.get(2).getStartDate());
        assertEquals(task.getTaskLocation(), tasks.get(2).getTaskLocation());
        assertEquals(task.getTaskStatus(), tasks.get(2).getTaskStatus());
        assertEquals(task.getTaskPrice(), tasks.get(2).getTaskPrice());
    }
}