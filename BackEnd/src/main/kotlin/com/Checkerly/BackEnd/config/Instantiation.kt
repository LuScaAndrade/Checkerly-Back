package com.Checkerly.BackEnd.config

import com.Checkerly.BackEnd.domain.Event
import com.Checkerly.BackEnd.domain.Organizer
import com.Checkerly.BackEnd.domain.User
import com.Checkerly.BackEnd.repository.EventRepository
import com.Checkerly.BackEnd.repository.OrganizerRepository
import com.Checkerly.BackEnd.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Arrays

@Configuration
class Instantiation : CommandLineRunner {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val eventRepository: EventRepository? = null

    @Autowired
    private val organizerRepository: OrganizerRepository? = null

    @Override
    @Throws(Exception::class)
    fun run(vararg args: String?) {
        val sdf: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val shf: DateTimeFormatter? = DateTimeFormatter.ofPattern("HH:mm")

        val maria: User?
        val alex: User?
        val bob: User?

        val eventHour: LocalTime? = LocalTime.parse("13:50", shf)
        val event1: Event?

        val lucas: Organizer?

        if (userRepository.count() === 0) {
            maria = User(null, "Maria Brown", "1234", "maria@gmail.com", "6199999-9999")
            alex = User(null, "Alex Green", "1234", "alex@gmail.com", "6199999-9999")
            bob = User(null, "Bob Grey", "1234", "Bob Grey", "6199999-9999")
            userRepository.saveAll(Arrays.asList(maria, alex, bob))
        } else {
            maria = userRepository.findAll().getFirst()
            alex = userRepository.findAll().getFirst()
            bob = userRepository.findAll().getFirst()
        }

        if (eventRepository.count() === 0) {
            event1 = Event(
                null, "Evento Qualquer", "Tecnologia", "-15.90", "48,07-", sdf.parse("24/09/2024"),
                sdf.parse("25/09/2024"), eventHour
            )
            eventRepository.save(event1)
        } else {
            event1 = eventRepository.findAll().getFirst()
        }

        if (organizerRepository.count() === 0) {
            lucas = Organizer(null, "Lucas", "1234", "Lucas@xyz.com", "6199999-9999")
            lucas.getEvents().add(event1)
            organizerRepository.save(lucas)
        } else {
            lucas = organizerRepository.findAll().getFirst()
        }
    }
}
