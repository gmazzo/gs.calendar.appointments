package gs.calendar.appointments.events

import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId

interface EventsService {

    fun list(agendaId: AgendaId, flatInstances: Boolean): List<Slot>

    fun invite(agendaId: AgendaId, slotId: SlotId, email: String): Slot

}
