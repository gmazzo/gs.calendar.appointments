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
    val attendees: (List<User>) = emptyList(),
    val selfIsAttendee: Boolean,
    val available: Boolean,
    override val capacity: Int
) : SlotParams

interface SlotParams {
    val capacity: Int
}
