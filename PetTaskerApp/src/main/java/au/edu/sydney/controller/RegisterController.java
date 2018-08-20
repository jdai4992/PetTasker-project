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
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	//getting the list of all the country (for displaying in dropdown)
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
	public String displayRegisterForm(ModelMap modelMap) {
		modelMap.addAttribute("countryList", getCountryList());
		modelMap.addAttribute("user", new User());
		
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String validateRegister(@Valid User user, BindingResult result, ModelMap modelMap) {
		if(result.hasErrors()) {
			modelMap.addAttribute("countryList", getCountryList());
			return "register";
		}

		//check to see if the entered email address already exists in the database or not
		List<User> users = userService.getAllUsers();
		for(User singleUser : users) {
			if(singleUser.getEmail().equals(user.getEmail())) {
				modelMap.addAttribute("countryList", getCountryList());
				modelMap.addAttribute("errorMessage", "Someone has already registered this email address, Please enter a different email address");
				return "register";
			}
		}

		//check to see if the user has entered the same email for 'email' and 'confirmEmail' field
		if(!user.getEmail().equals(user.getConfirmEmail())) {
			modelMap.addAttribute("countryList", getCountryList());
			modelMap.addAttribute("errorMessage", "Email and Confirm Email does not match! Please try again.");
			return "register";
		}
		
		//check to see if the user has entered password or not
		if(user.getPassword().equals(Encryption.encrypt("")) || user.getConfirmPassword().equals(Encryption.encrypt(""))) {
			modelMap.addAttribute("countryList", getCountryList());
			modelMap.addAttribute("errorMessage", "Password can't be empty! Please enter your password.");
			return "register";
		}

		//check to see if the user has entered the same password for 'password' and 'confirmPassword' field
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			modelMap.addAttribute("countryList", getCountryList());
			modelMap.addAttribute("errorMessage", "Password and Confirm Password does not match! Please try again.");
			return "register";
		}
		
		//check to see if the user has selected country or not
		if(user.getCountry().equals("NONE")) {
			modelMap.addAttribute("countryList", getCountryList());
			modelMap.addAttribute("errorMessage", "Select country can't be empty! Please select your country.");
			return "register";
		}

		//if no error has occurred, save the user details into the database
		user.setPassword(Encryption.encrypt(user.getPassword()));
		userService.addNewUser(user);
//		modelMap.addAttribute("errorMessage", "Congratulations " + user.getFullName() + "! You are Successfully Signed up.");
		return "redirect:/";
	}

}
