package au.edu.sydney.controller;

import au.edu.sydney.service.ReviewsService;
import au.edu.sydney.service.TaskService;
import au.edu.sydney.service.UserService;
import au.edu.sydney.dao.Encryption;
import au.edu.sydney.model.User;
import au.edu.sydney.model.Reviews;
import au.edu.sydney.model.Task;
import au.edu.sydney.model.TaskAppliers;
import au.edu.sydney.model.TaskWorkerId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ReviewsController{
	
	@Autowired
	TaskService taskService;

	@Autowired
	private ReviewsService reviewsService;
	
	@Autowired
    UserService userService;

	@RequestMapping(value="/tasks/{taskId}/reviews", method = RequestMethod.GET)
	public String displayReviewForm(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentUser") != null) {
			
			Task task = taskService.getTask(taskId);
			User user = (User) session.getAttribute("currentUser");
			
			if(user.getId() != task.getAdderId() && user.getId() != task.getWorkerId()){
				return "redirect:/userHomePage";
			}
//			modelMap.addAttribute("user", user.getId());
			modelMap.addAttribute("reviews", new Reviews());
			return "reviews";
		}
		//redirect to login page if the user isn't logged in
		return "redirect:/";
	}

	@RequestMapping(value = "/tasks/{taskId}/reviews", method = RequestMethod.POST)
	public String validateAndCreateReview(@PathVariable("taskId") int taskId, @Valid Reviews review, BindingResult result, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("currentUser") != null) {
			if (result.hasErrors()){
				// modelMap.addAttribute("task", taskService.getTask(taskId));
				return "reviews";
			}

			modelMap.addAttribute("reviews", new Reviews());
			//get the current user
			User user = (User) session.getAttribute("currentUser");
			int userId = user.getId();
			Task task = taskService.getTask(taskId);
			
			if(userId == task.getAdderId()){
				review.setReviewsRecieverId(task.getWorkerId());
				task.setTaskAdderReview(true);
				taskService.updateTask(task);
			}else{
				review.setReviewsRecieverId(task.getAdderId());
				task.setTaskWorkerReview(true);
				taskService.updateTask(task);
			}
			review.setReviewsGiverId(userId);
			review.setTaskId(taskId);
			reviewsService.addNewReviews(review);
			
			//after apply for the task redirect the user to the userHomePage
			return "redirect:/userHomePage";
		}
		//redirect to login page if the user isn't logged in
		return "redirect:/";
	}
}  