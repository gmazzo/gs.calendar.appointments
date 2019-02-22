package gs.calendar.appointments.api.booking

import gs.calendar.appointments.api.Required
import gs.calendar.appointments.events.EventsService
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.SlotId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Singleton
@Path("booking")
@Produces(MediaType.APPLICATION_JSON)
class BookingResource @Inject constructor(
    private val service: EventsService
) {

    @GET
    fun list(@Required @QueryParam("agenda") agendaId: AgendaId) =
        service.list(agendaId, true)

    @PUT
    fun book(
        @Required @QueryParam("agenda") agendaId: AgendaId,
        @Required @QueryParam("slot") slotId: SlotId,
        @Required @QueryParam("email") email: String
    ) =
        service.invite(agendaId, slotId, email)

}
