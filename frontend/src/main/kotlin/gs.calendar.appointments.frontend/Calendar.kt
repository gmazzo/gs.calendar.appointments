package gs.calendar.appointments.frontend

import react.RClass
import react.RProps

external interface CalendarProps : RProps {
    var localizer: dynamic
    var events: Array<CalendarEvent>
    var startAccessor: String
    var endAccessor: String
}

@JsModule("react-big-calendar")
external val Calendar: RClass<CalendarProps>
