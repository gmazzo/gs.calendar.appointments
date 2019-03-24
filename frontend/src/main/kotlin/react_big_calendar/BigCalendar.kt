@file:JsModule("react-big-calendar")

package react_big_calendar

import moment.Moment
import react.RClass
import react.RProps

external interface Localizer

abstract external class BigCalendar : RClass<BigCalendar.Props> {

    interface Props : RProps {
        var localizer: Localizer
        var culture: String
        var events: Array<CalendarEvent>
        var startAccessor: String
        var endAccessor: String
    }

    fun momentLocalizer(moment: Moment): Localizer

}

@JsName("default")
external val bigCalendar: BigCalendar
