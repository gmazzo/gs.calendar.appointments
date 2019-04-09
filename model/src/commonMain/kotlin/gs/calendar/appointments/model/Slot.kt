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

    override fun hashCode() = id.hashCode()

    override fun equals(other: Any?) = other is Slot && id == other.id

}
