package au.edu.sydney.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.sydney.model.Report;
import au.edu.sydney.model.Staff;
import au.edu.sydney.service.ReportService;


@Controller
//@SessionAttributes("currentStaff")

//@RequestMapping ("/staff/staffReport")
public class StaffReportController {

	@Autowired
	ReportService reportService; 

	@RequestMapping(value = "/staff/staffReport", method = RequestMethod.GET)
	public String displayTask(ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentStaff") != null){
			//modelMap.addAttribute("staff", new Staff());
			modelMap.addAttribute("reports", reportService.getAllReports());
			return "staffReport";
		}
		return "redirect:/staff";
	}



	//get report details by their id
	@RequestMapping(value = "/staff/staffReport/{reportId}", method = RequestMethod.GET)
	public String getReportById(@PathVariable("reportId") int reportId, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentStaff") != null) {
			//if the reportId exists in the database, display the reportDetails page
			if (reportService.getReport(reportId) != null) {
				System.out.print("its in the if");
				modelMap.addAttribute("report", reportService.getReport(reportId));
				return "reportDetail";
			} else {
				//if the reportId doesn't exists in the database display the invalid request page
				return "invalidRequest";
			}
		}
		//redirect to staff login page if the staff isn't logged in
		return "redirect:/staff";
	}

	@RequestMapping(value="/staff/staffReport/{reportId}/resolved", method = RequestMethod.GET)
	public String reportResolved(@PathVariable("reportId") int reportId, ModelMap modelMap, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("currentStaff");
		
		if (staff != null) {

			Report report = reportService.getReport(reportId);
			
			if (report.getReportStatus() == null) {
				System.out.println("i'm here!!!");
			
			}
			
			if (report.getReportStatus().equals("INCOMPLETE")) {
				report.setReportStatus("COMPLETE");
				
				// currentStaff was the staff responsible!
				report.setStaffId(staff.getId());
				
				reportService.updateReport(report);
				
				return "redirect:/staff/staffReport";
			}
			// else nothing

			return "reportDetail";
		}

		return "redirect:/staff";
	}
	
	@RequestMapping(value="/staff/staffReport/{reportId}/unresolved", method = RequestMethod.GET)
	public String reportUnresolved(@PathVariable("reportId") int reportId, ModelMap modelMap, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("currentStaff");
		
		if (staff != null) {

			Report report = reportService.getReport(reportId);
			
			if (report.getReportStatus().equals("COMPLETE")) {
				report.setReportStatus("INCOMPLETE");
				
				// no staff is responsible yet!
				report.setStaffId(-1);
				
				reportService.updateReport(report);
				return "redirect:/staff/staffReport";
			}
			// else nothing

			return "reportDetail";
		}

		return "redirect:/staff";
	}

}