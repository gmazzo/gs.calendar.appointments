package gs.calendar.appointments.agendas

import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.User

interface AgendasService {

    fun list(includeHidden: Boolean, user: User?): List<Agenda>

    fun enable(agendaId: AgendaId, enabled: Boolean, user: User?): Agenda

    fun isAdmin(agendaId: AgendaId, user: User): Boolean

}
