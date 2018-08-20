package au.edu.sydney.model;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private User user;
	
	protected void setUp() throws Exception{
		user = new User();
	}
	
	public void testSetAndGetId(){
		int testId = 12345;
		assertNotNull(user.getId());
		user.setId(testId);
		assertEquals(testId, user.getId());
	}
	
	public void testSetAndGetFirstName(){
		String testFirstName = "aFirstName";
		assertNull(user.getFirstName());
		user.setFirstName(testFirstName);
		assertEquals(testFirstName, user.getFirstName());
	}
	
	public void testSetAndGetLastName(){
		String testLastName = "aLastName";
		assertNull(user.getLastName());
		user.setLastName(testLastName);
		assertEquals(testLastName, user.getLastName());
	}
	
	public void testEmail(){
		String testEmail = "123456@qq.com";
		assertNull(user.getEmail());
		user.setEmail(testEmail);
		assertEquals(testEmail, user.getEmail());
	}
	
	public void address(){
		String testAddress = "aTestAddress";
		assertNull(user.getAddress());
		user.setAddress(testAddress);
		assertEquals(testAddress, user.getAddress());
	}
	
	public void testPhoneNumber(){
		String testPhoneNumber = "123456789";
		assertNull(user.getPhoneNumber());
		user.setPhoneNumber(testPhoneNumber);
		assertEquals(testPhoneNumber, user.getPhoneNumber());
	}
}