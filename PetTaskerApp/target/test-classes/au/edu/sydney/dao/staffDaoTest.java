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

import au.edu.sydney.model.Staff;
import au.edu.sydney.dao.StaffDao;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class staffDaoTest {
	
	@Autowired
	private StaffDao staffDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void addNewStaffTest(){
		
		boolean a = false;
		
		Staff staff = new Staff();
		
		staff.setFirstName("Dai");
		staff.setLastName("testing");
		staff.setEmail("testing@qq.com");
		staff.setPassword("testing");
		staff.setPhoneNumber("111111111");
		staff.setTechStatus(true);
		
		staffDao.addNewStaff(staff);
		List<Staff> staffs = staffDao.getAllStaffs();
		

		Assert.assertEquals(staff.getFirstName(), staffs.get(staffs.size()-1).getFirstName());
		Assert.assertEquals(staff.getLastName(), staffs.get(staffs.size()-1).getLastName());
		Assert.assertEquals(staff.getEmail(), staffs.get(staffs.size()-1).getEmail());
		Assert.assertEquals(staff.getPassword(), staffs.get(staffs.size()-1).getPassword());
		Assert.assertEquals(staff.getPhoneNumber(), staffs.get(staffs.size()-1).getPhoneNumber());
		Assert.assertEquals(staff.getTechStatus(), staffs.get(staffs.size()-1).getTechStatus());

	}

}
