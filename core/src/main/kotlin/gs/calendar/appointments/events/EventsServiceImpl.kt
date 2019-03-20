package gs.calendar.appointments.events

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

internal class EventsServiceImpl @Inject constructor(
    private val api: Provider<Calendar>
) : EventsService {

    override fun list(agendaId: String, flatInstances: Boolean) = api.get()
        .events()
        .list(agendaId)
        .setSingleEvents(flatInstances)
        .execute()
        .items
        .map { it.toSlot() }

    override fun invite(agendaId: AgendaId, slotId: SlotId, email: String) = api.get()
        .events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            api.get()
                .events()
                .patch(agendaId, slotId, event.apply {
                    attendees = (attendees ?: mutableListOf()).apply {
                        add(EventAttendee().also {
                            it.email = email
                            it.responseStatus = "accepted"
                        })
                    }
                })
                .setSendUpdates("all")
                .execute()
                .let { it.toSlot() }
        }


    private fun Event.toSlot() =
        Slot(
            id = id,
            description = summary,
            startTime = start?.dateTime?.value?.let(::Date),
            endTime = end?.dateTime?.value?.let(::Date),
            location = location,
            extraInfo = description,
            attendees = attendees?.map(EventAttendee::getEmail)?.toSet() ?: emptySet(),
            capacity = attendeesCapacity
        )

    private var Event.attendeesCapacity: Int
        get() = extendedProperties?.shared?.get("attendees.capacity")?.toInt() ?: 1
        set(value) {
            extendedProperties = (extendedProperties ?: Event.ExtendedProperties()).apply {
                shared = (shared ?: mutableMapOf()).apply {
                    this["attendees.capacity"] = value.toString()
                }
            }
        }

}
