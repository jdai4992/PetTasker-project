package au.edu.sydney.service;

import au.edu.sydney.dao.UserDao;
import au.edu.sydney.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addNewUser(User user) {
        userDao.addNewUser(user);
    }

    @Override
    public void updateUser(User newUserDetails, User currentUser) {
        userDao.updateUser(newUserDetails, currentUser);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public List getAllUsers() {
        return userDao.getAllUsers();
    }
}
