package au.edu.sydney.dao;

import au.edu.sydney.model.TaskAppliers;

import java.util.List;

public interface TaskApplierDao {

    void addNewTaskApplier (TaskAppliers task);
    TaskAppliers getTaskApplier (int id);
    List getAllTaskAppliers();

}