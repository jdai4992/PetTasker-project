package au.edu.sydney.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.sydney.model.Report;
import au.edu.sydney.dao.ReportDao;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class reportDaoTest {
	
	@Autowired
	private ReportDao reportDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void addNewReportTest(){
		Report report = new Report();
		report.setReason("test");
		report.setTitle("test");
		
		reportDao.addNewReport(report);
		List<Report> reports = reportDao.getAllReports();
		
		Assert.assertEquals(report.getReason(), reports.get(reports.size()-1).getReason());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateReportTest(){
		Report report = new Report();
		report.setReason("test");
		report.setTitle("test");
		
		reportDao.addNewReport(report);
		List<Report> reports = reportDao.getAllReports();
		
		Report report1 = new Report();
		report1.setReason("test1");
		report1.setTitle("test1");
		report1.setReportId(report.getReportId());
		reportDao.updateReport(report1);
		
		Assert.assertEquals(report.getReason(), report1.getReason());
	}

}
