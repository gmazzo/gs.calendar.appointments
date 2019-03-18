package gs.calendar.appointments.events

import gs.calendar.appointments.model.Slot

interface EventsService {

    fun list(agendaId: String, flatInstances: Boolean): List<Slot>

    fun invite(agendaId: String, slotId: String, email: String): Slot

}
