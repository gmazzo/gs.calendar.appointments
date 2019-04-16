package gs.calendar.appointments.api.slots

import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.api.PARAM_AGENDA
import gs.calendar.appointments.api.PARAM_SLOT
import gs.calendar.appointments.api.Resource
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.SlotParams
import gs.calendar.appointments.model.User
import gs.calendar.appointments.slots.SlotsService
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.DELETE
import javax.ws.rs.DefaultValue
import javax.ws.rs.ForbiddenException
import javax.ws.rs.GET
import javax.ws.rs.NotAuthorizedException
import javax.ws.rs.PATCH
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context

@Singleton
@Path("slots/{$PARAM_AGENDA}")
class SlotsResource @Inject constructor(
    private val agendasService: AgendasService,
    private val slotsService: SlotsService
) : Resource() {

    @GET
    fun list(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @Context authUser: User?
    ): List<Slot> =
        slotsService.list(agendaId, true, authUser)

    @PUT
    @Path("{$PARAM_SLOT}")
    fun book(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @PathParam(PARAM_SLOT) slotId: SlotId,
        @Context authUser: User?,
        user: User
    ): Slot = authUser.assureSame(user) {
        slotsService.register(agendaId, slotId, user)
    }

    @DELETE
    @Path("{$PARAM_SLOT}")
    fun unbook(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @PathParam(PARAM_SLOT) slotId: SlotId,
        @Context authUser: User?,
        user: User
    ): Slot = authUser.assureSame(user) {
        slotsService.unregister(agendaId, slotId, user)
    }

    @PATCH
    @Path("{$PARAM_SLOT}")
    fun update(
        @PathParam(PARAM_AGENDA) agendaId: AgendaId,
        @PathParam(PARAM_SLOT) slotId: SlotId,
        @Context authUser: User?,
        @QueryParam("all") @DefaultValue("false") all: Boolean,
        params: SlotParams
    ): Slot = authUser.assureAdmin(agendaId) {
        slotsService.update(agendaId, slotId, all, params, authUser)
    }

    private fun <R> User?.assureSame(user: User, then: () -> R): R {
        if (!user.isSelf(this)) {
            throw ForbiddenException("${this} can't operate on behalf of $user")
        }
        return then()
    }

    private fun <R> User?.assureAdmin(agendaId: AgendaId, then: () -> R): R {
        if (this == null) {
            throw NotAuthorizedException("An admin user is required to operate agenda $agendaId")
        }
        if (!agendasService.isAdmin(agendaId, this)) {
            throw ForbiddenException("${this} isn't admin on agenda $agendaId")
        }
        return then()
    }

}
