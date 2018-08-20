package au.edu.sydney.dao;

import au.edu.sydney.model.Reviews;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewsDaoImpl implements ReviewsDao {

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
	public void addNewReviews(Reviews review) {
		sessionFactory.getCurrentSession().save(review);
	}


	@Override
	public Reviews getReviews(int ReviewsId) {
		Reviews review = (Reviews) sessionFactory.getCurrentSession().get(Reviews.class, ReviewsId);
		return review;
	}

	@Override
	public List getAllReviews() {
		return sessionFactory.getCurrentSession().createQuery("from Reviews").list();
	}
}