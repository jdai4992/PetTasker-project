package au.edu.sydney.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.*;

@Embeddable
public class TaskWorkerId implements Serializable {
	@ManyToOne
    @JoinColumn(name = "taskId")
    private Task taskId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
    private User userId;

    public TaskWorkerId() {}

    public TaskWorkerId(User userId, Task taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }
    
    public User getUser() {
        return userId;
    }
    
    public Task getTask() {
        return taskId;
    }
}