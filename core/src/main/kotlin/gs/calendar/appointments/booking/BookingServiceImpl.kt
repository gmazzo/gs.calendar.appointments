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
    private val api: Provider<Calendar>
) : BookingService {

    override fun list(agendaId: AgendaId) = api.get()
        .events()
        .list(agendaId)
        .execute()
        .items
        .map { it.toSlot() }

    override fun book(agendaId: AgendaId, slotId: BookingSlotId, bookEmail: String) = api.get()
        .events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            api
                .get()
                .events()
                // FIXME just one iteration, not the whole series
                .patch(agendaId, slotId, event.apply {
                    val attendee = EventAttendee().apply {
                        email = bookEmail
                        responseStatus = "accepted"
                    }

                    if (attendees == null) {
                        attendees = mutableListOf(attendee)

                    } else {
                        attendees.add(attendee)
                    }
                })
                .setSendUpdates("all")
                .execute()
                .let { it.toSlot() }
        }


    private fun Event.toSlot() =
        BookingSlot(
            id = id,
            description = summary,
            location = location,
            extraInfo = description,
            attendees = attendees?.map(EventAttendee::getEmail)?.toSet() ?: emptySet(),
            capacity = attendeesCapacity
        )

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
