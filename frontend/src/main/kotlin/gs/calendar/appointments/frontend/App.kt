package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.imports.Button
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
    scheduler()
}

