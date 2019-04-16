package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

typealias SlotId = String

@Serializable
data class Slot(
    val id: SlotId,
    override val name: String,
    override val description: String? = null,
    @Serializable(DateSerializer::class) val startTime: Date,
    @Serializable(DateSerializer::class) val endTime: Date,
    override val location: String? = null,
    val attendees: (List<User>) = emptyList(),
    val selfIsAttendee: Boolean,
    val available: Boolean,
    override val capacity: Int,
    val externalUrl: URL
) : SlotParams

interface SlotParams {
    val name: String?
    val description: String?
    val location: String?
    val capacity: Int
}
