package gs.calendar.appointments.agendas

import gs.calendar.appointments.model.Agenda

interface AgendasService {

    fun list(): List<Agenda>

}
