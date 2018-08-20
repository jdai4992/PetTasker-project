package au.edu.sydney.service;


import java.util.List;

import au.edu.sydney.model.Report;

public interface ReportService {

    void addNewReport (Report report);
    void updateReport (Report report);
    Report getReport (int ReportId);
    List getAllReports();

}