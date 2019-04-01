package gs.calendar.appointments.api.agendas

import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.api.PARAM_AGENDA
import gs.calendar.appointments.api.PARAM_ENABLED
import gs.calendar.appointments.model.AgendaId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Singleton
@Path("agendas")
@Produces(MediaType.APPLICATION_JSON)
class AgendasResource @Inject constructor(
    private val service: AgendasService
) {

    @GET
    fun list(@QueryParam("all") all: String?) = service.list(all != null)

    @PATCH
    @Path("{$PARAM_AGENDA}")
    fun enable(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @QueryParam(PARAM_ENABLED) enabled: Boolean
    ) = service.enable(agendaId, enabled)

}
