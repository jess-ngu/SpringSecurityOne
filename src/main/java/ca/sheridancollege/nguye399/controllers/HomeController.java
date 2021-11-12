package ca.sheridancollege.nguye399.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index.html";
	}

	@GetMapping("/user")
	public String userHome() {
		return "/user/index.html";
	}
	
	@GetMapping("/owner")
	public String ownerHome() {
		return "/owner/index.html";
	}
	
	@GetMapping("/manager")
	public String managerHome() {
		return "/manager/index.html";
	}
	
	@GetMapping("/employee")
	public String employeeHome() {
		return "/employee/index.html";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "loginpage.html";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/accessdenied.html";
	}
}
