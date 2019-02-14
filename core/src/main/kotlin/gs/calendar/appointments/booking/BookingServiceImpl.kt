package gs.calendar.appointments.booking

import com.google.api.services.calendar.Calendar
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.BookingSlot
import javax.inject.Inject

internal class BookingServiceImpl @Inject constructor(
        api: Calendar) : BookingService {

    private val eventsApi = api.events()

    override fun list(agendaId: AgendaId) = eventsApi.list(agendaId)
            .execute()
            .let { it.items }
            .map { BookingSlot(id = it.id, description = it.description) }

}
