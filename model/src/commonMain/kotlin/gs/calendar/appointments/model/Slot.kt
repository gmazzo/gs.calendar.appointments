package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

typealias SlotId = String

private interface MutableSlot {
    val capacity: Int
}

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
    val recurrent: Boolean,
    override val capacity: Int,
    val externalUrl: URL
) : MutableSlot

@Serializable
data class SlotParams(
    override val capacity: Int
) : MutableSlot

val Slot.params get() = SlotParams(capacity = capacity)
