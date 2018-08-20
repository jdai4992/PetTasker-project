package au.edu.sydney.dao;


import java.util.List;

import au.edu.sydney.model.Report;
import au.edu.sydney.model.Task;

public interface ReportDao {

    void addNewReport (Report report);
    Report getReport (int ReportId);
    List getAllReports();
    void updateReport(Report report);
}