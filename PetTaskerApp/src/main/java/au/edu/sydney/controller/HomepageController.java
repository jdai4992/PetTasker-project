package au.edu.sydney.controller;

import au.edu.sydney.model.Task;
import au.edu.sydney.model.User;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.TaskApplierService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomepageController {
	
    @Autowired
    TaskService taskService; 
    @Autowired
    TaskApplierService taskApplierService;


	@RequestMapping(value = "/userHomePage", method = RequestMethod.GET)
    public String displayTask(ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		
	    		//get userId of current login user
	    		User user = (User) session.getAttribute("currentUser");
		    	int userId = user.getId();
		    	
	    		//get all tasks added and applied by current login user
	    		List<Task> tasks = taskService.getAllTasks();
	    		List<Task> addedTasks = new ArrayList<Task>();
	    		List<Task> appliedTasks = new ArrayList<Task>();
	    		for (Task task : tasks){
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
	    		modelMap.addAttribute("task", new Task());
	    		modelMap.addAttribute("addedTasks", addedTasks);
	    		modelMap.addAttribute("appliedTasks", appliedTasks);

	        return "userHomePage";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
    }

}