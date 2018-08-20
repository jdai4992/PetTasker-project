package au.edu.sydney.service;

import au.edu.sydney.dao.TaskDao;
import au.edu.sydney.model.Task;
import au.edu.sydney.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

	@Override
	public void addNewTask(Task task) {
		taskDao.addNewTask(task);		
	}

	@Override
	public void updateTask(Task task) {
		taskDao.updateTask(task);		
	}

	@Override
	public void deleteTask(int id) {
		taskDao.deleteTask(id);
	}

	@Override
	public Task getTask(int id) {
		return taskDao.getTask(id);
	}

	@Override
	public List getAllTasks() {
        return taskDao.getAllTasks();
	}
}
