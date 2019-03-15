package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.Button
import gs.calendar.appointments.frontend.imports.Calendar
import gs.calendar.appointments.frontend.imports.asLocalizer
import gs.calendar.appointments.frontend.imports.moment
import react.RBuilder
import react.dom.div
import kotlin.browser.window

fun RBuilder.app() {
    div {
        +"Hello World!"
    }
    Button {
        attrs {
            className = "submit-button"
            onClick = {
                window.alert("Vois La")
            }
        }
        +"Submit"
    }
    Calendar {
        attrs {
            localizer = moment.asLocalizer()
            culture = window.navigator.language
            events = arrayOf()
            startAccessor = "start"
            endAccessor = "end"
        }
    }
}
