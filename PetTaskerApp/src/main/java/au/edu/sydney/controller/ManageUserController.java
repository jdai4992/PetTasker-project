package au.edu.sydney.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.sydney.model.Task;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.model.User;
import au.edu.sydney.service.TaskApplierService;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;

@Controller
//@RequestMapping ("/staff/manageUser")
public class ManageUserController {

	@Autowired
	TaskService taskService; 

	@Autowired
	UserService userService; 

	@Autowired
	TaskApplierService taskApplierService;

	@RequestMapping(value="/staff/manageUser", method=RequestMethod.GET)
	public String displayManageUser(ModelMap modelMap, HttpSession session){
		if (session.getAttribute("currentStaff") != null){
			modelMap.addAttribute("users", userService.getAllUsers());
			return "manageUser";

		}
		return "redirect:/staff";
	}


	//	@RequestMapping(value="/staff/manageUser/staffUserDetail", method=RequestMethod.GET)
	//	public String displayUserDetail(ModelMap modelMap, HttpSession session){
	//		if (session.getAttribute("currentStaff") != null){
	//
	//			return "staffUserDetail";
	//		}
	//		return "redirect:/staff";
	//	}

	@RequestMapping(value="/staff/manageUser/{userId}", method=RequestMethod.GET)
	public String getUserById(@PathVariable("userId") int userId, ModelMap modelMap, HttpSession session){
		if (session.getAttribute("currentStaff") != null){
			User user = userService.getUser(userId);
			//if the reportId exists in the database, display the reportDetails page
			if (user != null) {

				// get all tasks added and applied by user
				List<Task> tasks = taskService.getAllTasks();
				List<Task> addedTasks = new ArrayList<Task>();
				List<Task> appliedTasks = new ArrayList<Task>();

				for (Task task : tasks) {
					if (task.getAdderId() == userId){
						addedTasks.add(task);
					}
				}

				List<TaskAppliers> taskAppliers = taskApplierService.getAllTaskAppliers();
				for (TaskAppliers taskApplier : taskAppliers){
					if (taskApplier.getId().getUser().getId() == userId){
						appliedTasks.add(taskApplier.getId().getTask());
					}
				}

				//add the task object as an attribute of the model
				modelMap.addAttribute("user", user);
				modelMap.addAttribute("task", new Task());
				modelMap.addAttribute("addedTasks", addedTasks);
				modelMap.addAttribute("appliedTasks", appliedTasks);

				return "staffUserDetail";
			} else {
				//if the reportId doesn't exists in the database display the invalid request page
				return "redirect:/staff/manageUser";
			}
		}
		return "redirect:/staff";
	}

	@RequestMapping(value = "/staff/manageUser/{userId}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") int userId, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentStaff") != null) {			
			User user = userService.getUser(userId);

			//check whether current login user is the task adder
			if (user != null){
				userService.deleteUser(userId);
			}
			return "redirect:/staff/manageUser";
		}	 
		return "redirect:/staff";
	}
	//	  
	//	  @RequestMapping(value="/staff/staffUserDetail/{userId}", method=RequestMethod.GET)
	//		public String displayUserDetail(@PathVariable("userId") int userId, ModelMap modelMap, HttpSession session){
	//		  if (session.getAttribute("currentStaff") != null){
	//				
	//				//if the reportId exists in the database, display the reportDetails page
	//				if (user_id != 0) {
	//					modelMap.addAttribute("user", userService.getUser(userId));
	//					return "staffUserDetail";
	//				} else {
	//					return "redirect:/staff/staffReport/reportDetail";
	//				}
	//			}
	//			return "redirect:/staff";
	//		}

	//	  @RequestMapping("/staff/manageUser/staffUserDetail/{userId}")
	//		public String displayManageUserDetail(@PathVariable("userId") int userId, ModelMap modelMap, HttpSession session){
	//		  if (session.getAttribute("currentStaff") != null){
	//				
	//				//if the reportId exists in the database, display the reportDetails page
	//				if (userService.getUser(userId) != null) {
	//					modelMap.addAttribute("user", userService.getUser(userId));
	//					//return "manageUser";
	//					System.out.print("its in the if!!!!!!!!!!!!!");
	//					return "staffUserDetail";
	//				} else {
	//					System.out.print("its!!!!!!!!!!!!!");
	//					//if the reportId doesn't exists in the database display the invalid request page
	//					return "redirect:/reportDetail";
	//				}
	//			}
	//			return "redirect:/staff";
	//		}

}

