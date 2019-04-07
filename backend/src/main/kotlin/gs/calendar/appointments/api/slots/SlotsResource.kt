package gs.calendar.appointments.api.slots

import gs.calendar.appointments.api.PARAM_AGENDA
import gs.calendar.appointments.api.PARAM_SLOT
import gs.calendar.appointments.events.EventsService
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Singleton
@Path("slots/{$PARAM_AGENDA}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SlotsResource @Inject constructor(
    private val service: EventsService
) {

    @GET
    fun list(@PathParam(PARAM_AGENDA) agendaId: AgendaId) =
        service.list(agendaId, true)

    @PUT
    @Path("{$PARAM_SLOT}")
    fun book(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @PathParam(PARAM_SLOT) slotId: SlotId,
        bookingUser: User
    ) =
        service.register(agendaId, slotId, bookingUser)

}
