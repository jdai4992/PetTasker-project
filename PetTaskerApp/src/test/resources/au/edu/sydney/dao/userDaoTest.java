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
import au.edu.sydney.model.User;
import au.edu.sydney.dao.UserDao;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class userDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void addNewUserTest(){
		
		User user = new User();
		
		user.setFirstName("Dai");
		user.setLastName("testing");
		user.setDel(false);
		user.setEmail("testing@qq.com");
		user.setConfirmEmail("testing@qq.com");
		user.setPassword("testing");
		user.setConfirmPassword("testing");
		user.setAddress("testing");
		user.setState("testing");
		user.setPostcode("1111");
		user.setCity("testing");
		user.setCountry("testing");
		user.setPhoneNumber("111111111");
		
		userDao.addNewUser(user);
		List<User> users = userDao.getAllUsers();
		

		Assert.assertEquals(user.getId(), users.get(users.size()-1).getId());
		Assert.assertEquals(user.getFirstName(), users.get(users.size()-1).getFirstName());
		Assert.assertEquals(user.getLastName(), users.get(users.size()-1).getLastName());
		Assert.assertEquals(user.getEmail(), users.get(users.size()-1).getEmail());
		Assert.assertEquals(user.getPassword(), users.get(users.size()-1).getPassword());
		Assert.assertEquals(user.getAddress(), users.get(users.size()-1).getAddress());
		Assert.assertEquals(user.getState(), users.get(users.size()-1).getState());
		Assert.assertEquals(user.getPostcode(), users.get(users.size()-1).getPostcode());
		Assert.assertEquals(user.getCountry(), users.get(users.size()-1).getCountry());
		Assert.assertEquals(user.getPhoneNumber(), users.get(users.size()-1).getPhoneNumber());

	}

}
