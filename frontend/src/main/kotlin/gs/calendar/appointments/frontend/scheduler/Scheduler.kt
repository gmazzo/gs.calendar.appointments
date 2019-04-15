package gs.calendar.appointments.frontend.scheduler

import allOf
import css
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.User
import kotlinext.js.jsObject
import kotlinx.css.Color
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.textDecoration
import material_ui.core.styles.WithTheme
import material_ui.core.styles.withTheme
import moment.moment
import notistack.WithSnackbar
import notistack.withSnackbar
import rClass
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
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
        if (props.agenda !== prevProps.agenda) { // !== is intentional to force an update
            loadEvents()
        }
    }

    private fun loadEvents() {
        props.agenda?.id?.let {
            API.listSlots(it, props.user)
                .uiLinked(props)
                .then {
                    setState {
                        events = it.map { slot ->
                            CalendarEvent(
                                slot = slot,
                                start = slot.startTime,
                                end = slot.endTime,
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
        appointmentDetails(
            agenda = props.agenda,
            slot = props.slot,
            user = props.user,
            props = props
        )
    }

    private fun eventPropGetter(event: CalendarEvent): AppointmentView.Props = jsObject {
        val inEvent = event.slot.selfIsAttendee
        val unavailable = !event.slot.available

        css {
            when {
                inEvent -> Color.darkGreen
                unavailable -> Color.darkRed
                else -> null
            }?.let { backgroundColor = it }

            if (!inEvent && unavailable) {
                textDecoration(TextDecorationLine.lineThrough)
            }
        }
    }

    interface Props : WithTheme, WithSnackbar {
        var agenda: Agenda?
        var slot: Slot?
        var user: User?
    }

    data class State(
        var events: List<CalendarEvent>? = null
    ) : RState

}

private val wrapped = allOf<Scheduler.Props>(withTheme(), withSnackbar())(Scheduler::class.rClass)

fun RBuilder.scheduler(
    agenda: Agenda?,
    slot: Slot?,
    user: User?,
    handler: (RHandler<Scheduler.Props>) = {}
) = wrapped {
    attrs.agenda = agenda
    attrs.slot = slot
    attrs.user = user

    handler(this)
}
