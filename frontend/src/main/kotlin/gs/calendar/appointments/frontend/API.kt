package gs.calendar.appointments.frontend

import axios.Axios
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

object API {

    fun listAgendas() = Axios
        .get<String>("$API_ENDPOINT/agendas")
        .then { Json.parse(Agenda.serializer().list, it.data!!) }

    fun listSlots(agendaId: AgendaId) = Axios
        .get<String>("$API_ENDPOINT/slots/$agendaId")
        .then { Json.parse(Slot.serializer().list, it.data!!) }

    fun book(agendaId: AgendaId, slotId: SlotId, user: User) = Axios
        .put<String>("$API_ENDPOINT/slots/$agendaId/$slotId", user)
        .then { Json.parse(Slot.serializer(), it.data!!) }

}
