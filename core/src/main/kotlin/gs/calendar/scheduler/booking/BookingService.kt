package gs.calendar.scheduler.booking

import gs.calendar.scheduler.model.AgendaId
import gs.calendar.scheduler.model.BookingSlot

interface BookingService {

    fun list(agendaId: AgendaId): List<BookingSlot>

}
