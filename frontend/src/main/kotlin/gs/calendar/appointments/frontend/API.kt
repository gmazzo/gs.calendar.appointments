package gs.calendar.appointments.frontend

import axios.Axios
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import kotlinext.js.js
import sanitized

object API {

    fun listAgendas() = Axios
        .get<Array<Agenda>>("$API_ENDPOINT/agendas")

    fun listSlots(agendaId: AgendaId) = Axios
        .get<Array<Slot>>("$API_ENDPOINT/slots/$agendaId")
        .then { it.data?.forEach { s -> s.sanitize() }; it }

    fun book(agendaId: AgendaId, slotId: SlotId, email: String) = Axios
        .put<Slot>("$API_ENDPOINT/slots/$agendaId/$slotId", js {
            this.email = email
        })
        .then { it.data?.sanitize(); it }

    private fun Slot.sanitize() {
        asDynamic().startTime = startTime?.sanitized
        asDynamic().endTime = endTime?.sanitized
    }

}
