package gs.calendar.appointments.events

import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.SlotParams
import gs.calendar.appointments.model.User

interface EventsService {

    fun list(agendaId: AgendaId, flatInstances: Boolean, user: User?): List<Slot>

    fun register(agendaId: AgendaId, slotId: SlotId, user: User): Slot

    fun unregister(agendaId: AgendaId, slotId: SlotId, user: User): Slot

    fun update(agendaId: AgendaId, slotId: SlotId, allInstances: Boolean, params: SlotParams): Slot

}
