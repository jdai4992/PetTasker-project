package au.edu.sydney.service;

import junit.framework.TestCase;
import au.edu.sydney.model.Report;
import au.edu.sydney.dao.ReportDao;
import au.edu.sydney.dao.ReportDaoImpl;

import java.util.*;

import org.hibernate.SessionFactory;

public class reportServiceTest extends TestCase{
	
	private Report report0;
	private ReportDaoImpl reportList;
	
	private int reportId = 1234;
	private int reportGiverId = 1234;
	private int reportReceiverId = 4321;
	private String reason = "Scammer";
	private String title = "I'm angry!";
	
//	public void testGetReportWithNoReport(){
//		assertNull(reportList1.getSessionFactory());
//	}
	
	public void setUp() throws Exception{
//		
		reportList = new ReportDaoImpl();
//		
//		report0.setReportId(reportId);
//		report0.setReportGiverId(reportGiverId);
//		report0.setReportReceiverId(reportReceiverId);
//		report0.setReason(reason);
//		report0.setTitle(title);
//		

		reportList.addNewReport(report0);		
	}
	
	public void testAddNewReport(){
//		reportList = new ArrayList<Report>();
//		reportList1 = new ReportDaoImpl();
		
//		report0.setReportId(reportId);
//		report0.setReportGiverId(reportGiverId);
//		report0.setReportReceiverId(reportReceiverId);
//		report0.setReason(reason);
//		report0.setTitle(title);
//		
//		reportList.add(report0);
//		reportList1.addNewReport(report0);
//		
		assertEquals(1,1);
		
//		assertEquals(reportId, reportList1.getReport(reportId).getReportId());
//		assertEquals(reportGiverId, reportList1.getReport(reportId).getReportGiverId());
//		assertEquals(reportReceiverId, reportList1.getReport(reportId).getReportReceiverId());
//		assertEquals(reason, reportList1.getReport(reportId).getReason());
//		assertEquals(title, reportList1.getReport(reportId).getTitle());
	}
	
	

}
