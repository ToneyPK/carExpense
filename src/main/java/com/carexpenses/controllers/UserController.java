package com.carexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carexpenses.entity.User;

import com.carexpenses.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;


	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String showPage(Model theModel) {
		
		theModel.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String newUser(User theUser, Model model) {
		
		if(userService.checkIfUserExists(theUser)) {
			
			model.addAttribute("errorMsg","User already registered.");
			return "/register";
			
			
		}
		
		userService.registerUser(theUser);
		return "redirect:/";
	}
	
	
	
}
