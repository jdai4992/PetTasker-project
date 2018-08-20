package au.edu.sydney.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import au.edu.sydney.dao.Encryption;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table (name = "users")
public class User {

    @Id
    @Column (name = "userId")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int userId;
    
    @Column (name = "del", columnDefinition="boolean default false")
    private Boolean del;

    @Column (name = "firstName")
    @NotEmpty (message = "Please enter your first name")
    private String firstName;

    @Column (name = "lastName")
    @NotEmpty (message = "Please enter your last name")
    private String lastName;

    @Column (name = "email")
    @Email
    @NotEmpty (message = "Please enter a valid email address")
    private String email;
    
    @Transient
    @Email
    private String confirmEmail;

    @Column (name = "password")
    @NotEmpty(message = "Please enter your password")
    private String password;
    
    @Transient
    private String confirmPassword;
    
    @Column (name = "address")
    @NotEmpty (message = "Please enter your address")
    private String address;
    
    @Column (name = "city")
    @NotEmpty (message = "Please enter your city")
    private String city;
    
    @Column (name = "state")
    @NotEmpty (message = "Please enter your state")
    private String state;
    
    @Column (name = "postcode")
    @NotEmpty (message = "Please enter your postcode")
    @Size(max = 4)
    private String postcode;
    
    @Column (name = "country")
    @NotEmpty (message = "Please enter your country")
    private String country;
    
    @Column (name = "phoneNumber")
    @Size(min = 9, max = 10, message="Please enter your Phone number")
    private String phoneNumber;
    
    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }
    
    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
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
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName () {
        return this.firstName + " " + this.lastName;
    }
    
    public String getFullAddress () {
        return this.address + ", " + this.city + ", " + this.state + ", " + this.postcode;
    }
}
