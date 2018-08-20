package au.edu.sydney.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.sydney.model.Task;
import au.edu.sydney.dao.TaskDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
								"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})

public class taskDaoTest {
	
	@Autowired
	private TaskDao taskDao;
	
	@Test
	@Transactional
	@Rollback(true)
	 public void addNewTaskTest(){
		Task task = new Task();
		
		task.setTaskName("test");
		task.setTaskLocation("test");
		task.setTaskDescription("test");
		task.setTaskPrice(1);
		
		taskDao.addNewTask(task);
		List<Task> tasks = taskDao.getAllTasks();
		
		Assert.assertEquals(task.getTaskName(), tasks.get(tasks.size()-1).getTaskName());
		Assert.assertEquals(task.getTaskLocation(), tasks.get(tasks.size()-1).getTaskLocation());
		Assert.assertEquals(task.getTaskDescription(), tasks.get(tasks.size()-1).getTaskDescription());
		Assert.assertEquals(task.getTaskPrice(), tasks.get(tasks.size()-1).getTaskPrice());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteTaskTest(){
		Task task = new Task();
		
		task.setTaskName("test");
		task.setTaskLocation("test");
		task.setTaskDescription("test");
		task.setTaskPrice(1);
		
		
		Task task1 = new Task();
		
		task1.setTaskName("test1");
		task1.setTaskLocation("test1");
		task1.setTaskDescription("test1");
		task1.setTaskPrice(2);
		
		taskDao.addNewTask(task);
		taskDao.addNewTask(task1);
		List<Task> tasks = taskDao.getAllTasks();
		
		taskDao.deleteTask(task.getId());
		
		Assert.assertNotEquals(task.getTaskName(), tasks.get(tasks.size()-1).getTaskName());
		Assert.assertNotEquals(task.getTaskLocation(), tasks.get(tasks.size()-1).getTaskLocation());
		Assert.assertNotEquals(task.getTaskDescription(), tasks.get(tasks.size()-1).getTaskDescription());
		Assert.assertNotEquals(task.getTaskPrice(), tasks.get(tasks.size()-1).getTaskPrice());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateReportTest(){

		Task task = new Task();
		task.setTaskName("test");
		task.setTaskLocation("test");
		task.setTaskDescription("test");
		task.setTaskPrice(1);

		taskDao.addNewTask(task);
		List<Task> tasks = taskDao.getAllTasks();
		
		Task task1 = new Task();		
		task1.setTaskName("test1");
		task1.setTaskLocation("test1");
		task1.setTaskDescription("test1");
		task1.setTaskPrice(2);
		task1.setId(task.getId());
		taskDao.updateTask(task1);
		
		Assert.assertEquals(task.getTaskName(), task1.getTaskName());
		Assert.assertEquals(task.getTaskLocation(), task1.getTaskLocation());
		Assert.assertEquals(task.getTaskDescription(), task1.getTaskDescription());
		Assert.assertEquals(task.getTaskPrice(), task1.getTaskPrice());
	}
}
