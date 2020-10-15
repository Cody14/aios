package com.smartcard.aios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.smartcard.aios.models.User;
import com.smartcard.aios.services.UserService;




@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public String getUsers(Model model) {	
		List<User> userList = userService.getUsers();
		model.addAttribute("users", userList);
		
		return "User";
	}
	
	
	

	@PostMapping("/users/addNew")
	public RedirectView addNew(User user, RedirectAttributes redir) {
		userService.save(user);	
		
		RedirectView  redirectView= new RedirectView("/login",true);
	    redir.addFlashAttribute("message1","Successfully Signed Up!");
	    redir.addFlashAttribute("message2","Now Sign In");
	    return redirectView;				
	}	
	
}
