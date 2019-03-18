package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.axios.get
import gs.calendar.appointments.model.Slot

object api {

    fun listSlots() = get<List<Slot>?>("api/slots")

    data class Event(val id: String)

}
