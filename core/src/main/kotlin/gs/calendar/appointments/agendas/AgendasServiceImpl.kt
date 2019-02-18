package gs.calendar.appointments.agendas

import com.google.api.services.calendar.Calendar
import gs.calendar.appointments.model.Agenda
import javax.inject.Inject
import javax.inject.Provider

internal class AgendasServiceImpl @Inject constructor(
        private val api: Provider<Calendar>) : AgendasService {

    override fun list() = api.get()
            .calendarList()
            .list()
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
