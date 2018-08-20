package au.edu.sydney.dao;

import au.edu.sydney.model.Report;
import au.edu.sydney.model.Task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDaoImpl implements ReportDao {

	/********************/
	/* SESSSION FACTORY */
	/*******************/
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	/************/

	@Override
	public void addNewReport(Report report) {
		sessionFactory.getCurrentSession().save(report);
	}


	@Override
	public Report getReport(int ReportId) {
		Report report = (Report) sessionFactory.getCurrentSession().get(Report.class, ReportId);
		return report;
	}

	@Override
	public List getAllReports() {
		return sessionFactory.getCurrentSession().createQuery("from Report").list();
	}

	@Override
	public void updateReport(Report report) {
		
		Report updateReport = getReport(report.getReportId());
		
		updateReport.setTaskId(report.getTaskId());
		updateReport.setReportId(report.getReportId());
		updateReport.setReporterId(report.getReporterId());
		updateReport.setReportedPersonId(report.getReportedPersonId());
		updateReport.setTitle(report.getTitle());
		updateReport.setReason(report.getReason());
		updateReport.setReportDetails(report.getReportDetails());
		updateReport.setStaffId(report.getStaffId());
		updateReport.setReportStatus(report.getReportStatus());
		
		sessionFactory.getCurrentSession().update(updateReport);
		
	}

}