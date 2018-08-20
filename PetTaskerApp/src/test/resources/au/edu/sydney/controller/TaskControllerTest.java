package au.edu.sydney.controller;

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

import au.edu.sydney.model.Task;
import au.edu.sydney.service.TaskService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) 
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@ActiveProfiles("test")
public class TaskControllerTest {

	@InjectMocks
	TaskController controller;
	
	@Autowired
	TaskService taskService;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	TaskService mock = org.mockito.Mockito.mock(TaskService.class);

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void testListOfTasksPage() throws Exception {
		//if the user isn't logged-in (i.e. there is no session) then redirect the '/tasks' url to '/' (homepage)
		mvc.perform(MockMvcRequestBuilders.get("/tasks"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/"));

		//after creating a valid user session
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		assertTrue(sessionattr.isEmpty());
		sessionattr.put("currentUser", "jahnavi@g.com");
		assertNotNull(sessionattr);
		assertFalse(sessionattr.isEmpty());

		List<Task> tasks = taskService.getAllTasks();

		mvc.perform(MockMvcRequestBuilders.get("/tasks").sessionAttrs(sessionattr))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("task"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/task.jsp"))
		.andExpect(MockMvcResultMatchers.model().attribute("tasks", hasSize(tasks.size())))
        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
                allOf(
                        hasProperty("taskName", is(tasks.get(0).getTaskName())),
                        hasProperty("taskLocation", is(tasks.get(0).getTaskLocation())),
                        hasProperty("startDate", is(tasks.get(0).getStartDate())),
                        hasProperty("taskPrice", is(tasks.get(0).getTaskPrice()))
                )
        )))
        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
                allOf(
                        hasProperty("taskName", is(tasks.get(tasks.size()-1).getTaskName())),
                        hasProperty("taskLocation", is(tasks.get(tasks.size()-1).getTaskLocation())),
                        hasProperty("startDate", is(tasks.get(tasks.size()-1).getStartDate())),
                        hasProperty("taskPrice", is(tasks.get(tasks.size()-1).getTaskPrice()))
                )
        )))
        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
                allOf(
                        hasProperty("taskName", is(tasks.get(tasks.size()-2).getTaskName())),
                        hasProperty("taskLocation", is(tasks.get(tasks.size()-2).getTaskLocation())),
                        hasProperty("startDate", is(tasks.get(tasks.size()-2).getStartDate())),
                        hasProperty("taskPrice", is(tasks.get(tasks.size()-2).getTaskPrice()))
                )
        )));
	}
}