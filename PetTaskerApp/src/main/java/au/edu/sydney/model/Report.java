package au.edu.sydney.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name = "reports")
public class Report{
	
	@Id
    @Column (name = "reportId")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int reportId;
	
	//id of the person making the report
	@Column (name = "reporterId")
    private int reporterId;
		
	//id of the person making the report for?
	@Column (name = "reportedPersonId")
	private int reportedPersonId;
	
	// TEST
	//id of the staff that resolved the report
	@Column (name = "staffId")
    private int staffId;
	
	@Column (name = "reason", nullable = false)
	@NotEmpty (message = "Please enter the reason:")
	private String reason;
	
	@Column (name = "title", nullable = false)
	@NotEmpty (message = "Please enter title:")
	private String title;

	@Column (name = "reportDetails")
	private String reportDetails;
	
    @Column (name = "reportStatus")
    private String reportStatus;
    
    @Column (name = "taskId")
    private int taskId;
    
    @Column (name = "reportedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getReportId() {
		return reportId;
	}
	
	public void setReportId(int reportId){
		this.reportId = reportId;
	}

	public int getReporterId() {
		return reporterId;
	}
	
	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}
	
	public int getReportedPersonId() {
		return reportedPersonId;
	}

	public void setReportedPersonId(int reportedPersonId) {
		this.reportedPersonId = reportedPersonId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//the different type of categories of report e.g. scammer
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String details) {
		this.reportDetails = details;
	}
	
	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    // TEST
	//id of the staff that resolved the report
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
}