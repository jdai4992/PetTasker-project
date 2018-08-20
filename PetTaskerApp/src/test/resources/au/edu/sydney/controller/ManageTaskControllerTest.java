package au.edu.sydney.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import au.edu.sydney.controller.LoginController;
import au.edu.sydney.dao.StaffDao;
import au.edu.sydney.dao.TaskDao;
import au.edu.sydney.model.Staff;
import au.edu.sydney.model.Task;
import au.edu.sydney.service.TaskService;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ManageTaskControllerTest {

	@Autowired
	ApplicationContext applicationContext;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter; 

	
	@InjectMocks
	private ManageTaskController manageTaskController;
	
	@Autowired
	TaskService taskService;

	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	TaskService mock = org.mockito.Mockito.mock(TaskService.class);

	@Before
	public void setUp(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

		handlerAdapter = new AnnotationMethodHandlerAdapter();
		manageTaskController = new ManageTaskController();
	}

	@Test
	public void testValidDisplay() throws Exception{
		request.setRequestURI("/staff/manageTask");
		request.setMethod("GET");

		// session
		
		// without the session, redirects to staff Login page
		mvc.perform(MockMvcRequestBuilders.get("/tasks"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
		
		// with session
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		assertTrue(sessionattr.isEmpty());
		sessionattr.put("currentStaff", "rj@g.com");
		assertNotNull(sessionattr);
		assertFalse(sessionattr.isEmpty());

		//final ModelAndView mav = handlerAdapter.handle(request, response, manageTaskController);

		List<Task> tasks = taskService.getAllTasks();

		mvc.perform(MockMvcRequestBuilders.get("/tasks").sessionAttrs(sessionattr))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("task"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/manageTask.jsp"))
		.andExpect(MockMvcResultMatchers.model().attribute("tasks", hasSize(tasks.size())))
		.andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
				allOf(
						hasProperty("taskId", is(tasks.get(0).getId())),    
						hasProperty("taskName", is(tasks.get(0).getTaskName())),
						hasProperty("adderId", is(tasks.get(0).getAdderId())),
						hasProperty("startDate", is(tasks.get(0).getStartDate())),
						hasProperty("taskStatus", is(tasks.get(0).getTaskStatus()))
						)
				)));

		//		Task task = new Task();
		//
		//		task.setTaskName("taskName");
		//		task.setTaskLocation("taskLocation");
		//		task.setTaskDescription("description");
		//		task.setTaskPrice(12);
		//		
		//		mav.addObject(task);
		//		
		////		taskDao.addNewTask(task);
		////		List<Task> staffs = taskDao.getAllTasks();
		//		
		//		Task taskTest = mav.getModel().get("tasks").get(0);
		//		
		//		assetEquals(task.getTaskName(), mav.getModel().getTaskName());
		//		assertNotNull(taskTest);
		//		assertTrue(mav.hasView());
		//		assertEquals(task.getTaskName(), mav.getModel().get("tasks").get(0));
		//		assertEquals("staff", mav.getViewName());
	}
}
