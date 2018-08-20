package au.edu.sydney.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import au.edu.sydney.model.Report;
import au.edu.sydney.model.Staff;
import au.edu.sydney.model.Task;
import au.edu.sydney.service.ReportService;
import au.edu.sydney.service.StaffService;

@Controller
@SessionAttributes("currentStaff")
@RequestMapping ("/staff")
public class StaffLoginController {

    @Autowired
    StaffService staffService;
    
    @Autowired
    ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayLoginForm(ModelMap modelMap) {
        modelMap.addAttribute("staff", new Staff());
        return "staff";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String validateLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap) {
        List<Staff> staffs = staffService.getAllStaffs();
        for(Staff staff : staffs) {
        	// due to manual insertion of records in the Staff database...
            //if(staff.getEmail().equals(email) && staff.getPassword().equals(Encryption.encrypt(password))) {
        	if(staff.getEmail().equals(email) && staff.getPassword().equals(password)) {
//                modelMap.addAttribute("resultMessage", "Welcome to your homepage " +  staff.getFullName() + "! You are Successfully Logged in.");
                modelMap.addAttribute("currentStaff", staff);
                return "redirect:/staff/staffHomePage";
            }
        }
        modelMap.addAttribute("staff", new Staff());
        modelMap.addAttribute("errorMessage", "Incorrect Username or Password! Please try again.");
        return "staff";
    }


	@RequestMapping("/staffHomePage")
	public String showHomePage(ModelMap modelMap, HttpSession session){
		Staff staff = (Staff) session.getAttribute("currentStaff");
		if (staff != null){
			
			List<Report> reports = reportService.getAllReports();
			List<Report> resolvedReports = new ArrayList<Report>();
			
			for (Report report : reports) {
				if (report.getStaffId() == staff.getId()) {
					resolvedReports.add(report);
				}
			}
			
			modelMap.addAttribute("resolvedReports", resolvedReports);
			
			return "staffHomePage";
		}
		return "redirect:/staff";
	}
	
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus status) {
        System.out.println("logout()");
        status.setComplete();
        return "redirect:/staff";
    }
}