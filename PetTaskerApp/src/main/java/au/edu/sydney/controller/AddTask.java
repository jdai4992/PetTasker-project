package au.edu.sydney.controller;

import au.edu.sydney.service.TaskService;
import au.edu.sydney.model.Task;
import au.edu.sydney.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddTask {

    @Autowired
    private TaskService taskService;
    

    @RequestMapping(value="/newTask", method = RequestMethod.GET)
    public String displayNewTaskForm(ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		modelMap.addAttribute("task", new Task());
	    		return "newTask";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
	}
    
    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public String createNewTask(@Valid Task task, BindingResult result, ModelMap modelMap, HttpSession session) {
	    	if (result.hasErrors()){
	    		return "newTask";
	    	}
	    	User user = (User) session.getAttribute("currentUser");
	    	int userId = user.getId();
	    	task.setAdderId(userId);
	    	Date date = new Date();
	    	task.setStartDate(date);
	    	task.setTaskStatus("openTask");
	    	taskService.addNewTask(task);
	    	return "redirect:/newTask/" + task.getId() + "/imageUpload";
    }  
}


