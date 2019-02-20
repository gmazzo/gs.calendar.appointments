package gs.calendar.appointments.booking

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.BookingSlot
import gs.calendar.appointments.model.BookingSlotId
import javax.inject.Inject
import javax.inject.Provider

internal class BookingServiceImpl @Inject constructor(
        private val api: Provider<Calendar>) : BookingService {

    override fun list(agendaId: AgendaId) = api.get()
            .events()
            .list(agendaId)
            .execute()
            .items
            .map(::toSlot)

    override fun book(agendaId: AgendaId, slotId: BookingSlotId, email: String) = api.get()
            .events()
            .patch(agendaId, slotId, Event().apply {
                attendees.add(EventAttendee().apply {
                    setEmail(email)
                })
            })
            .execute()
            .let(::toSlot)

    private fun toSlot(event: Event) =
            BookingSlot(
                    id = event.id,
                    description = event.summary,
                    location = event.location,
                    extraInfo = event.description,
                    attendees = event.attendees?.map(EventAttendee::getId)?.toSet() ?: emptySet(),
                    capacity = event.attendeesCapacity)

    private var Event.attendeesCapacity: Int
        get() = extendedProperties?.shared?.get("attendees.capacity")?.toInt() ?: 0
        set(value) {
            if (extendedProperties == null) {
                extendedProperties = Event.ExtendedProperties()
            }
            if (extendedProperties.shared == null) {
                extendedProperties.shared = mutableMapOf()
            }
            extendedProperties.shared["attendees.capacity"] = value.toString()
        }

}
