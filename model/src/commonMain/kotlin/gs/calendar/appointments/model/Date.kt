package gs.calendar.appointments.model

import kotlinx.serialization.Serializer

expect class Date

@Serializer(forClass = Date::class)
expect object DateSerializer
