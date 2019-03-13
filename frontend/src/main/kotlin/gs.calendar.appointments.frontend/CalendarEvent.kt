package gs.calendar.appointments.frontend

import kotlin.js.Date

data class CalendarEvent(
    val start: Date,
    val end: Date,
    val title: String
)
