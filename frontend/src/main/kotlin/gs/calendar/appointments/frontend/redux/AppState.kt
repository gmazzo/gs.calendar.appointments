package gs.calendar.appointments.frontend.redux

import gs.calendar.appointments.model.Agenda
import kotlin.js.Promise

data class AppState(
    val loading: Boolean = false,
    val currentPromise: Promise<*>? = null,
    val currentAgenda: Agenda? = null
)
