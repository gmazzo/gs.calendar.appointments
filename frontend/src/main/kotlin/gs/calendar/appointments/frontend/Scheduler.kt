package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.Calendar
import gs.calendar.appointments.frontend.imports.CalendarEvent
import gs.calendar.appointments.frontend.imports.asLocalizer
import gs.calendar.appointments.frontend.imports.moment
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import kotlin.browser.window

private val momentLocalizer = moment.asLocalizer()

data class SchedulerState(
    var events: List<CalendarEvent>? = null
) : RState

class Scheduler : RComponent<RProps, SchedulerState>() {

    override fun componentDidMount() {
        // TODO improve logic here
        api.listSlots().then {
            setState(SchedulerState(events = it?.map { CalendarEvent(it.startTime!!, it.endTime!!, it.description!!) }))
        }
    }

    override fun RBuilder.render() {
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

}

fun RBuilder.scheduler() = child(Scheduler::class) {}
