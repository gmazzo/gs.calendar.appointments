package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

typealias SlotId = String

@Serializable
data class Slot(
    val id: SlotId,
    val name: String,
    val description: String?,
    @Serializable(DateSerializer::class) val startTime: Date?,
    @Serializable(DateSerializer::class) val endTime: Date?,
    val location: String?,
    val attendees: List<User>,
    val capacity: Int
) {

    val available get() = attendees.size < capacity

    fun availableFor(user: User?) = available && user !in this

    operator fun contains(user: User?) = user != null && attendees.find { it.email == user.email } != null

}
