package au.edu.sydney.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "tasks")
public class Task {

    @Id
    @Column (name = "taskId")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int taskId;
    
//    @Id
//    @Column (name = "adderId")
//    @GeneratedValue (strategy = GenerationType.AUTO)
//    private int adderId;

    @Column (name = "taskName")
    @NotEmpty (message = "Please enter the task name")
    private String taskName;

    @Column (name = "taskLocation")
    @NotEmpty (message = "Please enter the location for your task")
    private String taskLocation;
    
    @Column (name = "taskDescription")
    @NotEmpty (message = "Please enter the description for your task")
    private String taskDescription;
    
    @Column (name = "taskPrice")
    @Min(1)
    @NotNull (message = "Please enter the price for your task")
    private Integer taskPrice; 
    
    @Column (name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
/*    
    @Column (name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
*/    
    @Column (name = "taskStatus")
    private String taskStatus;
    
    @Column (name = "adderCompletedStatus", columnDefinition="boolean default false")
    private Boolean adderCompletedStatus;
    
    @Column (name = "workerCompletedStatus", columnDefinition="boolean default false")
    private Boolean workerCompletedStatus;
    
    @Column (name = "adderId")
    private int adderId;
    
    @Column (name = "workerId")
    private int workerId;
    
    @Column (name = "del", columnDefinition="boolean default false")
    private Boolean del;
    
    @Column (name = "taskAdderReview", columnDefinition="boolean default false")
    private Boolean taskAdderReview;

	@Column (name = "taskWorkerReview", columnDefinition="boolean default false")
    private Boolean taskWorkerReview;
    
    public int getId() {
        return taskId;
    }

    public void setId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskLocation() {
        return taskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        this.taskLocation = taskLocation;
    }
    
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public Integer getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Integer taskPrice) {
        this.taskPrice = taskPrice;
    }
   
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
/*    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }    
*/	
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
    
	public Boolean getAdderCompletedStatus() {
		return adderCompletedStatus;
	}

	public void setAdderCompletedStatus(Boolean adderCompletedStatus) {
		this.adderCompletedStatus = adderCompletedStatus;
	}
	
	public Boolean getWorkerCompletedStatus() {
		return workerCompletedStatus;
	}

	public void setWorkerCompletedStatus(Boolean workerCompletedStatus) {
		this.workerCompletedStatus = workerCompletedStatus;
	}
	
	public int getAdderId() {
		return this.adderId;
	}
	
	public void setAdderId(int adderId) {
		this.adderId = adderId;
	}

	public int getWorkerId() {
		return this.workerId;
	}
	
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	
	public Boolean getDel(){
		return this.del;
	}
	
	public void setDel(Boolean del) {
		this.del = del;
	}
    
    public Boolean getTaskAdderReview() {
		return taskAdderReview;
	}

	public void setTaskAdderReview(Boolean taskAdderReview) {
		this.taskAdderReview = taskAdderReview;
	}

	public Boolean getTaskWorkerReview() {
		return taskWorkerReview;
	}

	public void setTaskWorkerReview(Boolean taskWorkerReview) {
		this.taskWorkerReview = taskWorkerReview;
	}
	
}

