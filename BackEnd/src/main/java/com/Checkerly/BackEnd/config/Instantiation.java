package com.Checkerly.BackEnd.config;

import java.text.SimpleDateFormat; 
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.repository.UserRepository;
@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown","1234", "maria@gmail.com", "6199999-9999");
		User alex = new User(null, "Alex Green","1234", "alex@gmail.com", "6199999-9999");
		User bob = new User(null, "Bob Grey","1234", "Bob Grey", "6199999-9999");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
    }
}
