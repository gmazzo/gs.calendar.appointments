package gs.calendar.appointments.frontend

import axios.Axios
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import kotlinext.js.js

object API {

    fun listAgendas() = Axios
        .get<Array<Agenda>>("$API_ENDPOINT/agendas")

    fun listSlots(agendaId: AgendaId) = Axios
        .get<Array<Slot>>("$API_ENDPOINT/slots/$agendaId")

    fun book(agendaId: AgendaId, slotId: SlotId, email: String) = Axios
        .put<Slot>("$API_ENDPOINT/slots/$agendaId/$slotId", js {
            this.email = email
        })

}
