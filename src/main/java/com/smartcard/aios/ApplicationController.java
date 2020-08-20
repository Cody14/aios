package com.smartcard.aios;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

	@GetMapping("/")
	public String goHome() {
		return "citizen";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/linkNid")
	public String linkNid() {
		return "linknid";
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
