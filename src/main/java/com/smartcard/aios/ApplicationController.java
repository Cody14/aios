package com.smartcard.aios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartcard.aios.models.User;
import com.smartcard.aios.services.UserService;

import javassist.compiler.ast.Keyword;

@Controller
public class ApplicationController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/")
	public String goHome() {
	  return "redirect:/citizens";
	}
	
	@GetMapping("/ex")
	public String goH() {
	  return "ex";
	}
	
	@GetMapping("/login")
	public String login() {
	return "login";	
	}
	
	@GetMapping("/adminLogin")
	public String adminLogin(String keyword) {		
		
		if(keyword!=null) {
			
		User user =	userService.findByUsername(keyword);
		
		if(user.getFullname().contentEquals("Cody")) {
			System.out.println("OKEEEEEEEEEEEEY "+user.getFullname());
			return"redirect:/admins";
		}else {
			
			return"adminLogin";
		}
		}
		else {
			
			return"adminLogin";
		}
			
			
	
			
	}
	
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/linkNid")
	public String linkNid() {
		return "linknid";
	}
	
	@GetMapping("/linkDl")
	public String linkDl() {
		return "linkdl";
	}
	
	@GetMapping("/linkHi")
	public String linkHi() {
		return "linkhi";
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
	

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	
	
}
