package gs.calendar.appointments.agendas

import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId

interface AgendasService {

    fun list(includeHidden: Boolean): List<Agenda>

    fun enable(agendaId: AgendaId, enabled: Boolean): Agenda

}
