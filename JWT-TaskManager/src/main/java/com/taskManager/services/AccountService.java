package com.taskManager.services;

import com.taskManager.models.AppRole;
import com.taskManager.models.AppUser;

public interface AccountService {
	AppUser findUserByUserName(String username);
	AppUser saveUser(AppUser user);
	AppRole saveRole(AppRole role);
	void roleToUser(String username, String role);
}
