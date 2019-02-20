package gs.calendar.appointments.api.booking

import gs.calendar.appointments.api.Required
import gs.calendar.appointments.booking.BookingService
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.BookingSlotId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Singleton
@Path("booking")
@Produces(MediaType.APPLICATION_JSON)
class BookingController @Inject constructor(
    private val service: BookingService
) {

    @GET
    fun list(@Required @QueryParam("agenda") agendaId: AgendaId) =
        service.list(agendaId)

    @PUT
    fun book(
        @Required @QueryParam("agenda") agendaId: AgendaId,
        @Required @QueryParam("slot") slotId: BookingSlotId,
        @Required @QueryParam("email") email: String
    ) =
        service.book(agendaId, slotId, email)

}
