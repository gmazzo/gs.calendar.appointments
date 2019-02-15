package gs.calendar.appointments.agendas

import com.google.api.services.calendar.Calendar
import gs.calendar.appointments.model.Agenda
import javax.inject.Inject

internal class AgendasServiceImpl @Inject constructor(
        api: Calendar) : AgendasService {

    private val listRequest = api.calendarList()
            .list()

    override fun list() = listRequest
            .setMinAccessRole("writer")
            .execute()
            .items
            .map {
                Agenda(
                        id = it.id,
                        name = it.summary,
                        description = it.description,
                        foregroundColor = it.foregroundColor,
                        backgroundColor = it.backgroundColor)
            }

}
