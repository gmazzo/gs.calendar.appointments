package gs.calendar.appointments.model

import java.util.*

data class Slot(
    val id: SlotId,
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
