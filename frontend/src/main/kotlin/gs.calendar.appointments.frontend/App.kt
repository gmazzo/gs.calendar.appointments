package gs.calendar.appointments.frontend

import react.RBuilder
import react.dom.div

fun RBuilder.app() {
    div {
        +"Hello World!"
    }
    Button {
        //attrs.asDynamic.className = "submit-button"
        +"Submit"
    }
}
