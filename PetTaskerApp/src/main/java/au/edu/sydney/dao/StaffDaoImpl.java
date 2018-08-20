package au.edu.sydney.dao;

import au.edu.sydney.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDaoImpl implements StaffDao {

	/********************/
	/* SESSSION FACTORY */
	/*******************/
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	/************/

	@Override
	public void addNewStaff(Staff staff) {
		sessionFactory.getCurrentSession().save(staff);
	}

	@Override
	public void updateStaff(Staff staff) {
		Staff updateStaff = getStaff(staff.getId());

		updateStaff.setId(staff.getId());
		updateStaff.setFirstName(staff.getFirstName());
		updateStaff.setLastName(staff.getLastName());
		updateStaff.setEmail(staff.getEmail());
		updateStaff.setPassword(Encryption.encrypt(staff.getPassword())); //set encrypted password

		sessionFactory.getCurrentSession().merge(updateStaff);
	}

	@Override
	public void deleteStaff(int id) {
		Staff staff = getStaff(id);
		if(staff != null) {
			sessionFactory.getCurrentSession().delete(staff);
		}
	}

	@Override
	public Staff getStaff(int id) {
		Staff staff = (Staff) sessionFactory.getCurrentSession().get(Staff.class, id);
		return staff;
	}

	@Override
	public List getAllStaffs() {
		return sessionFactory.getCurrentSession().createQuery("from Staff").list();
	}
}
