package au.edu.sydney.controller;

import au.edu.sydney.model.Task;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.model.TaskWorkerId;
import au.edu.sydney.model.User;
import au.edu.sydney.service.TaskApplierService;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplyTaskController {
	
    @Autowired
    TaskService taskService;
    
    @Autowired
    TaskApplierService taskApplierService;
    
    @Autowired
    UserService userService;
    
	
    //display apply for task page
    @RequestMapping(value = "/tasks/{taskId}/apply", method = RequestMethod.GET)
	public String getTaskById(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
    		if (session.getAttribute("currentUser") != null) {
			User user = (User) session.getAttribute("currentUser");
			modelMap.addAttribute("user", user.getId());
	    		modelMap.addAttribute("taskAppliers", new TaskAppliers());
	    		modelMap.addAttribute("adder", userService.getUser(taskService.getTask(taskId).getAdderId()));
			modelMap.addAttribute("task", taskService.getTask(taskId));
			//get the task
			Task task = taskService.getTask(taskId);
			//get all taskAppliers from the database
			List<TaskAppliers> getAllTaskAppliers = taskApplierService.getAllTaskAppliers();
			for(TaskAppliers taskApplier : getAllTaskAppliers) {
				//check if the user has already applied for this task, if so return a error saying you have already applied
				if(taskApplier.getId().getUser().getId() == user.getId() && taskApplier.getId().getTask().getId() == task.getId()) {
					modelMap.addAttribute("errorMessage", "You already Applied for this task!");
					return "applyForTask";
				}
			}
			
			return "applyForTask";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
	}
	
	//check if the filled in form is correct or not
	@RequestMapping(value = "/tasks/{taskId}/apply", method = RequestMethod.POST)
	public String validateTask(@PathVariable("taskId") int taskId, @Valid TaskAppliers taskAppliers, BindingResult result, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentUser") != null) {
			modelMap.addAttribute("adder", userService.getUser(taskService.getTask(taskId).getAdderId()));
			modelMap.addAttribute("task", taskService.getTask(taskId));
			if (result.hasErrors()){
		    		return "applyForTask";
		    	}
		    	modelMap.addAttribute("taskAppliers", new TaskAppliers());
		    	//get the current user
			User user = (User) session.getAttribute("currentUser");
			//get the task
			Task task = taskService.getTask(taskId);
			//get all taskAppliers from the database
			List<TaskAppliers> getAllTaskAppliers = taskApplierService.getAllTaskAppliers();
			for(TaskAppliers taskApplier : getAllTaskAppliers) {
				//check if the user has already applied for this task, if so return a error saying you have already applied
				if(taskApplier.getId().getUser().getId() == user.getId() && taskApplier.getId().getTask().getId() == task.getId()) {
					modelMap.addAttribute("errorMessage", "You already Applied for this task!");
					return "applyForTask";
				}
			}
//			redirectAttributes.addFlashAttribute("submittedMessage", "You have successfully applied for a task");
			//set the taskApplier id as a combination of user and task
			taskAppliers.setId(new TaskWorkerId(user,task));
			taskApplierService.addNewTaskApplier(taskAppliers);
			//after apply for the task redirect the user to the userHomePage
		    	return "redirect:/userHomePage";
		}
    		//redirect to login page if the user isn't logged in
    		return "redirect:/";
	}
}