package com.carexpenses.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String showPage(Model theModel, User theUser) {

		theModel.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String newUser(@Valid User theUser,BindingResult bindingResult, RedirectAttributes model ) {

		if(bindingResult.hasErrors()) {
			return "register";
		}

		if(userService.checkIfUserExists(theUser)) {

			model.addFlashAttribute("errorMsg","User already registered.");
			return "redirect:/register";

		}

		model.addFlashAttribute("successMessage", "You have been successfully registered! You can now log in.");

		userService.registerUser(theUser);
		return "redirect:/login";
	}



}
