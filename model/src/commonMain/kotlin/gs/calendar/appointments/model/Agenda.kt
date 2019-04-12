package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

typealias AgendaId = String

@Serializable
data class Agenda(
    val id: AgendaId,
    val name: String,
    val description: String? = null,
    val foregroundColor: String,
    val backgroundColor: String,
    val available: Boolean
)
