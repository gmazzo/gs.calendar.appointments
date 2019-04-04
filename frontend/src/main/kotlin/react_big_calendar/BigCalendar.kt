package react_big_calendar

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
    handler: (RHandler<BigCalendar.Props>) = {}
) = bigCalendar {
    attrs.localizer = localizer
    attrs.culture = culture
    attrs.events = events
    attrs.startAccessor = startAccessor
    attrs.endAccessor = endAccessor

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
    }

}

data class CalendarEvent(
    val start: Date,
    val end: Date,
    val title: String
)
