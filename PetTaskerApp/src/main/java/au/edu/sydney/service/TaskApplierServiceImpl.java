package au.edu.sydney.service;

import au.edu.sydney.dao.TaskApplierDao;
import au.edu.sydney.model.TaskAppliers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskApplierServiceImpl implements TaskApplierService {

    @Autowired
    private TaskApplierDao taskApplierDao;

	@Override
	public void addNewTaskApplier(TaskAppliers taskApplier) {
		taskApplierDao.addNewTaskApplier(taskApplier);		
	}

	@Override
	public TaskAppliers getTaskApplier(int id) {
		return taskApplierDao.getTaskApplier(id);
	}

	@Override
	public List getAllTaskAppliers() {
        return taskApplierDao.getAllTaskAppliers();
	}
}
