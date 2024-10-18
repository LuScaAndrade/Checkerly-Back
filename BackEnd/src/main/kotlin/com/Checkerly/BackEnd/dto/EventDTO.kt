package com.Checkerly.BackEnd.dto

import com.Checkerly.BackEnd.domain.Event
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import java.io.Serial
import java.io.Serializable
import java.time.LocalTime
import java.util.Date

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class EventDTO(obj: Event) : Serializable {
    @Id
    private val id: String?
    private val nomeEvento: String?
    private val assuntoEvento: String?
    private val latitude: String?
    private val longitude: String?
    private val dataInicio: Date?
    private val dataFim: Date?
    private val horaEvento: LocalTime?

    init {
        id = obj.getId()
        nomeEvento = obj.getNomeEvento()
        assuntoEvento = obj.getAssuntoEvento()
        latitude = obj.getLatitude()
        longitude = obj.getLongitude()
        dataInicio = obj.getDataInicio()
        dataFim = obj.getDataFim()
        horaEvento = obj.getHoraEvento()
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}
