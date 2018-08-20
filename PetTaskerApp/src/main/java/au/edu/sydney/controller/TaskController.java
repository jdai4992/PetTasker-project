package au.edu.sydney.controller;

import au.edu.sydney.model.Reviews;
import au.edu.sydney.model.Task;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.model.User;
import au.edu.sydney.service.ReviewsService;
import au.edu.sydney.service.TaskApplierService;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {
	
    @Autowired
    TaskService taskService; 
    
    @Autowired
    UserService userService;
    
    @Autowired
    TaskApplierService taskApplierService;
    
	@Autowired
	private ReviewsService reviewsService;
    
	public List<String> getDropDownList() {
		List<String> dropdownList = new ArrayList<String>();
		dropdownList.add("Active Tasks");
		dropdownList.add("Inactive Tasks");
		dropdownList.add("Newly Added");
		dropdownList.add("Older");
		dropdownList.add("Name (Ascending)");
		dropdownList.add("Name (Descending)");
		dropdownList.add("Price (Low to High)");
		dropdownList.add("Price (High to Low)");
		
		return dropdownList;
	}
	
    //get all tasks
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String displayTask(ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		if (taskService.getAllTasks().isEmpty()) {
	    			modelMap.addAttribute("noTasksInTheDatabase", "No currently posted tasks!");
	    		}
	    		//add the task object as an attribute of the model
	    		modelMap.addAttribute("task", new Task());
	    		modelMap.addAttribute("tasksList", getDropDownList());
	    		modelMap.addAttribute("tasks", taskService.getAllTasks());
	    		
	        return "task";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
    }

	//get tasks details by their id
	@RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
	public String getTaskById(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentUser") != null) {
			//make a review list to append all the found reviews
			List<Reviews> addedReviews = new ArrayList<Reviews>();
			//get all the reviews
			List<Reviews> getAllReviews = reviewsService.getAllReviews();
			for(Reviews review : getAllReviews) {
				if(review.getTaskId() == taskId) {
					addedReviews.add(review);
				}
			}
			//if addedReview list is empty i.e. if there aren't any reviews for this task
			if (addedReviews.isEmpty()) {
				modelMap.addAttribute("noReviews", "This task currently has no reviews!");
			} else {	
				modelMap.addAttribute("reviews", addedReviews);
			}
			
			//if the taskId exists in the database display the taskDetails page
			if (taskService.getTask(taskId) != null) {
				User user = (User) session.getAttribute("currentUser");				
				//get the task
				Task task = taskService.getTask(taskId);
				modelMap.addAttribute("user", user.getId());
				modelMap.addAttribute("task", task);
				//if the user hasn't applied then
				modelMap.addAttribute("adder", userService.getUser(taskService.getTask(taskId).getAdderId()));
				//get all taskAppliers from the database
				List<TaskAppliers> getAllTaskAppliers = taskApplierService.getAllTaskAppliers();
				for(TaskAppliers taskApplier : getAllTaskAppliers) {
					//check if the user has already applied for this task, if so return a error saying you have already applied
					if(taskApplier.getId().getUser().getId() == user.getId() && taskApplier.getId().getTask().getId() == task.getId()) {
						modelMap.addAttribute("errorMessage", "You already Applied for this task!");
						return "taskDetails";
					}
				}				
				return "taskDetails";
			} else {
				//if the taskId doesn't exists in the database display the invalid request page
				return "invalidRequest";
			}
		}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
	}
	
	@RequestMapping(value = "/tasks/{taskId}/edit", method = RequestMethod.GET)
    public String displayEditTaskForm(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		Task task = taskService.getTask(taskId);
			modelMap.addAttribute("task", task);	    		
	        return "editTask";
	    	}
	    	return "redirect:/userHomePage";
    }
	
    @RequestMapping(value = "/tasks/{taskId}/edit", method = RequestMethod.POST)
    public String updateTask(@PathVariable("taskId") int taskId, @Valid Task task, BindingResult result, ModelMap modelMap, HttpSession session) {
	    	if (result.hasErrors()){
	    		return "editTask";
	    	}
	    	task.setId(taskId);
	    	Task getTask = taskService.getTask(taskId);
	    	task.setTaskStatus(getTask.getTaskStatus());
	    	taskService.updateTask(task);
	    	return "redirect:/userHomePage";
    }  
    
    @RequestMapping(value = "/tasks/{taskId}/delete", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		User user = (User) session.getAttribute("currentUser");				
	    		Task task = taskService.getTask(taskId);
	    		//check whether current login user is the task adder
	    		if (task.getAdderId() == user.getId()){
	    			taskService.deleteTask(taskId);
	    		}	
	        return "redirect:/userHomePage";
	    	}	 
	    	return "redirect:/userHomePage";
    }
	
	@RequestMapping(value = "/tasks/{taskId}/choose", method = RequestMethod.GET)
    public String displayChoose(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		User user = (User) session.getAttribute("currentUser");				
				Task task = taskService.getTask(taskId);
				//check whether current login user is the task adder
				if (task.getAdderId() == user.getId()){
					
					List<TaskAppliers> list = new ArrayList<TaskAppliers>();;
					List<TaskAppliers> taskAppliers = taskApplierService.getAllTaskAppliers();
		    		for (TaskAppliers taskApplier : taskAppliers){
		    			if (taskApplier.getId().getTask().getId() == taskId){
		    				list.add(taskApplier);
		    			}
		    		}
		    		modelMap.addAttribute("taskAppliers", list);
		    		modelMap.addAttribute("task", task);
					return "chooseWorker";
				}
	        return "redirect:/userHomePage";
	    	}
	    	
	    	return "redirect:/userHomePage";
    }
    
	@RequestMapping(value = "/tasks/{taskId}/choose", method = RequestMethod.POST)
    public String chooseWorker(@PathVariable("taskId") int taskId, @RequestParam int workerId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		User user = (User) session.getAttribute("currentUser");				
				Task task = taskService.getTask(taskId);
				//check whether current login user is the task adder
				if (task.getAdderId() == user.getId()){
					task.setWorkerId(workerId);
					task.setTaskStatus("assignedTask");
					taskService.updateTask(task);
				}
	        return "redirect:/userHomePage";
	    	}
	    	
	    	return "redirect:/userHomePage";
    }
	
    @RequestMapping(value = "/tasks/{taskId}/workerCompleted", method = RequestMethod.GET)
    public String workerDone(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		User user = (User) session.getAttribute("currentUser");				
	    		Task task = taskService.getTask(taskId);
	    		//check whether current login user is the task worker
	    		if (task.getWorkerId() == user.getId()){
	    			task.setWorkerCompletedStatus(true);
	    			if ((task.getAdderCompletedStatus() != null) & (task.getWorkerCompletedStatus() != null)){
	    				task.setTaskStatus("completedTask");
	    			}
	    			taskService.updateTask(task);
	    		}
			
	        return "redirect:/userHomePage";
	    	}	 
	    	
	    	return "redirect:/userHomePage";
    }
    
    @RequestMapping(value = "/tasks/{taskId}/adderCompleted", method = RequestMethod.GET)
    public String adderDone(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		User user = (User) session.getAttribute("currentUser");				
	    		Task task = taskService.getTask(taskId);
	    		//check whether current login user is the task adder
	    		if (task.getAdderId() == user.getId()){
	    			task.setAdderCompletedStatus(true);
	    			if ((task.getAdderCompletedStatus() != null) & (task.getWorkerCompletedStatus() != null)){
	    				task.setTaskStatus("completedTask");
	    			}
	    			taskService.updateTask(task);
	    		}
			
	        return "redirect:/userHomePage";
	    	}	 
	    	
	    	return "redirect:/userHomePage";
    }
	
    
}
