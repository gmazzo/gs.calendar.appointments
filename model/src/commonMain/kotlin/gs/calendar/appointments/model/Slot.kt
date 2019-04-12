package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

typealias SlotId = String

@Serializable
data class Slot(
    val id: SlotId,
    val name: String,
    val description: String? = null,
    @Serializable(DateSerializer::class) val startTime: Date,
    @Serializable(DateSerializer::class) val endTime: Date,
    val location: String? = null,
    // TODO attendees should not be exposed to the client, add some auth token for current user and resolve it in backend
    val showAttendees: Boolean,
    val attendees: (List<User>) = emptyList(),
    val capacity: Int
) {

    val available get() = attendees.size < capacity

    fun availableFor(user: User?) = available && user !in this

    operator fun contains(user: User?) = attendees.find { it.isSelf(user) } != null

}
