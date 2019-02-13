package gs.calendar.scheduler.booking

import com.google.api.services.calendar.Calendar
import gs.calendar.scheduler.model.AgendaId
import gs.calendar.scheduler.model.BookingSlot
import javax.inject.Inject

internal class BookingServiceImpl @Inject constructor(
        api: Calendar) : BookingService {

    private val eventsApi = api.events()

    override fun list(agendaId: AgendaId) = eventsApi.list(agendaId)
            .execute()
            .let { it.items }
            .map { BookingSlot(id = it.id, description = it.description) }

}
