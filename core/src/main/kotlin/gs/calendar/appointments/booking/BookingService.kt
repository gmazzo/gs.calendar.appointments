package gs.calendar.appointments.booking

import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.BookingSlot

interface BookingService {

    fun list(agendaId: AgendaId): List<BookingSlot>

}
