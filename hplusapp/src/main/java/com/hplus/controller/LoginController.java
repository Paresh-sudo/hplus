package com.hplus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hplus.exceptions.ApplicationException;
import com.hplus.model.Login;
import com.hplus.model.User;
import com.hplus.repository.UserRepository;

@Controller
@SessionAttributes("login")
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") Login login, BindingResult result, Model model) {
		User user = userRepository.searchByName(login.getUsername());
		if(result.hasErrors()) {
			return "login";
		}
		else if(user==null){
//            throw new ApplicationException("User not found");
			model.addAttribute("error","User Not Found, please first register");
			return "login";
        }
		else {
			if(!(user.getPassword().equals(login.getPassword()))) {
				model.addAttribute("error", "please enter correct password");
				return "login";
			}
		}
		return "forward:/userprofile";
	}
	
	 @ExceptionHandler(ApplicationException.class)
	    public String handleException(){
	        System.out.println("in exception handler of Login Controller");
	        return "error";
	    }
}
