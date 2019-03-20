package gs.calendar.appointments.api.booking

import gs.calendar.appointments.api.PARAM_AGENDA
import gs.calendar.appointments.api.PARAM_EMAIL
import gs.calendar.appointments.api.PARAM_SLOT
import gs.calendar.appointments.api.Required
import gs.calendar.appointments.events.EventsService
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.SlotId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Singleton
@Path("booking/{$PARAM_AGENDA}")
@Produces(MediaType.APPLICATION_JSON)
class BookingResource @Inject constructor(
    private val service: EventsService
) {

    @GET
    fun list(@Required @PathParam(PARAM_AGENDA) agendaId: AgendaId) =
        service.list(agendaId, true)

    @PUT
    fun book(
        @Required @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @Required @QueryParam(PARAM_SLOT) slotId: SlotId,
        @Required @QueryParam(PARAM_EMAIL) email: String
    ) =
        service.invite(agendaId, slotId, email)

}
