package gs.calendar.appointments.events

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.api.services.calendar.model.EventDateTime
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import java.util.Date
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

    override fun register(agendaId: AgendaId, slotId: SlotId, user: User) =
        update(agendaId, slotId, user, Collection<EventAttendee>::plus)

    override fun unregister(agendaId: AgendaId, slotId: SlotId, user: User) =
        update(agendaId, slotId, user, Collection<EventAttendee>::minus)

    private fun update(
        agendaId: AgendaId,
        slotId: SlotId,
        user: User,
        action: List<EventAttendee>.(EventAttendee) -> List<EventAttendee>
    ) = api.events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            api
                .events()
                .patch(agendaId, slotId, event.also { ev ->
                    ev.attendees = action(ev.registeredAttendees ?: emptyList(), user.asEventAttendee())
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
        startTime = start!!.modelDate,
        endTime = end!!.modelDate,
        location = location,
        description = description,
        showAttendees = guestsCanSeeOtherGuests ?: true,
        attendees = registeredAttendees?.map { it.asUser() } ?: emptyList(),
        capacity = capacity
    )

    private val EventDateTime.modelDate
        get() =
            Date((dateTime ?: date).value)

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
