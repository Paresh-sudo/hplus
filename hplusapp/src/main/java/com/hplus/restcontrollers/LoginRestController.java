package com.hplus.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hplus.exceptions.LoginFailureException;
import com.hplus.model.Login;
import com.hplus.model.User;
import com.hplus.repository.UserRepository;

@RestController
public class LoginRestController {
	
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping("/rest/loginuser")
	public ResponseEntity loginuser(@RequestBody Login login)throws LoginFailureException {
		User user = userRepository.searchByName(login.getUsername());
		 if(user==null){
	          // return ResponseEntity.status(404).build();
	            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
	        }

	        if(user.getUsername().equals(login.getUsername())&&
	            user.getPassword().equals(login.getPassword()))  {
	            return new ResponseEntity<>("Welcome, "+user.getUsername(),HttpStatus.OK);
	        }
	        else{
	            //throw new Exception
	            throw new LoginFailureException("Invalid username or password");
	        }
	}
	
}
