package au.edu.sydney.service;


import java.util.List;

import au.edu.sydney.model.Staff;

public interface StaffService {

    void addNewStaff (Staff user);
    void updateStaff (Staff user);
    void deleteStaff (int id);
    Staff getStaff (int id);
    List getAllStaffs();

}
