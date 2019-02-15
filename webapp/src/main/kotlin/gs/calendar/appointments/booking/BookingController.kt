package gs.calendar.appointments.booking

import gs.calendar.appointments.model.AgendaId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("booking")
class BookingController(
        val service: BookingService) {

    @GetMapping
    fun list(@RequestParam("agenda") agendaId: AgendaId) =
            service.list(agendaId)

}
