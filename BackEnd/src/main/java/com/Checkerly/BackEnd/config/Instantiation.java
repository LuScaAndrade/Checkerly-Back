package com.Checkerly.BackEnd.config;

import java.text.SimpleDateFormat; 
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.repository.EventRepository;
import com.Checkerly.BackEnd.repository.UserRepository;
@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
		eventRepository.deleteAll();

		User maria = new User(null, "Maria Brown","1234", "maria@gmail.com", "6199999-9999");
		User alex = new User(null, "Alex Green","1234", "alex@gmail.com", "6199999-9999");
		User bob = new User(null, "Bob Grey","1234", "Bob Grey", "6199999-9999");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Event event1  = new Event(null, "Evento Qualquer", "Tecnologia", sdf.parse("24/09/2024"), sdf.parse("25/09/2024"), sdh.parse("13:50:20"));
		eventRepository.saveAll(Arrays.asList(event1));
    }
}
