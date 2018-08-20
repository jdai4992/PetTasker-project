package au.edu.sydney.dao;

import au.edu.sydney.model.TaskAppliers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskApplierDaoImpl implements TaskApplierDao {

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
	public void addNewTaskApplier(TaskAppliers taskApplier) {
		sessionFactory.getCurrentSession().save(taskApplier);
	}

	@Override
	public TaskAppliers getTaskApplier(int id) {
		TaskAppliers task = (TaskAppliers) sessionFactory.getCurrentSession().get(TaskAppliers.class, id);
		return task;
	}

	@Override
	public List getAllTaskAppliers() {
		return sessionFactory.getCurrentSession().createQuery("from TaskAppliers").list();
	}
}
