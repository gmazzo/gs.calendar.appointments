package react_big_calendar

import kotlinext.js.require
import moment.Moment
import react.RBuilder
import react.RHandler
import kotlin.js.Date

@Suppress("unused")
private val css = require("react-big-calendar/lib/css/react-big-calendar.css")

@Suppress("unused")
private val less = require("react-big-calendar/lib/less/styles.less")

data class CalendarEvent(
    val start: Date,
    val end: Date,
    val title: String
)

fun Moment.asLocalizer() = bigCalendar.momentLocalizer(this)

fun RBuilder.bigCalendar(handler: RHandler<BigCalendar.Props>) = bigCalendar.invoke(handler)
