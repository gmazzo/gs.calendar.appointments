package gs.calendar.appointments.model

data class Slot(
    val id: String,
    val description: String?,
    val startTime: Date?,
    val endTime: Date?,
    val location: String?,
    val extraInfo: String?,
    val attendees: Set<String>,
    val capacity: Int
) {

    val available = attendees.size < capacity

}
