package gs.calendar.appointments.frontend

import axios.Axios
import axios.get
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot

object API {

    fun listAgendas() = Axios
        .get<Array<Agenda>>("$API_ENDPOINT/agendas")

    fun listSlots(agendaId: AgendaId) = Axios
        .get<Array<Slot>>("$API_ENDPOINT/slots") {
            this.agendaId = agendaId
        }

}
