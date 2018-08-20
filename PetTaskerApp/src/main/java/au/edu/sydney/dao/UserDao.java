package au.edu.sydney.dao;

import au.edu.sydney.model.User;

import java.util.List;

public interface UserDao {

    void addNewUser (User user);
    void updateUser(User newUserDetails, User currentUser);
    void deleteUser (int id);
    User getUser (int id);
    List getAllUsers();

}