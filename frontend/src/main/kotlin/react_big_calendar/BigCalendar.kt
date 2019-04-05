package react_big_calendar

import gs.calendar.appointments.model.Slot
import moment.Moment
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps
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
    popup: Boolean? = null,
    onSelectEvent: ((CalendarEvent) -> Unit)? = null,
    onSelectSlot: ((start: Date, end: Date) -> Unit)? = null,
    handler: (RHandler<BigCalendar.Props>) = {}
) = bigCalendar {
    attrs.localizer = localizer
    attrs.culture = culture
    attrs.events = events
    attrs.startAccessor = startAccessor
    attrs.endAccessor = endAccessor
    popup?.let { attrs.popup = popup }
    onSelectEvent?.let { attrs.onSelectEvent = onSelectEvent }
    onSelectSlot?.let { attrs.onSelectSlot = onSelectSlot }

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
        var popup: Boolean
        var onSelectEvent: (CalendarEvent) -> Unit
        var onSelectSlot: (start: Date, end: Date) -> Unit
    }

}

data class CalendarEvent(
    val slot: Slot,
    val start: Date,
    val end: Date,
    val title: String
)
