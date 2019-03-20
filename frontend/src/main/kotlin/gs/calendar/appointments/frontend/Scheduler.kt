package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.Calendar
import gs.calendar.appointments.frontend.imports.CalendarEvent
import gs.calendar.appointments.frontend.imports.asLocalizer
import gs.calendar.appointments.frontend.imports.moment
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import kotlin.browser.window

private val momentLocalizer = moment.asLocalizer()

class Scheduler : RComponent<RProps, Scheduler.State>() {

    override fun componentDidMount() {
        // TODO not working
        /*
        API.listSlots().then {
            setState(State(events = it.data?.map { ev ->
                CalendarEvent(
                    ev.startTime!!,
                    ev.endTime!!,
                    ev.description!!
                )
            }))
        }
        */
    }

    override fun RBuilder.render() {
        agendasSelector()
        br {}
        Calendar {
            attrs {
                localizer = momentLocalizer
                culture = window.navigator.language
                events = state.events?.toTypedArray() ?: emptyArray()
                startAccessor = "start"
                endAccessor = "end"
            }
        }
    }

    data class State(
        var events: List<CalendarEvent>? = null
    ) : RState

}

fun RBuilder.scheduler() = child(Scheduler::class) {}
