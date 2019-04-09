package gs.calendar.appointments.frontend.scheduler

import css
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.User
import gs.calendar.appointments.model.available
import gs.calendar.appointments.model.hasAttendee
import kotlinext.js.jsObject
import kotlinx.css.Color
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.textDecoration
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
import react_big_calendar.CalendarComponents
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
                        events = it.map { slot ->
                            CalendarEvent(
                                slot = slot,
                                start = slot.startTime!!,
                                end = slot.endTime!!,
                                title = slot.name
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
            endAccessor = "end",
            popup = true,
            components = CalendarComponents(eventWrapper = AppointmentView::class.rClass),
            eventPropGetter = ::eventPropGetter,
            onSelectEvent = { SelectSlot(it.slot).dispatch() }
        )
    }

    private fun eventPropGetter(event: CalendarEvent): AppointmentView.Props = jsObject {
        val available = event.slot.available
        val hasSelf = event.slot.hasAttendee(props.user)

        css {
            when {
                hasSelf -> Color.darkGreen
                !available -> Color.darkRed
                else -> null
            }?.let { backgroundColor = it }

            if (!hasSelf && !available) {
                textDecoration(TextDecorationLine.lineThrough)
            }
        }
    }

    interface Props : WithSnackbar {
        var agenda: Agenda?
        var user: User?
    }

    data class State(
        var events: List<CalendarEvent>? = null
    ) : RState

}

private val wrapped = withSnackbar<Scheduler.Props>()(Scheduler::class.rClass)

fun RBuilder.scheduler(
    agenda: Agenda?,
    user: User?,
    handler: (RHandler<Scheduler.Props>) = {}
) = wrapped {
    attrs.agenda = agenda
    attrs.user = user

    handler(this)
}
