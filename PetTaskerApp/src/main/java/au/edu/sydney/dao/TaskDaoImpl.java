package au.edu.sydney.dao;

import au.edu.sydney.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

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
	public void addNewTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	@Override
	public void updateTask(Task task) {
		Task updateTask = getTask(task.getId());

		updateTask.setId(task.getId());
		updateTask.setTaskName(task.getTaskName());
		updateTask.setTaskLocation(task.getTaskLocation());
		updateTask.setTaskDescription(task.getTaskDescription());
		updateTask.setTaskPrice(task.getTaskPrice());
		System.out.println(task.getTaskStatus());
		updateTask.setTaskStatus(task.getTaskStatus());
		updateTask.setWorkerId(task.getWorkerId());
		updateTask.setWorkerCompletedStatus(task.getWorkerCompletedStatus());
		updateTask.setAdderCompletedStatus(task.getAdderCompletedStatus());
		updateTask.setTaskAdderReview(task.getTaskAdderReview());
		updateTask.setTaskWorkerReview(task.getTaskWorkerReview());

		sessionFactory.getCurrentSession().update(updateTask);
	}

	@Override
	public void deleteTask(int id) {
		Task deleteTask = getTask(id);
		Boolean del = true;
		deleteTask.setDel(del);
		sessionFactory.getCurrentSession().update(deleteTask);
	}

	@Override
	public Task getTask(int id) {
		Task task = (Task) sessionFactory.getCurrentSession().get(Task.class, id);
		return task;
	}

	@Override
	public List getAllTasks() {
		return sessionFactory.getCurrentSession().createQuery("from Task T where T.del is null").list();
	}
}
