package react_big_calendar

import gs.calendar.appointments.model.Slot
import moment.Moment
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps
import react.dom.WithClassName
import kotlin.js.Date

@JsModule("react-big-calendar")
@JsNonModule
internal external val module: dynamic

val bigCalendar = module.default.unsafeCast<BigCalendar>()

fun momentLocalizer(moment: Moment) = module.default.momentLocalizer(moment).unsafeCast<Localizer>()

fun RBuilder.bigCalendar(
    localizer: Localizer,
    culture: String,
    events: Array<CalendarEvent>,
    startAccessor: String,
    endAccessor: String,
    components: CalendarComponents<*>? = null,
    eventPropGetter: ((CalendarEvent) -> RProps)? = null,
    popup: Boolean? = null,
    defaultView: CalendarDefaultView? = null,
    onSelectEvent: ((CalendarEvent) -> Unit)? = null,
    onSelectSlot: ((start: Date, end: Date) -> Unit)? = null,
    handler: (RHandler<BigCalendar.Props>) = {}
) = bigCalendar {
    attrs.localizer = localizer
    attrs.culture = culture
    attrs.events = events
    attrs.startAccessor = startAccessor
    attrs.endAccessor = endAccessor
    components?.let { attrs.components = it }
    eventPropGetter?.let { attrs.eventPropGetter = it }
    popup?.let { attrs.popup = it }
    defaultView?.let { attrs.defaultView = it }
    onSelectEvent?.let { attrs.onSelectEvent = it }
    onSelectSlot?.let { attrs.onSelectSlot = it }

    handler(this)
}

fun Moment.asLocalizer() = momentLocalizer(this)

@Suppress("unused")
private val css = kotlinext.js.require("react-big-calendar/lib/css/react-big-calendar.css")

@Suppress("unused")
private val less = kotlinext.js.require("react-big-calendar/lib/less/styles.less")

external interface Localizer

abstract external class BigCalendar : RClass<BigCalendar.Props> {

    interface Props : RProps {
        var localizer: Localizer
        var culture: String
        var events: Array<CalendarEvent>
        var startAccessor: String
        var endAccessor: String
        var components: CalendarComponents<*>?
        var eventPropGetter: ((CalendarEvent) -> RProps)?
        var popup: Boolean
        var onSelectEvent: (CalendarEvent) -> Unit
        var onSelectSlot: (start: Date, end: Date) -> Unit
        @JsName("defaultView")
        var defaultViewValue: String
    }

    interface EventProps : WithClassName {
        val event: CalendarEvent
    }

}

data class CalendarEvent(
    val slot: Slot,
    val start: Date,
    val end: Date,
    val title: String
)

data class CalendarComponents<T : BigCalendar.EventProps>(
    val event: RClass<T>? = undefined,
    val eventWrapper: RClass<T>? = undefined
)

enum class CalendarDefaultView(val value: String) {
    MONTH("month"),
    WEEK("week"),
    WORK_WEEK("work_week"),
    DAY("day"),
    AGENDA("agenda")
}

var BigCalendar.Props.defaultView
    get() = defaultViewValue.let { CalendarDefaultView.values().find { v -> v.value == it }!! }
    set(value) {
        defaultViewValue = value.value
    }
