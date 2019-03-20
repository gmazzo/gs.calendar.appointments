package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.axios
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot

object API {

    fun listAgendas() = axios.get<Array<Agenda>>("api/agendas")

    fun listSlots() = axios.get<Array<Slot>>("api/slots")

}
