package au.edu.sydney.controller;

import au.edu.sydney.service.UserService;
import au.edu.sydney.dao.Encryption;
import au.edu.sydney.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("currentUser")

@RequestMapping ("/")
public class LoginController {

    @Autowired
    UserService userService;
    
	public List<String> getCountryList()
	   {
		List<String> countryList = new ArrayList<String>();
		String[] locales = Locale.getISOCountries();        
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countryList.add(obj.getDisplayCountry());
		}
		return countryList;
	}

    @RequestMapping(method = RequestMethod.GET)
    public String displayLoginForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String validateLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        for(User user : users) {
            if(user.getEmail().equals(email) && user.getPassword().equals(Encryption.encrypt(password))) {
//                modelMap.addAttribute("resultMessage", "Welcome to your homepage " +  user.getFullName() + "! You are Successfully Logged in.");
                modelMap.addAttribute("currentUser", user);
                return "redirect:/userHomePage";
            }
        }
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("errorMessage", "Incorrect Username or Password! Please try again.");
        return "login";
    }
    
    //logout
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus status, RedirectAttributes redirectAttributes) {
        System.out.println("logout()");
        status.setComplete();
        redirectAttributes.addFlashAttribute("submittedMessage", "You have successfully logged out!");
        return "redirect:/";
    }

    //load Edit form
    @RequestMapping(value="/editProfile", method = RequestMethod.GET)
    public String displayEditProfileForm(ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		modelMap.addAttribute("user", new User());
	    		User user = (User) session.getAttribute("currentUser");
	    		modelMap.addAttribute("user", user);
	    		modelMap.addAttribute("countryList", getCountryList());
	    		return "editProfile";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
	}
    
    //validate the edit form
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String editProfile(ModelMap modelMap, @Valid User user, BindingResult result, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
		    	if (result.hasErrors()) {
		    		modelMap.addAttribute("countryList", getCountryList());
		    		return "editProfile";
		    	}
			if(!user.getPassword().equals(user.getConfirmPassword())) {
				modelMap.addAttribute("countryList", getCountryList());
				modelMap.addAttribute("errorMessage", "Password and Confirm Password does not match! Please try again.");
				return "editProfile";
			}
		    	User currentUser = (User) session.getAttribute("currentUser");
		    	//update details
		    	userService.updateUser(user, currentUser);
		    	User getUserDetails = userService.getUser(currentUser.getId());
		    	//update the session attribute to the new one
		    	modelMap.addAttribute("currentUser", getUserDetails);
		    	//after editing the user information redirect the user to the userHomePage
		    	return "redirect:/userHomePage";
	    	}
	    	return "redirect:/";
    }
}
