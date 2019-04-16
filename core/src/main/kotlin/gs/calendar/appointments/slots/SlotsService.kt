package gs.calendar.appointments.slots

import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.SlotParams
import gs.calendar.appointments.model.User

interface SlotsService {

    fun list(agendaId: AgendaId, flatInstances: Boolean, user: User? = null): List<Slot>

    fun register(agendaId: AgendaId, slotId: SlotId, user: User): Slot

    fun unregister(agendaId: AgendaId, slotId: SlotId, user: User): Slot

    fun update(agendaId: AgendaId, slotId: SlotId, allInstances: Boolean, params: SlotParams, user: User? = null): Slot

}
