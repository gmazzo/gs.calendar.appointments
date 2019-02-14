package gs.calendar.appointments.api

import gs.calendar.appointments.agendas.AgendasService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("agendas")
class AgendasController(
        val service: AgendasService) {

    @GetMapping
    fun list() = service.list()

}
