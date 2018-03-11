package com.taskManager.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskManager.models.AppUser;

@Service
public class UserdetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AccountService accountServive;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user = accountServive.findUserByUserName(username);
		if (user==null) {
			throw new UsernameNotFoundException(username);
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		});
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
