package au.edu.sydney.service;

import au.edu.sydney.dao.StaffDao;
import au.edu.sydney.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public void addNewStaff(Staff staff) {
        staffDao.addNewStaff(staff);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffDao.updateStaff(staff);
    }

    @Override
    public void deleteStaff(int id) {
        staffDao.deleteStaff(id);
    }

    @Override
    public Staff getStaff(int id) {
        return staffDao.getStaff(id);
    }

    @Override
    public List getAllStaffs() {
        return staffDao.getAllStaffs();
    }
}
