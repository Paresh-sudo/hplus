package com.hplus.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hplus.model.User;
import com.hplus.repository.UserRepository;

@Controller
public class RegistrationController {
	
	@Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
       binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@PostMapping("/registeruser")
	public String registerUser(@Valid @ModelAttribute("newuser") User user, BindingResult result, Model model) {
		System.out.println("registeruser controller");
		
		if(result.hasErrors()) {
			return "register";
		}
		userRepository.save(user);
		model.addAttribute("dataSaved", "User Registered Successfully");
//		System.out.println(user.getUsername());
		return "login";
	}
}
