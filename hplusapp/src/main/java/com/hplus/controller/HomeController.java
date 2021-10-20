package com.hplus.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.hplus.model.User;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("home controller");
		return "index";
	}
	
	@GetMapping("/goToSearch")
	public String search() {
		System.out.println("search controller");
		return "search";
	}
	
	@GetMapping("/goToLogin")
    public String goToLogin(){
        System.out.println("going to login page");
        return "login";
    }

    @GetMapping("/goToRegistration")
    public String goToRegistration(){
        System.out.println("going to register page");
        return "register";
    }
    
    @GetMapping("/error")
    public String goToerrorpage(){
        return "error";
    }
    
//    @ModelAttribute("newuser")
//    public User getDefaultUser(){
//        return new User();
//    }
//
//    @ModelAttribute("genderItems")
//    public List<String> getGenderItems(){
//        return Arrays.asList(new String[]{"Male", "Female", "Other"});
//    }
	
}
