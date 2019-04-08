package gs.calendar.appointments.events

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import java.util.*
import javax.inject.Inject

internal class EventsServiceImpl @Inject constructor(
    private val api: Calendar
) : EventsService {

    override fun list(agendaId: String, flatInstances: Boolean) = api
        .events()
        .list(agendaId)
        .setSingleEvents(flatInstances)
        .setOrderBy("startTime")
        .execute()
        .items
        .map { it.asSlot() }

    override fun register(agendaId: AgendaId, slotId: SlotId, user: User) = api
        .events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            api
                .events()
                .patch(agendaId, slotId, event.also { ev ->
                    ev.attendees = (ev.registeredAttendees ?: emptyList()) + user.asEventAttendee()
                })
                .setSendUpdates("externalOnly")
                .execute()
                .let { it.asSlot() }
        }

    private var Event.capacity: Int
        get() = extendedProperties?.shared?.get("attendees.capacity")?.toInt() ?: 1
        set(value) {
            extendedProperties = (extendedProperties ?: Event.ExtendedProperties()).apply {
                shared = (shared ?: mutableMapOf()).apply {
                    this["attendees.capacity"] = value.toString()
                }
            }
        }

    private val Event.registeredAttendees: List<EventAttendee>?
        get() = attendees?.filter { it.responseStatus != "declined" }

    private fun Event.asSlot() = Slot(
        id = id,
        name = summary,
        startTime = start?.dateTime?.value?.let(::Date),
        endTime = end?.dateTime?.value?.let(::Date),
        location = location,
        description = description,
        attendees = registeredAttendees?.map { it.asUser() } ?: emptyList(),
        capacity = capacity
    )

    private fun EventAttendee.asUser() = User(
        name = displayName,
        email = email
    )

    private fun User.asEventAttendee() = EventAttendee().also {
        it.displayName = name
        it.email = email
        it.responseStatus = "accepted"
    }

}
