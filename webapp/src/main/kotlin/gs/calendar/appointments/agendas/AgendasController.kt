package gs.calendar.appointments.agendas

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
