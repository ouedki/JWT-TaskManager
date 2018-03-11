package com.taskManager.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.models.AppUser;
import com.taskManager.services.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser saveUser(@RequestBody RegisterForm user) {
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new RuntimeException("Please enter a valid password and confirm password");
		}
		
		if (accountService.findUserByUserName(user.getUsername())!=null) {
			throw new RuntimeException("Username already exists");
		}
		
		AppUser appUser = new AppUser();
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		accountService.saveUser(appUser);
		accountService.roleToUser(user.getUsername(), "USER");
		return appUser;
	}

}
