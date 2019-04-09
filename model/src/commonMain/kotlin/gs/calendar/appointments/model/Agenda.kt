package gs.calendar.appointments.model

typealias AgendaId = String

data class Agenda(
    val id: AgendaId,
    val name: String,
    val description: String?,
    val foregroundColor: String,
    val backgroundColor: String,
    val available: Boolean
)
