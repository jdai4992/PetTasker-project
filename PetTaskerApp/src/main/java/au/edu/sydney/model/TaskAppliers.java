package au.edu.sydney.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name = "taskAppliers")
public class TaskAppliers implements Serializable {
	
    @EmbeddedId
    private TaskWorkerId taskWorderId;
    
    @Column (name = "reasonForApply")
    @NotEmpty (message = "Please enter the reason for apply")
    private String reasonForApply;
    
    public TaskWorkerId getId() {
        return taskWorderId;
    }

    public void setId(TaskWorkerId taskWorderId) {
        this.taskWorderId = taskWorderId;
    }
    
    public String getReasonForApply() {
        return reasonForApply;
    }

    public void setReasonForApply(String reasonForApply) {
        this.reasonForApply = reasonForApply;
    }
}
