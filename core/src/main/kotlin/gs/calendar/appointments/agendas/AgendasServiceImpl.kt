package gs.calendar.appointments.agendas

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.CalendarListEntry
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.User
import javax.inject.Inject

internal class AgendasServiceImpl @Inject constructor(
    private val api: Calendar
) : AgendasService {

    override fun list(includeHidden: Boolean) = api
        .calendarList()
        .list()
        .setMinAccessRole("writer")
        .setShowHidden(includeHidden)
        .execute()
        .items
        .map { it.toAgenda() }

    override fun enable(agendaId: AgendaId, enabled: Boolean) = api
        .calendarList()
        .patch(agendaId, CalendarListEntry().setHidden(enabled))
        .execute()
        .let { it.toAgenda() }

    override fun isAdmin(agendaId: AgendaId, user: User) = api
        .acl()
        .list(agendaId)
        .execute()
        .items
        .find { it.scope.type == "user" && it.scope.value == user.email } != null

    private fun CalendarListEntry.toAgenda() = Agenda(
        id = id,
        name = summary,
        description = description,
        foregroundColor = foregroundColor,
        backgroundColor = backgroundColor,
        available = hidden != true
    )

}
