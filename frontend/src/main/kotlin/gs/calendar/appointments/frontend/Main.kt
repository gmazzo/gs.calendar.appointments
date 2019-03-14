package gs.calendar.appointments.frontend

import react.dom.h1
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    // FIXME kotlinext.js.require("react-big-calendar/lib/css/react-big-calendar.css")

    window.onload = {
        render(document.getElementById("root")!!) {
            h1 { +"Hello World!" }
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
                    events = arrayOf()
                    startAccessor = "start"
                    endAccessor = "end"
                }
            }
        }
    }
}
