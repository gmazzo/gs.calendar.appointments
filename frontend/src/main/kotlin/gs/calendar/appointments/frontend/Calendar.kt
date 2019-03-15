@file:Suppress("unused")

package gs.calendar.appointments.frontend

import kotlinext.js.require
import react.RClass
import react.RProps
import kotlin.js.Date

private val css = require("react-big-calendar/lib/css/react-big-calendar.css")
private val less = require("react-big-calendar/lib/less/styles.less")

data class CalendarEvent(
    val start: Date,
    val end: Date,
    val title: String
)

external interface Localizer

external interface CalendarProps : RProps {
    var localizer: Localizer
    var culture: String
    var events: Array<CalendarEvent>
    var startAccessor: String
    var endAccessor: String
}

@JsModule("react-big-calendar")
external val Calendar: RClass<CalendarProps>

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun Moment.asLocalizer() = Calendar.asDynamic().momentLocalizer(this) as Localizer
