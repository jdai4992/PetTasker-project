package au.edu.sydney.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import au.edu.sydney.dao.Encryption;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table (name = "staffs")
public class Staff {
	
	@Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "firstName")
    @NotEmpty (message = "Please enter your first name")
    private String firstName;

    @Column (name = "lastName")
    @NotEmpty (message = "Please enter your last name")
    private String lastName;

    @Column (name = "email")
    @Email
    @NotEmpty (message = "Please enter a valid email address (in the correct format)")
    private String email;
    
    @Transient
    @Email
    @NotEmpty (message = "Email and Confirm Email does not match")
    private String confirmEmail;

    @Column (name = "password")
    @NotEmpty(message = "Please enter your password")
    private String password;
    
    @Transient
    @NotEmpty(message = "Password and Confirm Password does not match")
    private String confirmPassword;
    
    @Column (name = "phoneNumber")
    @Size(min = 9, max = 10, message="Please enter your Phone number")
    private String phoneNumber;
    
    @Column(name = "technical", columnDefinition="boolean default false")
    @NotEmpty(message = "Are you in the technical team?")
	private boolean technical;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Encryption.encrypt(password);
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = Encryption.encrypt(confirmPassword);
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public boolean getTechStatus() {
        return technical;
    }

    public void setTechStatus(boolean technical) {
        this.technical = technical;
    }

    public String getFullName () {
        return this.firstName + " " + this.lastName;
    }
    
}
