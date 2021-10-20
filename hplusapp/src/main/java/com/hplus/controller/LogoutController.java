package com.hplus.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	
	@GetMapping("/goToLogout")
	public String logout(HttpSession session) {
		System.out.println("ending user session");
		session.invalidate();
		return "login";
	}
}
