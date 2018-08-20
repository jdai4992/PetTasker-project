package au.edu.sydney.service;

import au.edu.sydney.model.TaskAppliers;

import java.util.List;

public interface TaskApplierService {

    void addNewTaskApplier (TaskAppliers task);
    TaskAppliers getTaskApplier (int id);
    List getAllTaskAppliers();

}
