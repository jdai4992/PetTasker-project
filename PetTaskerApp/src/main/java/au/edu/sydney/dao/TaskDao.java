package au.edu.sydney.dao;

import au.edu.sydney.model.Task;

import java.util.List;

public interface TaskDao {

    void addNewTask (Task task);
    void updateTask (Task task);
    void deleteTask (int id);
    Task getTask (int id);
    List getAllTasks();

}