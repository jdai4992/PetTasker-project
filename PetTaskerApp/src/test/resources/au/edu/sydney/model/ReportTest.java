package au.edu.sydney.model;

import junit.framework.TestCase;

public class ReportTest extends TestCase{
	
	private Report report;
	
	protected void setUp() throws Exception{
		report = new Report();
	}
	
	public void testSetAndGetReportId(){
		int testId = 12345;
		assertNotNull(report.getReportId());
		report.setReportId(testId);
		assertEquals(testId, report.getReportId());
	}
	
	public void testSetAndGetReportGiverId(){
		int testId = 12345;
		assertNotNull(report.getReporterId());
		report.setReporterId(testId);
		assertEquals(testId, report.getReporterId());
	}
	
	public void testSetAndGetReportReceiverId(){
		int testId = 12345;
		assertNotNull(report.getReportedPersonId());
		report.setReportedPersonId(testId);
		assertEquals(testId, report.getReportedPersonId());
	}
	
	public void testSetAndGetTitle(){
		String testTitle = "I'm angry!";
		assertNull(report.getTitle());
		report.setTitle(testTitle);
		assertEquals(testTitle, report.getTitle());
	}
	
	public void testSetAndGetReason(){
		String testReason = "Scammer";
		report.setReason(testReason);
		assertEquals(testReason, report.getReason());
	}

}
