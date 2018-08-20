package au.edu.sydney.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import au.edu.sydney.service.TaskApplierService;
import au.edu.sydney.service.TaskService;


@RunWith(SpringJUnit4ClassRunner.class) 
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
								"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@ActiveProfiles("test")
public class ApplyTaskControllerTest {

	@InjectMocks
	ApplyTaskController controller;
	
	@Autowired
	TaskApplierService taskApplierService;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	TaskService mock = org.mockito.Mockito.mock(TaskService.class);

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
}

//	@Test
//	public void testApplyFormPage() throws Exception {
//		//if the user isn't logged-in (i.e. there is no session) then redirect the '/tasks' url to '/' (homepage)
//		mvc.perform(MockMvcRequestBuilders.get("/tasks/1/apply"))
//		.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
//
//		//after creating a valid user session
//		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
//		assertTrue(sessionattr.isEmpty());
//		sessionattr.put("currentUser", "jahnavi@g.com");
//		assertNotNull(sessionattr);
//		assertFalse(sessionattr.isEmpty());
//
//		List<TaskAppliers> taskAppliers = taskApplierService.getAllTaskAppliers();
//
//		mvc.perform(MockMvcRequestBuilders.get("/tasks/1/apply").sessionAttrs(sessionattr))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.view().name("applyForTask"))
//		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/taskDetails.jsp"));
//		.andExpect(MockMvcResultMatchers.model().attribute("tasks", hasSize(tasks.size())))
//        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
//                allOf(
//                        hasProperty("taskName", is(tasks.get(0).getTaskName())),
//                        hasProperty("taskLocation", is(tasks.get(0).getTaskLocation())),
//                        hasProperty("startDate", is(tasks.get(0).getStartDate())),
//                        hasProperty("taskPrice", is(tasks.get(0).getTaskPrice()))
//                )
//        )))
//        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
//                allOf(
//                        hasProperty("taskName", is(tasks.get(1).getTaskName())),
//                        hasProperty("taskLocation", is(tasks.get(1).getTaskLocation())),
//                        hasProperty("startDate", is(tasks.get(1).getStartDate())),
//                        hasProperty("taskPrice", is(tasks.get(1).getTaskPrice()))
//                )
//        )))
//        .andExpect(MockMvcResultMatchers.model().attribute("tasks", hasItem(
//                allOf(
//                        hasProperty("taskName", is(tasks.get(2).getTaskName())),
//                        hasProperty("taskLocation", is(tasks.get(2).getTaskLocation())),
//                        hasProperty("startDate", is(tasks.get(2).getStartDate())),
//                        hasProperty("taskPrice", is(tasks.get(2).getTaskPrice()))
//                )
//        )));
//		sessionattr.put();
//	}
	
//}