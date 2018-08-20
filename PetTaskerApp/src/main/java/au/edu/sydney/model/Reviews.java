package au.edu.sydney.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import au.edu.sydney.dao.Encryption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "reviews")
public class Reviews{
	
	@Id
    @Column (name = "reviewsId")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int reviewsId;

	@Column (name = "reviewsGiverId")
    private int reviewsGiverId;
		
	@Column (name = "reviewsRecieverId")
	private int reviewsRecieverId;
	
	@Column (name = "taskId")
	private int taskId;

	@Column (name = "attitude")
	@NotNull (message = "Please enter ratings for attitude")
	private Integer attitude;
	
	@Column (name = "honesty")
	@NotNull (message = "Please enter ratings for honesty")
	private Integer honesty;
	
	@Column (name = "efficiency")
	@NotNull (message = "Please enter ratings for efficiency")
	private Integer efficiency;
	
	@Column (name = "overall")
	@NotNull (message = "Please enter ratings for overall")
	private Integer overall;

	@Column (name = "reviewsDetails")
	private String reviewsDetails;
	
	public int getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(int reviewsId) {
		this.reviewsId = reviewsId;
	}

	public int getReviewsGiverId() {
		return reviewsGiverId;
	}
	
	public void setReviewsGiverId(int reviewsGiverId) {
		this.reviewsGiverId = reviewsGiverId;
	}

	public int getReviewsRecieverId() {
		return reviewsRecieverId;
	}	
	
	public void setReviewsRecieverId(int reviewsRecieverId) {
		this.reviewsRecieverId = reviewsRecieverId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Integer getAttitude() {
		return attitude;
	}

	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}

	public Integer getHonesty() {
		return honesty;
	}

	public void setHonesty(Integer honesty) {
		this.honesty = honesty;
	}

	public Integer getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}	
	
	public Integer getOverall() {
		return overall;
	}

	public void setOverall(Integer overall) {
		this.overall = overall;
	}

	public String getReviewsDetails() {
		return reviewsDetails;
	}

	public void setReviewsDetails(String details) {
		this.reviewsDetails = details;
	}
}