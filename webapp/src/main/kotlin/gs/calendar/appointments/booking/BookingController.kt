package gs.calendar.appointments.booking

import gs.calendar.appointments.model.AgendaId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Singleton
@Path("booking")
@Produces(MediaType.APPLICATION_JSON)
class BookingController @Inject constructor(
        private val service: BookingService) {

    @GET
    fun list(@QueryParam("agenda") agendaId: AgendaId) =
            service.list(agendaId)

}
