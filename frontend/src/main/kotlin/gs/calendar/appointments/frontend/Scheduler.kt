package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import moment.moment
import notistack.WithSnackbar
import notistack.withSnackbar
import rClass
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.invoke
import react.setState
import react_big_calendar.CalendarEvent
import react_big_calendar.asLocalizer
import react_big_calendar.bigCalendar
import kotlin.browser.window

private val momentLocalizer = moment.asLocalizer()

class Scheduler : RComponent<Scheduler.Props, Scheduler.State>() {

    override fun componentDidMount() {
        loadEvents()
    }

    override fun componentDidUpdate(prevProps: Props, prevState: State, snapshot: Any) {
        props.agenda?.takeIf { it.id != prevProps.agenda?.id }?.also {
            loadEvents()
        }
    }

    private fun loadEvents() {
        props.agenda?.id?.let {
            API.listSlots(it)
                .uiLinked(props)
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
        }
    }

    override fun RBuilder.render() {
        bigCalendar(
            localizer = momentLocalizer,
            culture = window.navigator.language,
            events = state.events?.toTypedArray() ?: emptyArray(),
            startAccessor = "start",
            endAccessor = "end"
        )
    }

    interface Props : WithSnackbar {
        var agenda: Agenda?
    }

    data class State(
        var events: List<CalendarEvent>? = null
    ) : RState

}

private val wrapped = withSnackbar<Scheduler.Props>()(Scheduler::class.rClass)

fun RBuilder.scheduler(
    agenda: Agenda?,
    handler: (RHandler<Scheduler.Props>) = {}
) = wrapped {
    attrs.agenda = agenda

    handler(this)
}
