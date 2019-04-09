package gs.calendar.appointments.frontend

import body
import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinext.js.jsObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
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
        .fetch("$API_ENDPOINT/slots/$agendaId/$slotId", jsObject {
            method = "put"
            headers = jsObject<dynamic> {
                this["Content-Type"] = "application/json"
            }
            body = Json.stringify(User.serializer(), user)
        })
        .body()
        .then { Json.parse(Slot.serializer(), it) }

}
