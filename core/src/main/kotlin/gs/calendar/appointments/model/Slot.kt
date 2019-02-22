package gs.calendar.appointments.model

data class Slot(
    val id: SlotId,
    val description: String?,
    val location: String?,
    val extraInfo: String?,
    val attendees: Set<String>,
    val capacity: Int
) {

    val available = attendees.size < capacity

}
