package au.edu.sydney.dao;

import au.edu.sydney.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

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
	public void addNewUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(User newUserDetails, User currentUser) {
		
		User updateCurrentUser = getUser(currentUser.getId());

		updateCurrentUser.setFirstName(newUserDetails.getFirstName());
		updateCurrentUser.setLastName(newUserDetails.getLastName());
		updateCurrentUser.setPassword(Encryption.encrypt(newUserDetails.getPassword()));
		updateCurrentUser.setAddress(newUserDetails.getAddress());
		updateCurrentUser.setCity(newUserDetails.getCity());
		updateCurrentUser.setState(newUserDetails.getState());
		updateCurrentUser.setCountry(newUserDetails.getCountry());
		updateCurrentUser.setPostcode(newUserDetails.getPostcode());
		updateCurrentUser.setPhoneNumber(newUserDetails.getPhoneNumber());		

		sessionFactory.getCurrentSession().update(updateCurrentUser);
	}

	@Override
	public void deleteUser(int id) {
//		User user = getUser(id);
//		if(user != null) {
//			sessionFactory.getCurrentSession().delete(user);
//		}
		
		User deletedUser = getUser(id);
		Boolean del = true;
		deletedUser.setDel(del);
		sessionFactory.getCurrentSession().update(deletedUser);
	}

	@Override
	public User getUser(int id) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	public List getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User U where U.del is null").list();
	}
}
