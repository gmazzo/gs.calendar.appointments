package gs.calendar.appointments.model

data class Agenda(
    val id: String,
    val name: String,
    val description: String?,
    val foregroundColor: String,
    val backgroundColor: String
)
