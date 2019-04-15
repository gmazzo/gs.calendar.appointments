package gs.calendar.appointments.api.agendas

import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.api.PARAM_AGENDA
import gs.calendar.appointments.api.Resource
import gs.calendar.appointments.model.AgendaId
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.PATCH
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@Singleton
@Path("agendas")
class AgendasResource @Inject constructor(
    private val service: AgendasService
) : Resource() {

    @GET
    fun list(
        @QueryParam("all") @DefaultValue("false") all: Boolean
    ) = service.list(all)

    @PATCH
    @Path("{$PARAM_AGENDA}")
    fun enable(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @QueryParam("enabled") enabled: Boolean
    ) = service.enable(agendaId, enabled)

}
