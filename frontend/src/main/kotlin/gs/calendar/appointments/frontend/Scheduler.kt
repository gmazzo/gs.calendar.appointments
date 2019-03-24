package gs.calendar.appointments.frontend

import moment.moment
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState
import react_big_calendar.CalendarEvent
import react_big_calendar.asLocalizer
import react_big_calendar.bigCalendar
import kotlin.browser.window

private val momentLocalizer = moment.asLocalizer()

class Scheduler : RComponent<RProps, Scheduler.State>() {

    override fun componentDidMount() {
        API.listSlots().then {
            setState {
                events = it.data?.map { ev ->
                    CalendarEvent(
                        ev.startTime!!,
                        ev.endTime!!,
                        ev.description!!
                    )
                }
            }
        }
    }

    override fun RBuilder.render() {
        bigCalendar {
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

fun RBuilder.scheduler(handler: (RHandler<RProps>) = {}) =
    child(Scheduler::class, handler)
