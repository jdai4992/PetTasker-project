package au.edu.sydney.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.sydney.model.Report;
import au.edu.sydney.model.Task;
import au.edu.sydney.model.User;
import au.edu.sydney.service.ReportService;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;


@Controller

public class ReportController{
		
		@Autowired
		private ReportService reportService;
		
		@Autowired
		TaskService taskService;
		
		@Autowired
	    UserService userService;
	   
		
		@RequestMapping(value = "/tasks/{taskId}/report", method = RequestMethod.GET)
	    public String displayReport(ModelMap modelMap, HttpSession session) {
			if (session.getAttribute("currentUser") != null) {
		        modelMap.addAttribute("report", new Report());
		        return "report";	        
			}else{
			return "redirect:/";
	}
	    }
		
		@RequestMapping(value = "/tasks/{taskId}/report", method = RequestMethod.POST)
		public String submitOfReport(@PathVariable("taskId") int taskId, @Valid Report report, BindingResult result, ModelMap modelMap, HttpSession session){
			if (session.getAttribute("currentUser") != null) {
				if (result.hasErrors()){
		    		return "report";
		    	}
				modelMap.addAttribute("report", new Report());
				//get the current user
				User user = (User) session.getAttribute("currentUser");
				int userId = user.getId();
				Task task = taskService.getTask(taskId);
//				System.out.print("Worker id: " + task.getWorkerId());
//				System.out.print("Adder id: " + task.getAdderId());
				
				if(task.getWorkerId() != 0){
					if (userId == task.getAdderId()){
						report.setReportedPersonId(task.getWorkerId());
					}else{
						report.setReportedPersonId(task.getAdderId());
					}
				}else{
					report.setReportedPersonId(task.getAdderId());
				}
				
				report.setReporterId(userId);
				report.setTaskId(taskId);
				
				// date
				Date date = new Date();
				report.setStartDate(date);
				
				report.setReportStatus("INCOMPLETE");
				
				reportService.addNewReport(report);
				return "redirect:/userHomePage";
			}else{
				return "redirect:/";
			}
			
		}
		
}