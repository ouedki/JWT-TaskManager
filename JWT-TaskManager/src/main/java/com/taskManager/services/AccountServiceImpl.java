package com.taskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskManager.dao.AppRoleRepository;
import com.taskManager.dao.AppUserRepository;
import com.taskManager.models.AppRole;
import com.taskManager.models.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public AppUser findUserByUserName(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public AppUser saveUser(AppUser user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return appUserRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return appRoleRepository.save(role);
	}

	@Override
	public void roleToUser(String username, String role) {
		AppUser user = appUserRepository.findByUsername(username);
		AppRole userRole = appRoleRepository.findByRole(role);
		user.getRoles().add(userRole);
	}
}
