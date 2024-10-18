package com.Checkerly.BackEnd.services

import com.Checkerly.BackEnd.domain.Event
import com.Checkerly.BackEnd.dto.EventDTO
import com.Checkerly.BackEnd.repository.EventRepository
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date
import java.util.Optional

@Service
class EventService {
    @Autowired
    private val repo: EventRepository? = null

    fun findAll(): List<Event?>? {
        return repo.findAll()
    }

    fun findById(id: String?): Event? {
        val event: Optional<Event?>? = repo.findById(id)
        return event.orElseThrow({ ObjectNotFoundException("Objeto não encontrado") })
    }

    fun findByTheme(text: String?): List<Event?>? {
        return repo.searchAssuntoEvento(text)
    }

    fun fullSearch(text: String?, min: java.util.Date?, max: java.util.Date): List<Event?>? {
        var max: java.util.Date = max
        max = Date(max.getTime() + 24 * 60 * 60 * 1000)
        return repo.fullSearch(text, min, max)
    }

    fun insert(obj: Event?): Event? {
        val savedEvent: Event? = repo.save(obj)
        System.out.println("Event inserted: " + savedEvent)
        return savedEvent
    }

    fun delete(id: String?) {
        findById(id)
        repo.deleteById(id)
    }

    fun update(obj: Event): Event? {
        val newObj: Event? = findById(obj.getId())
        updateData(newObj, obj)
        return repo.save(newObj)
    }

    private fun updateData(newObj: Event, obj: Event) {
        newObj.setNomeEvento(obj.getNomeEvento())
        newObj.setAssuntoEvento(obj.getAssuntoEvento())
        newObj.setLatitude(obj.getLatitude())
        newObj.setLongitude(obj.getLongitude())
        newObj.setDataInicio(obj.getDataInicio())
        newObj.setDataFim(obj.getDataFim())
        newObj.setHoraEvento(obj.getHoraEvento())
    }

    fun fromDTO(objDto: EventDTO): Event? {
        return Event(
            objDto.getId(),
            objDto.getNomeEvento(),
            objDto.getAssuntoEvento(),
            objDto.getLatitude(),
            objDto.getLongitude(),
            objDto.getDataInicio(),
            objDto.getDataFim(),
            objDto.getHoraEvento()
        )
    }
}
