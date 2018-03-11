package com.taskManager;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.taskManager.dao.AppTaskRepository;
import com.taskManager.models.AppRole;
import com.taskManager.models.AppTask;
import com.taskManager.models.AppUser;
import com.taskManager.services.AccountService;

@SpringBootApplication
public class JwtTaskManagerApplication implements CommandLineRunner {

	@Autowired
	private AppTaskRepository appTaskRepository;
	
	@Autowired
	private AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtTaskManagerApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getPassEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... arg0) throws Exception {
		accountService.saveUser(new AppUser(null, "yassine", "1234", null));
		accountService.saveUser(new AppUser(null, "admin", "1234", null));
		accountService.saveUser(new AppUser(null, "user", "1234", null));
		
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		
		accountService.roleToUser("yassine", "ADMIN");
		accountService.roleToUser("yassine", "USER");
		accountService.roleToUser("admin", "ADMIN");
		accountService.roleToUser("admin", "USER");
		accountService.roleToUser("user", "USER");
		
		
		Stream.of("T1","T2","T3" ).forEach(task->{
			appTaskRepository.save(new AppTask(null, task));
		});
	}
}
