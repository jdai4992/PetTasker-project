package au.edu.sydney.service;

import au.edu.sydney.model.Task;

import java.util.List;

public interface TaskService {

    void addNewTask (Task task);
    void updateTask (Task task);
    void deleteTask (int id);
    Task getTask (int id);
    List getAllTasks();

}
