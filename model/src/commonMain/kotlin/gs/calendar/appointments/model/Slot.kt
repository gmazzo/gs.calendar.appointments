package gs.calendar.appointments.model

typealias SlotId = String

data class Slot(
    val id: SlotId,
    val name: String,
    val description: String?,
    val startTime: Date?,
    val endTime: Date?,
    val location: String?,
    val attendees: List<User>,
    val capacity: Int
)

val Slot.available get() = attendees.size < capacity

fun Slot.hasAttendee(user: User?) = user != null && attendees.find { it.email == user.email } != null
