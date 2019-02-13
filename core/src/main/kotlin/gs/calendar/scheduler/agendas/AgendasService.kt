package gs.calendar.scheduler.agendas

import gs.calendar.scheduler.model.Agenda

interface AgendasService {

    fun list(): List<Agenda>

}
