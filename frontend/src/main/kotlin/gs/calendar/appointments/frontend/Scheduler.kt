package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.showLoading
import gs.calendar.appointments.model.Agenda
import moment.moment
import notistack.WithSnackbar
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.setState
import react_big_calendar.CalendarEvent
import react_big_calendar.asLocalizer
import react_big_calendar.bigCalendar
import kotlin.browser.window

private val momentLocalizer = moment.asLocalizer()

class Scheduler : RComponent<Scheduler.Props, Scheduler.State>() {

    override fun componentDidUpdate(prevProps: Props, prevState: State, snapshot: Any) {
        props.agenda?.takeIf { it.id != prevProps.agenda?.id }?.also {
            loadEvents()
        }
    }

    private fun loadEvents() {
        props.agenda?.id?.let {
            API.listSlots(it)
                .then {
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
                .showLoading()
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

    interface Props : WithSnackbar {
        var agenda: Agenda?
    }

    data class State(
        var events: List<CalendarEvent>? = null
    ) : RState

}

fun RBuilder.scheduler(
    agenda: Agenda?,
    handler: (RHandler<Scheduler.Props>) = {}
) = child(Scheduler::class) {
    attrs.agenda = agenda
    handler(this)
}
