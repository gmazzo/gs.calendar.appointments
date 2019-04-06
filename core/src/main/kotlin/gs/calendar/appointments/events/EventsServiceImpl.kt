package gs.calendar.appointments.events

import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.api.services.people.v1.PeopleService
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

internal class EventsServiceImpl @Inject constructor(
    private val capendarApi: Provider<Calendar>,
    private val peopleApi: Provider<PeopleService>
) : EventsService {

    override fun list(agendaId: String, flatInstances: Boolean) = capendarApi.get()
        .events()
        .list(agendaId)
        .setSingleEvents(flatInstances)
        .setOrderBy("startTime")
        .execute()
        .items
        .map { it.toSlot() }

    override fun invite(agendaId: AgendaId, slotId: SlotId, email: String) = capendarApi.get()
        .events()
        .get(agendaId, slotId)
        .execute()
        .let { event ->
            capendarApi.get()
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
            attendees = attendees?.resolveUsers() ?: emptyList(),
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

    private fun Iterable<EventAttendee>.resolveUsers() = runBlocking(Dispatchers.IO) {
        val api = peopleApi.get().People()

        fun EventAttendee.retrievePhotoAsync() = id?.let {
            async {
                api.get(it)
                    ?.setPersonFields("photos")
                    ?.execute()
                    ?.photos
                    ?.firstOrNull()
                    ?.let { it.url }
            }
        }

        map { it to it.retrievePhotoAsync() }
            .map { (ev, photo) ->
                User(
                    name = ev.displayName,
                    email = ev.email,
                    imageUrl = photo?.await()
                )
            }
    }

}
