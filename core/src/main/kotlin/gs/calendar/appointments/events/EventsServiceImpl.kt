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

    override fun list(agendaId: String, flatInstances: Boolean, user: User?) = api
        .events()
        .list(agendaId)
        .setSingleEvents(flatInstances)
        .setOrderBy("startTime")
        .execute()
        .items
        .map { it.asSlot(user) }

    override fun register(agendaId: AgendaId, slotId: SlotId, user: User) =
        update(agendaId, slotId, user, { assureAvailable(it) }) { it + user.asEventAttendee() }

    override fun unregister(agendaId: AgendaId, slotId: SlotId, user: User) =
        update(agendaId, slotId, user) { it.filter { at -> at.email != user.email } }

    private fun update(
        agendaId: AgendaId,
        slotId: SlotId,
        user: User,
        validation: Event.(User) -> Unit = {},
        action: (List<EventAttendee>) -> List<EventAttendee>
    ) = api.events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            event.validation(user)

            api.events()
                .patch(agendaId, slotId, event.also { ev ->
                    ev.attendees = action(ev.registeredAttendees ?: emptyList())
                })
                .setSendUpdates("externalOnly")
                .execute()
                .let { it.asSlot(user) }
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

    private val Event.available: Boolean
        get() = capacity > registeredAttendees?.size ?: 0

    private operator fun Event.contains(user: User?): Boolean =
        user != null && registeredAttendees?.find { user.isSelf(it.asUser()) } != null

    private fun Event.availableFor(user: User?): Boolean = available && user !in this

    private fun Event.assureAvailable(user: User) {
        if (!availableFor(user)) {
            throw IllegalStateException("Slot $id is no available for $user")
        }
    }

    private fun Event.asSlot(user: User?) = Slot(
        id = id,
        name = summary,
        startTime = start!!.modelDate,
        endTime = end!!.modelDate,
        location = location,
        description = description,
        attendees = registeredAttendees
            ?.filter { guestsCanSeeOtherGuests != false || it.isSelf(user) }
            ?.map { it.asUser() }
            ?: emptyList(),
        selfIsAttendee = user != null && registeredAttendees?.find { it.isSelf(user) } != null,
        available = availableFor(user),
        capacity = capacity
    )

    private val EventDateTime.modelDate
        get() = Date((dateTime ?: date).value)

    private fun EventAttendee.asUser() = User(
        name = displayName,
        email = email
    )

    private fun EventAttendee.isSelf(user: User?): Boolean =
        asUser().isSelf(user)

    private fun User.asEventAttendee() = EventAttendee().also {
        it.displayName = name
        it.email = email
        it.responseStatus = "accepted"
    }

}
