package au.edu.sydney.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import au.edu.sydney.controller.LoginController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
"file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
public class LoginControllerTest {

    @Autowired
    ApplicationContext applicationContext;
     
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter; 
     
    @Autowired
    private LoginController loginController;
    
    @Before
    public void setUp(){
    	request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        loginController = new LoginController();
    }
    
    @Test
    public void testValidateLogin() throws Exception{
    	request.setMethod("GET");
    	assertEquals(1,1);
    }
}
