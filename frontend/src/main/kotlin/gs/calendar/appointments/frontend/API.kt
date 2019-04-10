package gs.calendar.appointments.frontend

import body
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import request
import kotlin.browser.window

object API {

    fun listAgendas() = window
        .fetch("$API_ENDPOINT/agendas")
        .body()
        .then { Json.parse(Agenda.serializer().list, it) }

    fun listSlots(agendaId: AgendaId) = window
        .fetch("$API_ENDPOINT/slots/$agendaId")
        .body()
        .then { Json.parse(Slot.serializer().list, it) }

    fun book(agendaId: AgendaId, slotId: SlotId, user: User) = window
        .fetch("$API_ENDPOINT/slots/$agendaId/$slotId", request("put", User.serializer(), user))
        .body()
        .then { Json.parse(Slot.serializer(), it) }

    fun unbook(agendaId: AgendaId, slotId: SlotId, user: User) = window
        .fetch("$API_ENDPOINT/slots/$agendaId/$slotId", request("delete", User.serializer(), user))
        .body()
        .then { Json.parse(Slot.serializer(), it) }

}
