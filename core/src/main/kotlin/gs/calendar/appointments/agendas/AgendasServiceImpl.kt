package gs.calendar.appointments.agendas

import com.google.api.services.calendar.Calendar
import gs.calendar.appointments.model.Agenda
import javax.inject.Inject

internal class AgendasServiceImpl @Inject constructor(
        api: Calendar) : AgendasService {

    private val listRequest = api.calendarList()
            .list()

    override fun list() = listRequest.execute().items
            .filter { it.accessRole in listOf("owner", "writer") }
            .map {
                Agenda(
                        id = it.id,
                        name = it.summary,
                        foregroundColor = it.foregroundColor,
                        backgroundColor = it.backgroundColor)
            }

}
