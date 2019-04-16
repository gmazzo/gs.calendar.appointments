package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.BuildConfig.API_ENDPOINT
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinext.js.jsObject
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.browser.window
import kotlin.js.Promise

object API {

    fun listAgendas(authUser: User?) = window
        .fetch("$API_ENDPOINT/agendas", request(authUser))
        .body()
        .then { Json.parse(Agenda.serializer().list, it) }

    fun listSlots(agendaId: AgendaId, authUser: User?) = window
        .fetch("$API_ENDPOINT/slots/$agendaId", request(authUser))
        .body()
        .then { Json.parse(Slot.serializer().list, it) }

    fun book(agendaId: AgendaId, slotId: SlotId, user: User, authUser: User? = user) = window
        .fetch("$API_ENDPOINT/slots/$agendaId/$slotId", request(authUser, "put", user, User.serializer()))
        .body()
        .then { Json.parse(Slot.serializer(), it) }

    fun unbook(agendaId: AgendaId, slotId: SlotId, user: User, authUser: User? = user) = window
        .fetch("$API_ENDPOINT/slots/$agendaId/$slotId", request(authUser, "delete", user, User.serializer()))
        .body()
        .then { Json.parse(Slot.serializer(), it) }


    private fun request(authUser: User? = null): RequestInit =
        jsObject {
            this.headers = jsObject<dynamic> {
                this["Content-Type"] = "application/json"

                authUser?.let(User::tokenId)?.let { this[HEADER_AUTH_TOKEN_ID] = it }
            }
        }

    private fun <T : Any> request(
        authUser: User? = null,
        method: String,
        body: T,
        serializer: SerializationStrategy<T>
    ) =
        request(authUser).apply {
            this.method = method
            this.body = Json.stringify(serializer, body)
        }

    private fun <R : Response> Promise<R>.body() = then {
        if (it.ok) it.text() else throw IllegalStateException("${it.url} failed: ${it.status} ${it.statusText}")
    }

}
