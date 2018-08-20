package au.edu.sydney.service;

import au.edu.sydney.dao.ReportDao;
import au.edu.sydney.model.Report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public void addNewReport(Report report) {
        reportDao.addNewReport(report);
    }

    @Override
    public Report getReport(int ReportId) {
        return reportDao.getReport(ReportId);
    }

	@Override
	public List getAllReports() {
		return reportDao.getAllReports();
	}

	@Override
	public void updateReport(Report report) {
		reportDao.updateReport(report);
		
	}

}