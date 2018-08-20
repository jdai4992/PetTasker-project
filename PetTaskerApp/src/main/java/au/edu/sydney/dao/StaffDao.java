package au.edu.sydney.dao;

import au.edu.sydney.model.Staff;

import java.util.List;

public interface StaffDao {

    void addNewStaff (Staff user);
    void updateStaff (Staff user);
    void deleteStaff (int id);
    Staff getStaff (int id);
    List getAllStaffs();

}