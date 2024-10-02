package com.Checkerly.BackEnd.config;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.repository.EventRepository;
import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private OrganizerRepository organizerRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter shf = DateTimeFormatter.ofPattern("HH:mm");

		User maria;
		User alex;
		User bob;

		LocalTime eventHour = LocalTime.parse("13:50", shf);
		Event event1;

		Organizer lucas;

		if (userRepository.count() == 0) {
			maria = new User(null, "Maria Brown", "1234", "maria@gmail.com", "6199999-9999");
			alex = new User(null, "Alex Green", "1234", "alex@gmail.com", "6199999-9999");
			bob = new User(null, "Bob Grey", "1234", "Bob Grey", "6199999-9999");
			userRepository.saveAll(Arrays.asList(maria, alex, bob));
		} else {
			maria = userRepository.findAll().get(0);
			alex = userRepository.findAll().get(0);
			bob = userRepository.findAll().get(0);
		}

		if (eventRepository.count() == 0) {
			event1 = new Event(null, "Evento Qualquer", "Tecnologia", "Uniceplac", sdf.parse("24/09/2024"),
					sdf.parse("25/09/2024"), eventHour);
			eventRepository.save(event1);
		} else {
			event1 = eventRepository.findAll().get(0);
		}

		if (organizerRepository.count() == 0) {
			lucas = new Organizer(null, "Lucas", "1234", "Lucas@xyz.com", "6199999-9999");
			lucas.getEvents().add(event1);
			organizerRepository.save(lucas);
		} else {
			lucas = organizerRepository.findAll().get(0);
		}
	}
}
