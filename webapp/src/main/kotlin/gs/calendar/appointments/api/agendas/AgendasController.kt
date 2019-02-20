package gs.calendar.appointments.api.agendas

import gs.calendar.appointments.agendas.AgendasService
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Singleton
@Path("agendas")
@Produces(MediaType.APPLICATION_JSON)
class AgendasController @Inject constructor(
    private val service: AgendasService
) {

    @GET
    fun list() = service.list()

}
