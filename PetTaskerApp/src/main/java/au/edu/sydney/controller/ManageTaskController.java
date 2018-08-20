package au.edu.sydney.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.sydney.model.Task;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;

@Controller
//@RequestMapping ("/staff/manageUser")
public class ManageTaskController {

	@Autowired
	TaskService taskService; 
	@Autowired
	UserService userService; 

	@RequestMapping(value="/staff/manageTask", method=RequestMethod.GET)
	public String displayManageUser(ModelMap modelMap, HttpSession session){
		if (session.getAttribute("currentStaff") != null){
			modelMap.addAttribute("tasks", taskService.getAllTasks());
			return "manageTask";

		}
		return "redirect:/staff";
	}

	@RequestMapping(value="/staff/manageTask/{taskId}", method=RequestMethod.GET)
	public String getUserById(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session){
		if (session.getAttribute("currentStaff") != null){

			//if the reportId exists in the database, display the Task Detail page
			if (taskService.getTask(taskId) != null) {

				modelMap.addAttribute("task", taskService.getTask(taskId));

				modelMap.addAttribute("users", userService.getAllUsers());
				return "staffTaskDetail";
			} else {
				return "redirect:/staff/manageTask";
			}
		}
		return "redirect:/staff";
	}

	@RequestMapping(value = "/staff/manageTask/{taskId}/delete", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentStaff") != null) {			
			Task task = taskService.getTask(taskId);
			//check whether current login user is the task adder
			if (task != null){
				taskService.deleteTask(taskId);
			}
			return "redirect:/staff/manageTask";
		}	 
		return "redirect:/staff";
	}


}

