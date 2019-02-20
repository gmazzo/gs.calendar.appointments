package gs.calendar.appointments.booking

import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.BookingSlot
import gs.calendar.appointments.model.BookingSlotId

interface BookingService {

    fun list(agendaId: AgendaId): List<BookingSlot>

    fun book(agendaId: AgendaId, slotId: BookingSlotId, email: String): BookingSlot

}
